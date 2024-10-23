<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/header-guest.jsp" %>
<style> footer {
        position: fixed;
        bottom: 0;
        width: 100%;
        background-color: #343a40;
        color: white;
        text-align: center;
        padding: 10px 0;
    }</style>
<!-- Bootstrap CSS -->
<link rel="stylesheet" 
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
      crossorigin="anonymous">

<!-- Contenu principal de la page -->
<div class="container vh-100 d-flex align-items-center">
    <div class="row w-100">
        <!-- Formulaire de connexion à gauche -->
        <div class="col-md-6 d-flex flex-column justify-content-center">
            <h2 class="text-center mb-4">Login</h2>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username">User name</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group mt-3">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block mt-4">Login</button>
                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/views/register.jsp">Create account</a>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mt-3">${errorMessage}</div>
                </c:if>
            </form>
        </div>

        <!-- Image à droite -->
        <div class="col-md-6 d-flex justify-content-center align-items-center">
            <img src="${pageContext.request.contextPath}/assets/images/login-image.jpg" 
                 alt="Image de connexion"
                 class="img-fluid" style="max-height: 500px;">
        </div>
    </div>

</div>
<%@ include file="../common/footer.jsp" %>
