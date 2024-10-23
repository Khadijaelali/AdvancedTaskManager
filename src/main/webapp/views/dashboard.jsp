<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header-user.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Bootstrap CSS -->
<link rel="stylesheet" 
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
      crossorigin="anonymous">

<style>
    .hero {
        height: 80vh;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
    }
    .hero img {
        max-width: 300px;
        margin-top: 20px;
    }
    footer {
        position: fixed;
        bottom: 0;
        width: 100%;
        background-color: #343a40;
        color: white;
        text-align: center;
        padding: 10px 0;
    }
</style>

<div class="container mt-5">
    <h2 class="mb-4">My Tasks</h2>

    <a href="${pageContext.request.contextPath}/addTask" class="btn btn-primary mb-3">Add new Task</a>

    <table class="table table-bordered table-hover">
        <thead class="thead-light">
            <tr>
                <!-- <th>ID</th> -->
                <th>Title</th>
                <th>Description</th>
                <th>State</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Boucle JSTL pour afficher les tâches -->
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <!-- <td>${task.id}</td> -->
                    <td>${task.title}</td>
                    <td>${task.description}</td>
                    <td>${task.status}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/editTask?id=${task.id}" 
                           class="btn btn-warning btn-sm">Modifier</a>
                        <a href="${pageContext.request.contextPath}/deleteTask?id=${task.id}" 
                           class="btn btn-danger btn-sm">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../common/footer.jsp" %>
