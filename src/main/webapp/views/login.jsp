<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
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
        #outer {
            width: 100%;
            text-align: center;
        }
        #inner {
            display: inline-block;
            margin-top: 100px;
        }
    </style>
</head>
<body>
<div id="outer">
<div id="inner" class="form-control" style="width: 370px;height: 200px; padding-right: 25px">
    <h2>Login</h2>
    <a class="btn btn-block btn-social btn-facebook" style="margin: 5px" href="${pageContext.request.contextPath}/auth/facebook"><span class="fa fa-facebook"></span>Facebook</a>
    <a class="btn btn-block btn-social btn-google" style="margin: 5px" href="${pageContext.request.contextPath}/auth/google?scope=profile"><span class="fa fa-google"></span>Google+</a>
    <a class="btn btn-block btn-social btn-vk" style="margin: 5px" href="${pageContext.request.contextPath}/auth/vkontakte"><span class="fa fa-vk"></span>Vkontakte</a>
</div>
</div>
</body>
</html>
