<%@ include file="../common/header-admin.jsp" %>

<div class="container mt-5">
    <h2>Ajouter un Utilisateur</h2>
    <form action="${pageContext.request.contextPath}/admin/addUser" method="post">
        <div class="form-group mt-3">
            <label for="username">Nom d'utilisateur</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group mt-3">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group mt-3">
            <label for="password">Mot de passe</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group mt-3">
            <label for="role">Rôle</label>
            <select class="form-control" id="role" name="role" required>
                <option value="user">Utilisateur</option>
                <option value="admin">Administrateur</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-4">Ajouter</button>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>
