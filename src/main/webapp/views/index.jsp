<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/header-guest.jsp" %>

<!-- Bootstrap CSS -->
<link rel="stylesheet" 
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
      crossorigin="anonymous">



<div class="container vh-100 d-flex align-items-center">
    <div class="row w-100">
        <!-- Colonne de Texte -->
        <div class="col-md-6 d-flex flex-column justify-content-center">
            <h1 class="display-4 fw-bold mb-4">Organize Your Tasks Efficiently</h1>
            <p class="lead">
                With Task Manager, simplify your daily management by creating, tracking, and completing your tasks quickly. Boost your productivity with a clear and intuitive interface.
            </p>
            <ul class="list-unstyled mt-4">
                <li><i class="bi bi-check-circle-fill text-success me-2"></i> Easy Task Tracking</li>
                <li><i class="bi bi-check-circle-fill text-success me-2"></i> Intuitive and Responsive Interface</li>
                <li><i class="bi bi-check-circle-fill text-success me-2"></i> Real-Time Priority Management</li>
            </ul>
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/views/register.jsp" class="btn btn-primary btn-lg me-2">SignUp</a>
                <a href="${pageContext.request.contextPath}/views/login.jsp" class="btn btn-outline-secondary btn-lg">Login</a>
            </div>
        </div>

        <!-- Colonne de l'Image -->
        <div class="col-md-6 d-flex justify-content-center align-items-center">
            <img src="${pageContext.request.contextPath}/assets/images/task-manager.png" alt="Task Manager"
                 class="img-fluid" style="max-height: 400px;">
        </div>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>