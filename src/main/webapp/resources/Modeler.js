var currElementId = '';
var currPreviewId = '';
var prevPreviewId = '';
var elementIndex = 0;
var previewPageIndex = 0;
var previewPageCount = 0;
var map;

function addElement(content,taghtml) {
	$(content).append(taghtml);
    $('.clickElement').click(
        function(){
            if(currElementId!='#'+this.id){
                currElementId ='#'+ this.id;
                $(currElementId).addClass('activeElement').siblings().removeClass('activeElement');
                monitorState();
                preview();
            }
        }
    );
	$('.resizeElement').resizable({
		start: function(event, ui){
            $('#'+ this.id).trigger("click");
			currElementId ='#'+ this.id;
		},
		resize: function(event, ui) {
			monitorState();
		},
		stop: function(event,ui){
			preview();
		}
	});
	$('.dragElement').draggable({
		containment:"parent",
		start: function () {
            $('#'+ this.id).trigger("click");
			currElementId ='#'+ this.id;
		},
		drag: function() {
			monitorState();
		},
		stop: function(){
			preview();
		}
	}
	);
    preview();
}

function addPreviewPage(){
	previewPageIndex++;
    previewPageCount++;
	var previewHtml = pagePreviewElement.format(previewPageIndex);
	$("#pages").append(previewHtml);
	$('.clickPreview').click(
		function(){
			if(map==null)
				map = new Map;
			currPreviewId ='#'+ this.id;
			if(map.size==0)
				prevPreviewId=currPreviewId;
			var pageContent = new Array();
			pageContent	= copyContext();
			map.put(prevPreviewId,pageContent);
			$(currPreviewId).addClass('activeElement').siblings().removeClass('activeElement');
			pageContent = map.get(currPreviewId);
			if(currPreviewId!=prevPreviewId){
				$('#content').empty();
				if(pageContent!=null){
					for(var i=0;i<pageContent.length;i++) {
						createTag(pageContent[i].tagtype);
						currElementId = '#id'+ elementIndex;
						$(currElementId+' span').text(pageContent[i].text);
						$(currElementId).width(pageContent[i].width);
						$(currElementId).height(pageContent[i].height);
						$(currElementId).offset({top:pageContent[i].y, left:pageContent[i].x});
						$(currElementId+' span').css('font-size',pageContent[i].textsize);
					}
				}
			}
			prevPreviewId=currPreviewId;
		}
	);
	if(previewPageIndex==1) {
		$("#pg" + 1).click();
	}
}

function copyContext(){
	var contextArray = new Array();
	$("div#content > *").each(function(){
		var curr = '#'+ this.id;
		var contextObject = new Object();
		contextObject.tagtype = $(curr).attr("tagtype");
		contextObject.text = $(curr+' span').text();
		contextObject.width = $(curr).width();
		contextObject.height = $(curr).height();
		contextObject.x = $(curr).offset().left;
		contextObject.y = $(curr).offset().top;
		contextObject.textsize = $(curr+' span').css('font-size');
		contextObject.color = $(curr).css('background-color');
		contextArray.push(contextObject);
	});
	return contextArray;
}

function createTag(tag){
	var taghtml = '';
	elementIndex++;
	if(tag=='button')
		taghtml = buttonElement.format(elementIndex,tag);
	else if(tag=='field')
		taghtml = fieldElement.format(elementIndex,tag);
	else if(tag=='image')
		taghtml = imageElement.format(elementIndex,tag);
	else if(tag=='label')
		taghtml = labelElement.format(elementIndex,tag);
	else if(tag=='link')
		taghtml = linkElement.format(elementIndex,tag);
	else if(tag=='textblock')
		taghtml = textBlockElement.format(elementIndex,tag);
	else if(tag=='horizontalhr')
		taghtml = horizontalHrElement.format(elementIndex,tag);
	else if(tag=='checkbox')
		taghtml = checkboxElement.format(elementIndex,tag);
	else if(tag=='radiobutton')
		taghtml = radioButtonElement.format(elementIndex,tag);
	else if(tag=='select')
		taghtml = selectElement.format(elementIndex,tag);
	else if(tag=='textarea')
		taghtml = textAreaElement.format(elementIndex,tag);
	addElement("#content",taghtml);
}

