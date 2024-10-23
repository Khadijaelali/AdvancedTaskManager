<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/header-admin.jsp" %>
<style>
.table {
    border-radius: 10px;
    overflow: hidden;
}

.table-hover tbody tr:hover {
    background-color: #f1f1f1;
    transition: background-color 0.3s;
}

.card {
    border-radius: 15px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s;
}

.card:hover {
    transform: scale(1.05);
}

.btn {
    transition: background-color 0.3s, transform 0.3s;
}

.btn:hover {
    transform: scale(1.1);
}

</style>
<div class="container mt-5">
    <h1 class="text-center mb-4">Admin Dashboard</h1>
    <p class="text-center">Welcome to admin Interface </p>

    <!-- Statistiques Section -->
    <div class="row text-center mb-5">
        <div class="col-md-4">
            <div class="card bg-dark text-white shadow-lg">
                <div class="card-body">
                    <h5 class="card-title">Total Users</h5>
                    <p class="card-text display-4">${totalUsers}</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card bg-info text-white shadow-lg">
                <div class="card-body">
                    <h5 class="card-title">Tasks In Progress</h5>
                    <p class="card-text display-4">${inProgressTasks}</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card bg-success text-white shadow-lg">
                <div class="card-body">
                    <h5 class="card-title">Tasks Completed</h5>
                    <p class="card-text display-4">${completedTasks}</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Bouton Ajouter Utilisateur -->
    <div class="d-flex justify-content-between mb-3">
        <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Rechercher un utilisateur" aria-label="Search" id="searchInput">
            <button class="btn btn-outline-success" type="button" onclick="searchTable()">Search</button>
        </form>
        <a href="${pageContext.request.contextPath}/views/add-user.jsp" class="btn btn-primary">Add User</a>
    </div>

    <!-- Table des Utilisateurs -->
    <div class="table-responsive">
        <table class="table table-hover align-middle shadow-sm" id="userTable">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>User name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td>
                            <div class="d-flex">
                                <a href="${pageContext.request.contextPath}/admin/editUser?id=${user.id}" class="btn btn-warning btn-sm me-2">Edit</a>
                                <a href="${pageContext.request.contextPath}/admin/deleteUser?id=${user.id}" class="btn btn-danger btn-sm">Deletee</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    function searchTable() {
        const input = document.getElementById("searchInput").value.toUpperCase();
        const table = document.getElementById("userTable");
        const rows = table.getElementsByTagName("tr");

        for (let i = 1; i < rows.length; i++) {
            let cells = rows[i].getElementsByTagName("td");
            let match = false;

            for (let j = 0; j < cells.length; j++) {
                if (cells[j].innerText.toUpperCase().indexOf(input) > -1) {
                    match = true;
                    break;
                }
            }
            rows[i].style.display = match ? "" : "none";
        }
    }
</script>


<%@ include file="../common/footer.jsp" %>
