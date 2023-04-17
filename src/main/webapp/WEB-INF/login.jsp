<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 14/04/2023
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <button type="submit">Login</button>
</form>
<a href="register">register</a>
<c:if test="${isError == true}">
    <p>Bad credentials.</p>
</c:if>

</body>
</html>