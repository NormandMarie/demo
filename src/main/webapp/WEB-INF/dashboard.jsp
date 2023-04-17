<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 12/04/2023
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ma page</title>
</head>
<body>


<h1> Bonjour ${name} ${nom} </h1>
<p> ${MA_Cle}</p>
<a href="posts"> posts</a>
<a href="${pageContext.request.contextPath}/"> index</a>
<form action="${pageContext.request.contextPath}/secured/posts" method="post">
    <input type="text" name="title">
    <input type="text" name="author">
    <input type="text" name="content">
    <button type="submit">new post</button>
</form>
</body>
</html>
