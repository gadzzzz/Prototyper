<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/font-awesome.css">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/bootstrap-social.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="/resources/Modeler.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <style>
        body{
            font-family: Tahoma;
        }
        #footer{
            text-align: center;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            background-color: rgba(239, 240, 237, 0.9);
            height:50px;
            padding: 1%;
            width: 98%;
        }
    </style>
    <script>
        var self = ${myid};
        function shareTo(link,id){
            var to = $("#"+id).val();
            if(to!=self){
                if(to>0) {
                    $.ajax({
                        type: "post",
                        url: "/share?link=" + link + "&to=" + to,
                        success: function (data) {
                            alert("Прототип отправлен")
                        }
                    });
                }else
                    alert("Введите ид получателя");
            }else
                alert("Самому себе отправлять нельзя")
        }
        function deletePrototype(id){
            $.ajax({
                type:"post",
                url:"/delete?prototypeId="+id,
                async:false,
                success:function(){
                    location.reload();
                }
            })
        }
        function deleteShare(id){
            $.ajax({
                type:"post",
                url:"/deleteShare?shareId="+id,
                async:false,
                success:function(){
                    location.reload();
                }
            })
        }
        $(function() {
            $("#loaddialog").dialog({
                autoOpen: false,
                height: 114,
                width: 400,
                resizable: false
            });
        });
        function editName(){
            var id = $('#prototypeid').val();
            var name = $('#prototypename').val();
            if(name.trim().length>0){
                $.ajax({
                    type:"post",
                    url:"/updateName?prototypeId="+id+"&prototypeName="+name,
                    async:false,
                    success:function(){
                        location.reload();
                    }
                });
            }else
                alert("Название не должно быть пустым")
        }
        function openDialog(id,name){
            $('#prototypeid').val(id);
            $('#prototypename').val(name);
            $('#loaddialog').dialog('open');
        }
        function setLink(link){

        }
    </script>
</head>
<body>
<div id="loaddialog" style="background-color: #e9eae4" title="Изменить название">
    <input type="text" class="formControl" placeholder="Name" id="prototypename">
    <input type="hidden" id="prototypeid">
    <input type="button" onclick="editName()" value="ok">
</div>
<div id="parent">
    <div id="toolbar" style="box-shadow: 0 0 10px rgba(0,0,0,0.5);">
        <div id="logo">
            <img src="/resources/images/logo-white.png" width="35px" height="35px"> Prototyper </div>
        <div id="userinfo">
            <a href="/logged">Редактор</a>
            <c:if test="${provider eq 'FACEBOOK'}"><i class="fa fa-facebook"></i></c:if>
            <c:if test="${provider eq 'GOOGLE'}"><i class="fa fa-google"></i></c:if>
            <c:if test="${provider eq 'VKONTAKTE'}"><i class="fa fa-vk"></i></c:if>
            &nbsp;${userinfo}&nbsp;<a href="/logout">logout</a></div>
    </div>
    <div id="cabinet">
        <div style="width: 100%;text-align: center"><h2>Your user id: ${myid}</h2></div>
        <h3>Your prototypes:</h3>
        <table width='100%' style='border-spacing: 8px;'>
            <tr>
                <td>
                    <div style="width: 30px;float:left;">Id.</div>
                    <div style="width: 90px;float:left;">Изменение</div>
                    <div style="float:left;">Название</div>
                    <div style="cursor: pointer;float: right;padding-right: 10px">Операции</div>
                </td>
                <td>Поделиться</td>
            </tr>
            <tr><td><hr></td><td><hr></td></tr>
            <c:forEach items="${protorypes}" var="element" varStatus="loop">
                <tr>
                    <td  class="prototd">
                        <div style="width: 30px;float:left;"><b>${loop.index+1}.</b></div>
                        <div style="width: 90px;float:left;">${element.updateDate}</div>
                        <div style="float:left;">${element.name}</div>
                        <div style="cursor: pointer;float: right;padding-right: 10px">
                            <img src="/resources/images/icons/delete.png" onclick="deletePrototype(${element.id})" width="15px" height="15px">
                        </div>
                        <div style="cursor: pointer;float: right;padding-right: 10px">
                            <img src="/resources/images/icons/edit.png" onclick="openDialog(${element.id},'${element.name}')" width="15px" height="15px">
                        </div>
                    </td>
                    <td width="230px">
                        <div style="position: relative;">
                            <input class="formControl" type="text" id="inp${loop.index}" placeholder="id">
                            <div style="cursor: pointer;position: absolute;bottom: 0;left:200px;">
                                <img src="/resources/images/icons/send.png" onclick="shareTo('${element.link}','inp${loop.index}')" width="20px" height="20px">
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <h3>Prototypes shared to you:</h3>
    <table class="proto" width='100%' style='border-spacing: 8px;'>
        <tr>
            <td><div style="width: 30px;float:left;">Id.</div>
                <div style="width: 90px;float:left;">Изменение</div>
                <div style="float:left;">Название</div>
                <div style="cursor: pointer;float: right;padding-right: 10px">Операции</div>
            </td>
        </tr>
        <tr><td><hr></td></tr>
        <c:forEach items="${shares}" var="element" varStatus="loop">
            <tr>
                <td  class="prototd">
                    <div style="width: 30px;float:left;"><b>${loop.index+1}.</b></div>
                    <div style="width: 90px;float:left;">${element.updateDate}</div>
                    <div style="float:left;">${element.name}</div>
                    <div style="cursor: pointer;float: right;padding-right: 10px">
                        <img src="/resources/images/icons/delete.png" onclick="deleteShare(${element.id})" width="15px" height="15px">
                    </div>
                    <div style="float: right;padding-right: 10px">
                        <a href="/logged?link=${element.link}"><img src="/resources/images/icons/apply.png" width="15px" height="15px"></a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <div id="footer">
        Prototyper @ 2016<br>
        by Hadzhiev Bogdan<br>
    </div>
</div>
</body>
</html>
