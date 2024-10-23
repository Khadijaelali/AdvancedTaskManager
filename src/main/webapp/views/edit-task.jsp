<%@ include file="../common/header-user.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Bootstrap CSS -->
<link rel="stylesheet" 
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
      crossorigin="anonymous">

<div class="container mt-5">
    <h2>Modifier la Tâche</h2>
    <form action="${pageContext.request.contextPath}/editTask" method="post">
        <input type="hidden" name="id" value="${task.id}">
        <input type="hidden" name="userId" value="${task.userId}">
        <div class="form-group">
            <label for="title">Titre</label>
            <input type="text" class="form-control" id="title" name="title" value="${task.title}" required>
        </div>
        <div class="form-group mt-3">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required>${task.description}</textarea>
        </div>
        <div class="form-group mt-3">
            <label for="status">Statut</label>
            <select class="form-control" id="status" name="status">
                <option value="En cours" ${task.status == 'En cours' ? 'selected' : ''}>En cours</option>
                <option value="Terminé" ${task.status == 'Terminé' ? 'selected' : ''}>Terminé</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Mettre à jour</button>
    </form>
</div>
<%@ include file="../common/footer.jsp" %>
