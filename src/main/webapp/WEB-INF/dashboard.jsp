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
<a href="posts"> posts</a>
<a href="${pageContext.request.contextPath}/"> index</a>
<form action="${pageContext.request.contextPath}/secured/posts" method="post">
    <input type="text" name="title" placeholder="titre">
    <input type="text" name="author" placeholder="auteur">
    <input type="text" name="content" placeholder="contenu">
    <input type="text" name="category" placeholder="catégories">
    <button type="submit">new post</button>
</form>
</body>
</html>
