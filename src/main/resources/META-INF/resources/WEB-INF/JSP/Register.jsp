<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

   <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
       <div class="container-fluid">
           <a class="navbar-brand d-flex align-items-center" href="#">
               <i class="bi bi-card-checklist me-2" style="font-size: 1.5rem; color: white;"></i>
               Todo App
           </a>
           <div class="collapse navbar-collapse">
               <ul class="navbar-nav me-auto">
                   <li class="nav-item"><a class="nav-link active" href="/">Home</a></li>
               </ul>
           </div>
       </div>
   </nav>


    <!-- Registration Form -->
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card shadow-lg">
                    <div class="card-body p-4">
                        <h3 class="text-center mb-4">Create Account</h3>

                        <form:form method="post" modelAttribute="userDto" action="/register">

                            <!-- Username -->
                            <div class="mb-3">
                                <form:label path="username" cssClass="form-label">Username</form:label>
                                <form:input path="username" cssClass="form-control"/>
                                <form:errors path="username" cssClass="text-danger small"/>
                            </div>

                            <!-- Email -->
                            <div class="mb-3">
                                <form:label path="email" cssClass="form-label">Email</form:label>
                                <form:input path="email" type="email" cssClass="form-control"/>
                                <form:errors path="email" cssClass="text-danger small"/>
                            </div>

                            <!-- Password -->
                            <div class="mb-3">
                                <form:label path="password" cssClass="form-label">Password</form:label>
                                <form:password path="password" cssClass="form-control"/>
                                <form:errors path="password" cssClass="text-danger small"/>
                            </div>

                            <!-- Submit Button -->
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Register</button>
                            </div>

                        </form:form>

                        <p class="text-center mt-3 mb-0">
                            Already have an account? <a href="/login">Login here</a>
                        </p>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
