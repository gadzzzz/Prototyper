<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prototyper</title>
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/bootstrap.css" crossorigin="anonymous">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/font-awesome.css">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/docs.css">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/bootstrap-social.css">
    <style>
        body {
            background-image: url('/resources/images/bg.jpg');
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;

        }
        .shadowtext {
            color:white;
            text-shadow: 1px 1px 2px black, 0 0 1em black;
            font-size: 2em;
        }
        #outer {
            width: 100%;
            text-align: center;
        }
        #inner {
            display: inline-block;
            margin-top: 20px;
        }
        .footer{
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            text-align: center;
            padding: 20px;
            position: absolute;
            bottom: 0;
            background-color: rgba(239, 240, 237, 0.9);
            width:100%;
            height: 100px;
        }
    </style>
</head>
<body>
<div id="outer">
    <div style="margin-top: 50px">
    <img src="/resources/images/logo.png" width="10%" height="10%"><h2><b class="shadowtext">PROTOTYPER</b></h2>
    </div>
<div id="inner" style="width: 370px;height: 200px;">
    <h2 class="shadowtext">Sign In With</h2>
    <%--<a class="btn btn-block btn-social btn-facebook" style="margin: 5px" href="${pageContext.request.contextPath}/auth/facebook"><span class="fa fa-facebook"></span>Facebook</a>--%>
    <%--<a class="btn btn-block btn-social btn-google" style="margin: 5px" href="${pageContext.request.contextPath}/auth/google?scope=profile"><span class="fa fa-google"></span>Google+</a>--%>
    <%--<a class="btn btn-block btn-social btn-vk" style="margin: 5px" href="${pageContext.request.contextPath}/auth/vkontakte"><span class="fa fa-vk"></span>Vkontakte</a>--%>

    <a title="Facebook" href="${pageContext.request.contextPath}/auth/facebook" class="btn btn-social-icon btn-lg btn-facebook" style="margin: 5px" ><i class="fa fa-facebook"></i> </a>
    <a title="Google+" href="${pageContext.request.contextPath}/auth/google?scope=profile" class="btn btn-social-icon btn-lg btn-google" style="margin: 5px"><i class="fa fa-google"></i> </a>
    <a title="Vkontakte" href="${pageContext.request.contextPath}/auth/vkontakte" class="btn btn-social-icon btn-lg btn-vk" style="margin: 5px"><i class="fa fa-vk"></i> </a>

</div>
</div>
<div class="footer">
    Prototyper @ 2016<br>
    by Hadzhiev Bogdan<br>
</div>
</body>
</html>
