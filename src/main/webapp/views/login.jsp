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
</head>
<body>
<!-- Social Sign In Buttons -->
<div class="panel panel-default">
    <div class="panel-body">
        <div class="row social-button-row">
            <div class="col-lg-4">
                <a href="${pageContext.request.contextPath}/auth/facebook">facebook</a>
                <a href="${pageContext.request.contextPath}/auth/google?scope=profile">Google+</a>
                <a href="${pageContext.request.contextPath}/auth/vkontakte">Vkontakte</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
