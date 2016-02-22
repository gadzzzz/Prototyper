<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Site modeler</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/themes/ui-lightness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/Modeler.css">
    <script type="text/javascript" src="/resources/Modeler.js"></script>
    <script type="text/javascript" src="/resources/libs/map.js"></script>
    <script type="text/javascript" src="/resources/libs/html2canvas.js"></script>
    <script type="text/javascript" src="/resources/libs/jquery.plugin.html2canvas.js"></script>
    <script type="text/javascript" src="/resources/htmlpatterns/SimpleElements.js"></script>
</head>
<body onload="addPreviewPage()">
<div id="parent">
    <div id="toolbar">
        <div id="logo">Modeler</div>
    </div>
    <div id="elements">
        <table >
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
        </table>
    </div>
    <div class="saveConent">
        <div id="content"></div>
    </div>
    <div id="pages">
        <div><span title="Создать страницу" onclick="addPreviewPage()"><img class="quickbutton" src='/resources/images/icons/create.png'></span></div>
        <div><span title="Удалить страницу" onclick="removePreviewPage()"><img class="quickbutton" src='/resources/images/icons/delete.png'></span></div>
    </div>
    <div id="menu">
        <ul style="">
            <li>Операции:
                <div>
                    <span title="Копировать элемент" onclick="copyElement()"><img class="quickbutton" src='/resources/images/icons/copy.png'></span>
                    <span title="Удалить элемент" onclick="removeElement()"><img class="quickbutton" src='/resources/images/icons/delete.png'></span>
                </div>
            </li>
            <li>
                Свойства:<input id="elementId" type="text" size="5" readonly>
            </li>
            <li>
                текст:<input id="elementText" type="text">
            </li>
            <li>
                шрифт:<input id="elementTextSize" type="text">
            </li>
            <li>
                ширина:<input id="elementWidth" type="text">
            </li>
            <li>
                высота:<input id="elementHeight" type="text">
            </li>
            <li>
                х:<input id="elementX" type="text">
            </li>
            <li>
                y:<input id="elementY" type="text">
            </li>
            <li>
                цвет:<input id="elementColor" type="text">
            </li>
            <li>
                <button onclick="changeState()">применить</button>
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