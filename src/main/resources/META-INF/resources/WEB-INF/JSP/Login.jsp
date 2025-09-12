<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h3 class="text-center mb-4">🔑 Login</h3>

                    <!-- Error Message -->
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">Invalid username or password!</div>
                    </c:if>

                    <!-- Logout Message -->
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">You have been logged out successfully.</div>
                    </c:if>

                    <!-- ✅ Plain HTML Form (works with Spring Security) -->
                    <form action="${pageContext.request.contextPath}/doLogin" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" name="username" id="username" class="form-control" required />
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" name="password" id="password" class="form-control" required />
                        </div>

                        <button type="submit" class="btn btn-dark w-100">Login</button>
                    </form>

                    <p class="mt-3 text-center">
                        Don’t have an account? <a href="/register">Register</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
