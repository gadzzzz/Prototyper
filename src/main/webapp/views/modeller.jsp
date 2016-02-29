<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Site modeler</title>

    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/font-awesome.css">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/bootstrap-social.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/themes/ui-lightness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/Modeler.css">
    <script type="text/javascript" src="/resources/Modeler.js"></script>
    <script type="text/javascript" src="/resources/libs/map.js"></script>
    <script type="text/javascript" src="/resources/libs/html2canvas.js"></script>
    <script type="text/javascript" src="/resources/libs/jquery.plugin.html2canvas.js"></script>
    <script type="text/javascript" src="/resources/htmlpatterns/SimpleElements.js"></script>
    <script>
        function changeNameToSave(){
            if($('#savediv').css('display')=='inline')
                $('#savediv').css('display','none');
            else
                $('#savediv').css('display','inline');
            $('#loaddiv').css('display','none');
        }
        function changeNameToLoad(){
            if($('#loaddiv').css('display')=='inline')
                $('#loaddiv').css('display','none');
            else
                $('#loaddiv').css('display','inline');
            $('#savediv').css('display','none');
        }
    </script>
    <style>
        body{
            background-color: #e9eae4;
        }
    </style>
</head>
<body onload="addPreviewPage()">
<div id="parent">
    <div id="toolbar">
        <div id="logo">
            <img src="/resources/images/logo-white.png" width="35px" height="35px"> Prototyper
        </div>
        &nbsp;<span title="Сохранить шаблон" onclick="changeNameToSave()" style='cursor: pointer;'><img class="quickbutton" src='/resources/images/icons/save.png'></span>
        <span style="position: relative;top: -16px; display: none" id="savediv">
            <input class="formControl" type="text" placeholder="name" id="savename">
            <span title="Сохранить" onclick="save()" style='cursor: pointer;position: relative;top: 12px;'><img class="quickbutton" src='/resources/images/icons/apply.png'></span>
            <span title="Отменить" onclick="changeNameToSave()" style='cursor: pointer;position: relative;top: 12px;'><img class="quickbutton" src='/resources/images/icons/delete.png'></span>
            </span>
        <span title="Загрузить шаблон" onclick="changeNameToLoad()" style='cursor: pointer'><img class="quickbutton" src='/resources/images/icons/load.png'></span>
        <span style="position: relative;top: -16px;display: none" id="loaddiv">
            <select class="formControl">
            <option>Пункт 1</option>
            <option>Пункт 2</option>
            </select>
            <span title="Загрузить" onclick="load(9)" style='cursor: pointer;position: relative;top: 12px;'><img class="quickbutton" src='/resources/images/icons/apply.png'></span>
            <span title="Отменить" onclick="changeNameToLoad()" style='cursor: pointer;position: relative;top: 12px;'><img class="quickbutton" src='/resources/images/icons/delete.png'></span>
        </span>
        <div id="userinfo">
            <c:if test="${provider eq 'FACEBOOK'}"><i class="fa fa-facebook"></i></c:if>
            <c:if test="${provider eq 'GOOGLE'}"><i class="fa fa-google"></i></c:if>
            <c:if test="${provider eq 'VKONTAKTE'}"><i class="fa fa-vk"></i></c:if>
            &nbsp;${userinfo}&nbsp;<a href="/logout">logout</a></div>
    </div>
    <div id="elements">
        <table style="border-spacing: 4px;">
            <tr>
                <td onclick="createTag('button')">
                    <div class="elementButton elementStyle" >кнопка</div>
                </td>
                <td onclick="createTag('label')">
                    <div class="elementLabel elementStyle" >текст</div>
                </td>
            </tr>
            <tr>
                <td colspan="2" onclick="createTag('field')">
                    <div class="elementField elementStyle" >поле</div>
                </td>
            </tr>
            <tr>
                <td  onclick="createTag('image')">
                    <div class="elementImage elementStyle"></div>
                </td>
                <td  onclick="createTag('link')">
                    <div class="elementLink elementStyle">ссылка</div>
                </td>
            </tr>
            <tr>
                <td colspan="2"  onclick="createTag('textblock')">
                    <div class="elementLabel elementStyle">..блок текста..<br>..блок текста..<br>..блок текста..</div>
                </td>
            </tr>
            <tr>
                <td  onclick="createTag('horizontalhr')">
                    <div class="elementHr elementStyle"><hr width="70"></div>
                </td>
                <td onclick="createTag('checkbox')">
                    <div class="elementCheckBox elementStyle" ><input type="checkbox" onclick="return false" checked>флаг</div>
                </td>
            </tr>
            <tr>
                <td colspan="2" onclick="createTag('textarea')">
                    <div class="elementTextArea elementStyle" >многострочное поле<br>многострочное поле<br>многострочное поле</div>
                </td>
            </tr>
            <tr>
                <td  onclick="createTag('radiobutton')">
                    <div class="elementRadioButton elementStyle"><input type="radio" onclick="return false">радио</div>
                </td>
                <td onclick="createTag('select')">
                    <div class="elementSelect elementStyle" ><select disabled><option>список</option></select></div>
                </td>
            </tr>
        </table>
    </div>
    <div class="saveConent">
        <div id="content"></div>
    </div>
    <div id="pages">
        <div><span title="Создать страницу" style='cursor: pointer' onclick="addPreviewPage()"><img class="quickbutton" src='/resources/images/icons/create.png'></span></div>
        <div><span title="Удалить страницу" style='cursor: pointer' onclick="removePreviewPage()"><img class="quickbutton" src='/resources/images/icons/delete.png'></span></div>
    </div>
    <div id="menu">
        <ul style="">
            <li>Операции:
                <div>
                    <span title="Копировать элемент" style='cursor: pointer' onclick="copyElement()"><img class="quickbutton" src='/resources/images/icons/copy.png'></span>
                    <span title="Удалить элемент" style='cursor: pointer' onclick="removeElement()"><img class="quickbutton" src='/resources/images/icons/delete.png'></span>
                </div>
            </li>
            <li>
                Свойства:<input class="formControl" id="elementId" type="text" size="5" readonly>
            </li>
            <li>
                текст:<input class="formControl" id="elementText" type="text">
            </li>
            <li>
                шрифт:<input class="formControl" id="elementTextSize" type="text">
            </li>
            <li>
                ширина:<input class="formControl" id="elementWidth" type="text">
            </li>
            <li>
                высота:<input class="formControl" id="elementHeight" type="text">
            </li>
            <li>
                х:<input class="formControl" id="elementX" type="text">
            </li>
            <li>
                y:<input class="formControl" id="elementY" type="text">
            </li>
            <li>
                цвет:<input class="formControl" id="elementColor" type="text">
            </li>
            <li>
                <span title="Применить изменения" onclick="changeState()" style='cursor: pointer'><img class="quickbutton" src='/resources/images/icons/apply.png'></span>
            </li>
        </ul>
        <hr>
        <div id="patterns">
            <table >
                <tr>
                    <td onclick="createCompoundTag('loginForm')">
                        <div class="elementStyle" >форма логина</div>
                    </td>
                    <td onclick="createTag('label')">
                        <div class="elementLabel elementStyle" >текст</div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" onclick="createTag('field')">
                        <div class="elementField elementStyle" >поле</div>
                    </td>
                </tr>
                <tr>
                    <td  onclick="createTag('image')">
                        <div class="elementImage elementStyle"></div>
                    </td>
                    <td  onclick="createTag('link')">
                        <div class="elementLink elementStyle">ссылка</div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"  onclick="createTag('textblock')">
                        <div class="elementLabel elementStyle">...<br>блок текста<br>...</div>
                    </td>
                </tr>
                <tr>
                    <td  onclick="createTag('horizontalhr')">
                        <div class="elementHr elementStyle"><hr width="70"></div>
                    </td>
                    <td onclick="createTag('checkbox')">
                        <div class="elementCheckBox elementStyle" ><input type="checkbox" onclick="return false" checked>флаг</div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" onclick="createTag('textarea')">
                        <div class="elementTextArea elementStyle" >...<br>многострочное поле<br>...</div>
                    </td>
                </tr>
                <tr>
                    <td  onclick="createTag('radiobutton')">
                        <div class="elementRadioButton elementStyle"><input type="radio" onclick="return false">радио</div>
                    </td>
                    <td onclick="createTag('select')">
                        <div class="elementSelect elementStyle" ><select disabled><option>список</option></select></div>
                    </td>
                </tr>
                <tr>
                    <td  onclick="createTag('radiobutton')">
                        <div class="elementRadioButton elementStyle"><input type="radio" onclick="return false">радио</div>
                    </td>
                    <td onclick="createTag('select')">
                        <div class="elementSelect elementStyle" ><select disabled><option>список</option></select></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>