<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.model.Post" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: warie
  Date: 12/04/2023
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<html>
<head>
    <title>indexPoste</title>
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
                  <p>Catégorie: ${post.category.name}</p>
                    <p>${post.id}</p>
                    <a href="delete?id=${post.id}">delete</a>
                    <a href="update?id=${post.id}">update</a>
                    <a href="#" class="card-link">détail</a>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="card" style="width: 18rem;">
        <ul class="list-group list-group-flush">
<c:forEach items="${categories}" var="category">
            <li class="list-group-item">${category.name} <a href="deletec?id=${category.id}">delete</a><a href ="category?id=${category.id}">List Posts</a>  <a href="updateC?id=${category.id}">update</a></li>

</c:forEach>
    </ul>
</div>
</div>

<%--
<h1 class ="container" style="text-align: center;"> voici l'index de tout mes Posts à l'ancienne</h1>
<div class="d-flex container ">


    Post[] posts = new Post[0];
    for (Post p : posts) {
%>
    <div>
        <div class="card m-4" style="width: 18rem;">
            <div class="card-body ">
                <h5 class="card-title"><%= p.getTitle()%></h5>
                <h6 class="card-subtitle mb-2 text-body-secondary"><%= p.getAuthor()%></h6>
                <p class="card-text"><%= p.getContent()%></p>
                <p> <%= p.getCreateAt()%></p>
                <a href="#" class="card-link">Another link</a>
            </div>
        </div>
    </div>
<% }--%>
    <a href="${pageContext.request.contextPath}/secured/dashboard">dasbord</a>
    <a href="${pageContext.request.contextPath}/secured/logout">Close Session</a>
</div>
</body>
</html>
