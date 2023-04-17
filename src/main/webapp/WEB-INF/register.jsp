<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 17/04/2023
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post">
    <input type="text" name="usernamere">
    <input type="password" name="passwordre">
    <button type="submit">register</button>
</form>

</body>
</html>
