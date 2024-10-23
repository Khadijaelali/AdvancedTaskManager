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


<div class="container vh-100 d-flex align-items-center">
    <div class="row w-100">
        <!-- Colonne du Formulaire d'Inscription -->
        <div class="col-md-6 d-flex flex-column justify-content-center">
            <h2 class="text-center mb-4">Create a new account</h2>
            <form action="${pageContext.request.contextPath}/register" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group mt-3">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="form-group mt-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <button type="submit" class="btn btn-success btn-block mt-4">SignUp</button>
                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/views/login.jsp"> You Already have account? login </a>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mt-3">${errorMessage}</div>
                </c:if>
            </form>
        </div>

        <!-- Colonne de l'Image -->
        <div class="col-md-6 d-flex justify-content-center align-items-center">
            <img src="${pageContext.request.contextPath}/assets/images/signup.jpg" 
                 alt="Inscription"
                 class="img-fluid" style="max-height: 400px;">
        </div>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>
