<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Gadzzzz
  Date: 11.02.2016
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/bootstrap.css" crossorigin="anonymous">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/font-awesome.css">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/assets/css/docs.css">
    <link rel="stylesheet" href="http://lipis.github.io/bootstrap-social/bootstrap-social.css">
    <link rel="stylesheet" href="/resources/Modeler.css">
</head>
<body>
<table width="200px" align="center" style="margin-top: 100px"><tr><td>
    <div class="row">
        <div class="col-sm-20 social-buttons">
            <a class="btn btn-block btn-social btn-facebook" style="margin: 5px" href="${pageContext.request.contextPath}/auth/facebook"><span class="fa fa-facebook"></span>Facebook</a>
            <a class="btn btn-block btn-social btn-google" style="margin: 5px" href="${pageContext.request.contextPath}/auth/google?scope=profile"><span class="fa fa-google"></span>Google+</a>
            <a class="btn btn-block btn-social btn-vk" style="margin: 5px" href="${pageContext.request.contextPath}/auth/vkontakte"><span class="fa fa-vk"></span>Vkontakte</a>
        </div>
    </div>
</td></tr></table>
</body>
</html>