function createCompoundTag(tag){
	//elementIndex++;
	//if(tag=='loginForm') {
	//	var parentIndex = "#id"+elementIndex;
	//
	//	addElement(parentIndex,labelElement.format(++elementIndex,'label'));
	//	addElement(parentIndex,fieldElement.format(++elementIndex,'field'));
	//}

}

function save(){
    var pageContent = new Array();
    pageContent	= copyContext();
    map.put(prevPreviewId,pageContent);
    var list = [];
    list = map.listValues();
    $.ajax({
        type:"post",
        url:"/save",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        data: JSON.stringify(list),
        success: function(){

        }
    });
}

function load(prototypeId){
    $.ajax({
        type:"post",
        url:"/load",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        data: JSON.stringify(prototypeId),
        success: function(data){
            alert(data.name+";"+data.updateDate);
        }
    });
}

function monitorState(){
	document.getElementById("elementId").value=currElementId;
	document.getElementById("elementText").value=$(currElementId+' span').text();
	document.getElementById("elementWidth").value=$(currElementId).width();
	document.getElementById("elementHeight").value=$(currElementId).height();
	document.getElementById("elementX").value=$(currElementId).offset().left;
	document.getElementById("elementY").value=$(currElementId).offset().top;
	document.getElementById("elementTextSize").value=$(currElementId+' span').css('font-size');
	document.getElementById("elementColor").value=$(currElementId).css('background-color');
}

function clearMonitorState(){
    document.getElementById("elementId").value='';
    document.getElementById("elementText").value='';
    document.getElementById("elementWidth").value='';
    document.getElementById("elementHeight").value='';
    document.getElementById("elementX").value='';
    document.getElementById("elementY").value='';
    document.getElementById("elementTextSize").value='';
    document.getElementById("elementColor").value='';
}

function preview(){
	html2canvas($('#content'), {
		onrendered: function(canvas) {
			var base64String = canvas.toDataURL("image/png");
			$(currPreviewId).css({'background-image':'url('+base64String+')','background-size': 'cover'});
		}
	});
}

function changeState(){
	$(currElementId+' span').text(document.getElementById("elementText").value);
	$(currElementId).width(document.getElementById("elementWidth").value);
	$(currElementId).height(document.getElementById("elementHeight").value);
	$(currElementId).offset({top:document.getElementById("elementY").value, left:document.getElementById("elementX").value});
	$(currElementId+' span').css('font-size',document.getElementById("elementTextSize").value);
	preview();
}

function removeElement(){
	$(currElementId).remove();
	preview();
    clearMonitorState();
}

function removePreviewPage(){
    if(previewPageCount > 1){
        $(currPreviewId).remove();
        $(currPreviewId+' span').remove();
        previewPageCount--;
    }
}

function copyElement(){
	var tagtype = $(currElementId).attr("tagtype");
	createTag(tagtype);
	var tmpid = "#id"+elementIndex;
	$(tmpid+' span').text(document.getElementById("elementText").value);
	$(tmpid).width(document.getElementById("elementWidth").value);
	$(tmpid).height(document.getElementById("elementHeight").value);
	$(tmpid).offset({top:document.getElementById("elementY").value, left:document.getElementById("elementX").value});
	$(tmpid+' span').css('font-size',document.getElementById("elementTextSize").value);
    $(tmpid).trigger("click");
	currElementId=tmpid;
}

String.prototype.format = function() {
	var formatted = this;
	for (var i = 0; i < arguments.length; i++) {
		var regexp = new RegExp('\\{'+i+'\\}', 'gi');
		formatted = formatted.replace(regexp, arguments[i]);
	}
	return formatted;
};