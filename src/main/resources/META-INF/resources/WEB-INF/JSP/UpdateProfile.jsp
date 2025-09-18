<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Profile | ToDo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { background: #f5f7fa; }
        .card { border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        .hidden-section { display: none; }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4">

                <!-- Profile Section -->
                <div id="profileSection"
                     <c:if test="${passwordSection}">style="display:none;"</c:if>>
                    <h3 class="mb-3 text-center">Manage Profile</h3>
                    <form:form method="post" modelAttribute="profile" action="/update-profile">
                        <!-- Username (display only + hidden for binding) -->
                        <div class="mb-3">
                            <input type="hidden" name="username" value="${profile.username}" />
                            <input type="text" class="form-control" value="${profile.username}" disabled />
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <form:label path="email" cssClass="form-label">Email</form:label>
                            <form:input path="email" cssClass="form-control"/>
                            <form:errors path="email" cssClass="text-danger small"/>
                        </div>

                        <!-- Actions -->
                        <div class="d-flex justify-content-between">
                            <a href="/todos" class="btn btn-secondary">⬅ Go Back</a>
                            <button type="submit" class="btn btn-primary">Update Profile</button>
                        </div>
                    </form:form>

                    <hr>

                    <!-- Change Password Trigger -->
                    <div class="text-center">
                        <button type="button" id="showPasswordBtn" class="btn btn-outline-secondary mt-2">
                            <i class="bi bi-shield-lock"></i> Change Password
                        </button>
                    </div>
                </div>

                <!-- Password Section -->
                <div id="passwordSection"
                     <c:if test="${!passwordSection}">style="display:none;"</c:if>>
                    <h3 class="mb-3 text-center">Manage Password</h3>
                    <form:form method="post" modelAttribute="password" action="/update-password">
                        <!-- Current Password -->
                        <div class="mb-3">
                            <form:label path="currentPassword" cssClass="form-label">Current Password</form:label>
                            <form:password path="currentPassword" cssClass="form-control"/>
                            <form:errors path="currentPassword" cssClass="text-danger small"/>
                        </div>

                        <!-- New Password -->
                        <div class="mb-3">
                            <form:label path="newPassword" cssClass="form-label">New Password</form:label>
                            <form:password path="newPassword" cssClass="form-control"/>
                            <form:errors path="newPassword" cssClass="text-danger small"/>
                        </div>

                        <!-- Confirm Password -->
                        <div class="mb-3">
                            <form:label path="confirmPassword" cssClass="form-label">Confirm Password</form:label>
                            <form:password path="confirmPassword" cssClass="form-control"/>
                            <form:errors path="confirmPassword" cssClass="text-danger small"/>
                        </div>

                        <!-- Actions -->
                        <div class="d-flex justify-content-between">
                            <button type="button" id="backToProfileBtn" class="btn btn-secondary">⬅ Back</button>
                            <button type="submit" class="btn btn-success">Update Password</button>
                        </div>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Toggle between profile and password forms (client-side)
    document.getElementById("showPasswordBtn").addEventListener("click", function () {
        document.getElementById("profileSection").style.display = "none";
        document.getElementById("passwordSection").style.display = "block";
    });

    document.getElementById("backToProfileBtn").addEventListener("click", function () {
        document.getElementById("passwordSection").style.display = "none";
        document.getElementById("profileSection").style.display = "block";
    });
</script>
</body>
</html>
