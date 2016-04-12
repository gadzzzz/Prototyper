<%@ page contentType="text/html;charset=utf-8" language="java" %>
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
    <link rel="stylesheet" href="/resources/Modeler.css">
    <script type="text/javascript" src="/resources/Modeler.js"></script>
    <script type="text/javascript" src="/resources/libs/map.js"></script>
    <script type="text/javascript" src="/resources/libs/html2canvas.js"></script>
    <script type="text/javascript" src="/resources/libs/jquery.plugin.html2canvas.js"></script>
    <script type="text/javascript" src="/resources/htmlpatterns/SimpleElements.js"></script>
    <script type="text/javascript" src="/resources/htmlpatterns/ComplicatedElements.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script>
        $(function() {
            $("#loaddialog").dialog({
                autoOpen: false,
                height: 350,
                width: 400,
                resizable: false
            });

            $("#openload").click(function() {
                loadAll();
                $("#loaddialog").dialog("open");
            });
        });
        $(function(){
            var link = '${protolink}';
            if(link!=""){
                autoload(link);
            }
        });
        function changeNameToSave(){
            if($('#savediv').css('display')=='inline')
                $('#savediv').css('display','none');
            else
                $('#savediv').css('display','inline');
        }
    </script>
    <style>
        body{
            background-color: #e9eae4;
        }
    </style>
</head>
<body onload='addPreviewPage()'>
<div id="loaddialog" style="background-color: #e9eae4" title="Прототипы">
    <div id="prototypelist"></div>
