<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 25/04/2023
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/secured/updateC" method="post">
<input type="hidden" name="id" value="${id}" />
<label>name:</label>
<input type="text" name="name" value="${category}" /><br>
<button type="submit">modifier</button>
    </form>
</body>
</html>
