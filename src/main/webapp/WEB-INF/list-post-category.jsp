<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 25/04/2023
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<html>
<head>
    <title>Category</title>
</head>
<body>
<c:if test="${empty posts}">
    <p>Aucun post trouvé.</p>
</c:if>
<h1 class ="container" style="text-align: center;"> voici l'index de tout mes Posts nouvelle version</h1>
<div class=" container " style="display: grid;grid-template-columns: repeat(3, 1fr);
grid-column-gap: 20px;
grid-row-gap: 20px; ">
    <jsp:useBean id="posts" scope="request" type="java.util.List"/>
    <c:forEach items="${posts}" var="post">
    <div>
        <div class="card m-4" style="width: 18rem;">
            <div class="card-body bg-dark-subtle">
                <h5 class="card-title">${post.title}</h5>
                <p> ${post.title}</p>
                <h6 class="card-subtitle mb-2 text-body-secondary">${post.author}</h6>
                <p class="card-text">${post.content}</p>
                <p>${post.createAt} </p>
                <p>Catégorie: ${category}</p>
                <p>${post.id}</p>
                <a href="update?id=${post.id}">update</a>
                <a href="#" class="card-link">détail</a>
            </div>
        </div>
    </div>
    </c:forEach>
        <a href="posts"> retour</a>
</body>
</html>