</div>
<div id="parent">
    <div id="toolbar">
        <div id="logo">
            <img src="/resources/images/logo-white.png" width="35px" height="35px"> Prototyper
        </div>
        &nbsp;<span title="Новый шаблон" onclick="javascript:location.href='/logged'" style='cursor: pointer;'><img class="quickbutton" src='/resources/images/icons/new.png'></span>
        <span title="Сохранить шаблон" onclick="changeNameToSave()" style='cursor: pointer;'><img class="quickbutton" src='/resources/images/icons/save.png'></span>
        <span style="position: relative;top: -16px; display: none" id="savediv">
            <input class="formControl" type="text" placeholder="name" id="savename">
            <span title="Сохранить" onclick="save()" style='cursor: pointer;position: relative;top: 12px;'><img class="quickbutton" src='/resources/images/icons/apply.png'></span>
            <span title="Отменить" onclick="changeNameToSave()" style='cursor: pointer;position: relative;top: 12px;'><img class="quickbutton" src='/resources/images/icons/delete.png'></span>
        </span>
        <span title="Выбрать шаблон" id="openload" style='cursor: pointer;'><img class="quickbutton" src='/resources/images/icons/choose.png'></span>
        <span title="Экспорт в файл" id="export" onclick="toFile()" style='cursor: pointer;'><img class="quickbutton" src='/resources/images/icons/load.png'></span>
        <div id="userinfo">
            <a href="/cabinet">Кабинет</a>
            <c:if test="${provider eq 'FACEBOOK'}"><i class="fa fa-facebook"></i></c:if>
            <c:if test="${provider eq 'GOOGLE'}"><i class="fa fa-google"></i></c:if>
            <c:if test="${provider eq 'VKONTAKTE'}"><i class="fa fa-vk"></i></c:if>
            &nbsp;${userinfo}&nbsp;<a href="/logout">logout</a></div>
    </div>
    <div id="elements">
        <table style="border-spacing: 5px;margin: 10px">
            <tr>
                <td onclick="createTag('button')">
                    <div class="elementButton elementStyle" >кнопка${protolink}</div>
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
            <tr>
                <td colspan="2"  onclick="createTag('calendar')">
                    <div class="elementCalendar elementStyle"></div>
                </td>
            </tr>
            <tr>
                <td  onclick="createTag('verticalvr')">
                    <div class="elementVr elementStyle"><div style='border-left: solid 1px;min-height: 100%; width: 1px;margin-left: 30px'></div></div>
                </td>
                <td  onclick="createTag('vscroll')">
                    <div class="elementVScroll elementStyle"><div style='overflow-y: scroll;min-height: 100%;margin-right: 20px'></div></div>
                </td>
            </tr>
            <tr>
                <td colspan="2"  onclick="createTag('pager')">
                    <div class="elementPager elementStyle"></div>
                </td>
            </tr>
            <tr>
                <td  onclick="createTag('hscroll')">
                    <div class="elementHScroll elementStyle"><div style='overflow-x: scroll;min-width: 100%;margin-left: 15px'></div></div>
                </td>
                <td  onclick="createTag('border')">
                    <div class="elementBorder elementStyle"><div style='border: solid 1px;width:74px;height: 48px;'></div></div>
                </td>
            </tr>
        </table>
    </div>
    <div class="saveConent">
        <div id="content"></div>
    </div>
    <div id="pages">
        <div id="pagebtns" style="margin-left:5px">
        <div><span title="Создать страницу" style='cursor: pointer' onclick='addPreviewPage()'><img class="quickbutton" src='/resources/images/icons/create.png'></span></div>
        <div><span title="Удалить страницу" style='cursor: pointer' onclick="removePreviewPage()"><img class="quickbutton" src='/resources/images/icons/delete.png'></span></div>
        </div>
    </div>
    <div id="menu">
        <ul>
            <li>Операции:
                <div>
                    <span title="Копировать элемент" style='cursor: pointer' onclick="copyElement()"><img class="quickbutton" src='/resources/images/icons/copy.png'></span>
                    <span title="Удалить элемент" style='cursor: pointer' onclick="removeElement()"><img class="quickbutton" src='/resources/images/icons/delete.png'></span>
                </div>
            </li>
            <li>
                Свойства: <input class="formControl" id="elementId" type="text" size="5" readonly>
            </li>
            <li>
                Текст: <input class="formControl" id="elementText" type="text">
            </li>
            <li>
                Шрифт: <input class="formControl" id="elementTextSize" type="text">
            </li>
            <li>
                Ширина: <input class="formControl" id="elementWidth" type="text">
            </li>
            <li>
                Высота: <input class="formControl" id="elementHeight" type="text">
            </li>
            <li>
                Лево: <input class="formControl" id="elementX" type="text">
            </li>
            <li>
                Верх: <input class="formControl" id="elementY" type="text">
            </li>
            <li>
                Цвет: <input class="formControl" id="elementColor" type="text">
            </li>
            <li>
                <span title="Применить изменения" onclick="changeState()" style='cursor: pointer'><img class="quickbutton" src='/resources/images/icons/apply.png'></span>
            </li>
        </ul>
        <hr>
        <div id="patterns">
            <table style="border-spacing: 5px;margin: 10px">
                <tr>
                    <td onclick="createComplicatedTag('loginForm')">
                        <div class="elementStyle" >Форма логина</div>
                    </td>
                    <td onclick="createComplicatedTag('registerForm')">
                        <div class="elementStyle" >Форма регистрации</div>
                    </td>
                </tr>
                <tr>
                    <td onclick="createComplicatedTag('commentForm')">
                        <div class="elementStyle" >Форма добавления коментария</div>
                    </td>
                    <td onclick="createComplicatedTag('newsForm')">
                        <div class="elementStyle" >Форма добавления новости</div>
                    </td>
                </tr>
                <tr>
                    <td onclick="createComplicatedTag('oneColumnPrototype')">
                        <div class="elementStyle" >Одноколоночный прототип</div>
                    </td>
                    <td onclick="createComplicatedTag('twoColumnPrototype')">
                        <div class="elementStyle" >Двухколоночный прототип</div>
                    </td>
                </tr>
                <tr>
                    <td onclick="createComplicatedTag('threeColumnPrototype')">
                        <div class="elementStyle" >Трехколоночный прототип</div>
                    </td>
                    <td onclick="createComplicatedTag('menuElement')">
                        <div class="elementStyle" >Меню</div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>