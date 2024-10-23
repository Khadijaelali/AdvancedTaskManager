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
    <h2>Ajouter une Nouvelle Tâche</h2>
    <form action="${pageContext.request.contextPath}/addTask" method="post">
        <div class="form-group">
            <label>Titre de la tâche</label>
            <input type="text" name="title" class="form-control" required>
        </div>
        <div class="form-group mt-3">
            <label>Description</label>
            <textarea name="description" class="form-control" rows="3" required></textarea>
        </div>
        <div class="form-group mt-3">
            <label>Statut</label>
            <select name="status" class="form-control">
                <option value="En cours">En cours</option>
                <option value="Terminé">Terminé</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Ajouter</button>
    </form>
</div>
<%@ include file="../common/footer.jsp" %>
