<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 18/04/2023
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit post</title>
</head>
<body>
<h1>Edit post</h1>
<form action="${pageContext.request.contextPath}/secured/update" method="post">
    <input type="hidden" name="id" value="${post.id}" />
    <label>Title:</label>
    <input type="text" name="title" value="${post.title}" /><br>
    <label>Author:</label>
    <input type="text" name="author" value="${post.author}" /><br>
    <label>Content:</label>
    <textarea name="content">${post.content}</textarea><br>
    <button type="submit">modifier</button>
</form>
</body>
</html>