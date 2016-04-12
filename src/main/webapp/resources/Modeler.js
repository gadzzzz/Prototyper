var currElementId = '';
var currPreviewId = '';
var prevPreviewId = '';
var elementIndex = 0;
var previewPageIndex = 0;
var previewPageCount = 0;
var map;
var isCopy = "ok";
var dragList = [];

function addElement(content,taghtml) {
	$(content).append(taghtml);
    $('#id'+elementIndex).click(
        function(e){
            if (e.ctrlKey) {
                currElementId = '#' + this.id;
                $(currElementId).addClass('activeElement');
                $(currElementId).addClass('multipleSelect');
            }else {
                if(currElementId!='#'+this.id) {
                    currElementId = '#' + this.id;
                    $('.activeElement').removeClass('multipleSelect');
                    $(currElementId).addClass('activeElement').siblings().removeClass('activeElement');
                    monitorState();
                }
            }
            preview();
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
		start: function (event, ui) {
            $('#' + this.id).trigger("click");
            currElementId = '#' + this.id;
            var classList = $('.multipleSelect');
            dragList = [];
            $.each(classList, function(index) {
                dragList.push(new elementConstrucrot( ui.offset.left-$(this).offset().left,ui.offset.top-$(this).offset().top));
            });
		},
		drag: function(event, ui) {
            if (event.ctrlKey) {
                var classList = $('.multipleSelect');
                $.each(classList, function(index) {
                    if(('#'+$(this).attr('id'))!=currElementId){
                        var distanceLeft = ui.offset.left-$(this).offset().left;
                        var distanceTop = ui.offset.top-$(this).offset().top;
                        var diffLeft = distanceLeft - dragList[index].left;
                        var diffTop = distanceTop -  dragList[index].top;
                        $(this).offset({top:$(this).offset().top+diffTop, left:$(this).offset().left+diffLeft});
                        dragList[index] = (new elementConstrucrot( ui.offset.left-$(this).offset().left,ui.offset.top-$(this).offset().top));
                    }
                });
            }else {
                monitorState();
            }
		},
		stop: function(){
			preview();
		}
	}
	);
}

function elementConstrucrot(left,top){
    this.left = left;
    this.top = top;
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
            if(isCopy==="ok"){
                pageContent	= copyContext();
                map.put(prevPreviewId,pageContent);
            }
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
            clearMonitorState();
		}
	);
	if(previewPageIndex==1) {
		$("#pg1").click();
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
    else if(tag=='calendar')
        taghtml = calendarElement.format(elementIndex,tag);
    else if(tag=='pager')
        taghtml = pagerElement.format(elementIndex,tag);
    else if(tag=='vscroll')
        taghtml = vScrollElement.format(elementIndex,tag);
    else if(tag=='hscroll')
        taghtml = hScrollElement.format(elementIndex,tag);
    else if(tag=='border')
        taghtml = borderElement.format(elementIndex,tag);
	else if(tag=='label')
		taghtml = labelElement.format(elementIndex,tag);
	else if(tag=='link')
		taghtml = linkElement.format(elementIndex,tag);
	else if(tag=='textblock')
		taghtml = textBlockElement.format(elementIndex,tag);
	else if(tag=='horizontalhr')
		taghtml = horizontalHrElement.format(elementIndex,tag);
    else if(tag=='verticalvr')
        taghtml = verticalHrElement.format(elementIndex,tag);
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

function createComplicatedTag(tag){
    var dataarr = [];
    if(tag=='loginForm')
        dataarr = loginForm;
    if(tag=='registerForm')
        dataarr = registerForm;
    if(tag=='newsForm')
        dataarr = newsForm;
    jQuery.each(dataarr,function(i, val){
        createTag(val.name);
        $("#id"+elementIndex).offset({top:val.top,left:val.left});
        $("#id"+elementIndex).text(val.val);
        $("#id"+elementIndex).addClass('activeElement');
        $("#id"+elementIndex).addClass('multipleSelect');
    });
}

function save(){
    var title = $('#savename').val();
    if(title.trim()!='') {
        var pageContent = new Array();
        pageContent = copyContext();
        map.put(prevPreviewId, pageContent);
        var list = [];
        list = map.listValues();
        var ok = "ok";
        for(var i=0;i<list.length;i++)
            if(list[i].length==0)
                ok = "no";
        if(ok == "ok") {
            var obj = {
                id: 0,
                name: title,
                contextObjects: list
            };
            $.ajax({
                type: "post",
                url: "/save",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                data: JSON.stringify(obj),
            });
        }else
            alert("Нельзя сохранять пустые страницы");
    }else
        alert("Необходимо задать имя шаблону");
}

function load(prototypeId){
    $('#content').empty();
    $("#pages").contents().filter(function(){
        return !$(this).is('#pagebtns');
    }).remove();
    clearMonitorState();
    map = new Map;
    previewPageIndex = 0;
    previewPageCount = 0;
    currElementId = '';
    currPreviewId = '';
    prevPreviewId = '';
    elementIndex = 0;
    $.ajax({
        type:"post",
        url:"/load",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        data: JSON.stringify(prototypeId),
        success: function(data){
            $("#savename").val(data.name);
            isCopy = "no";
            for(var i=0;i<data.pageList.length;i++){
                addPreviewPage();
                var pg = [];
                pg = JSON.parse(data.pageList[i].json);
                map.put('#pg'+previewPageIndex,pg);
            }
            prevPreviewId = '';
            $("#pg1").click();
            isCopy = "ok";
        }
    });
    $("#loaddialog").dialog("close");
}

function autoload(link){
    $.ajax({
        type:"post",
        url:"/loadByLink?link="+link,
        success: function(data){
            $("#savename").val(data.name);
            $('#content').empty();
            $("#pages").contents().filter(function(){
                return !$(this).is('#pagebtns');
            }).remove();
            clearMonitorState();
            map = new Map;
            previewPageIndex = 0;
            previewPageCount = 0;
            currElementId = '';
            currPreviewId = '';
            prevPreviewId = '';
            elementIndex = 0;
            isCopy = "no";
            for(var i=0;i<data.pageList.length;i++){
                addPreviewPage();
                var pg = [];
                pg = JSON.parse(data.pageList[i].json);
                map.put('#pg'+previewPageIndex,pg);
            }
            prevPreviewId = '';
            $("#pg1").click();
            isCopy = "ok";
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
    currElementId = '';
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

function loadAll(){
    $.ajax({
        type:"post",
        url:"/loadall",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        success: function(data){
            $('#prototypelist').empty();
            console.log(data);
            var str = "<table class='listprototype' width='100%' style='border-spacing: 8px;''>";
            for(var i=0;i<data.length;i++)
                str = str + "<tr><td onclick='load("+data[i].id+")'>"+data[i].name+';'+data[i].updateDate+"</td></tr>";
            var str = str + "</table>";
            $('#prototypelist').append(str);
        }
    });
}

function toFile(){
    html2canvas($('#content'), {
        onrendered: function(canvas) {
            var base64String = canvas.toDataURL("image/png");
            base64String.replace(/^data:image\/[^;]/, 'data:application/octet-stream');
            window.open(base64String);
        }
    });
}