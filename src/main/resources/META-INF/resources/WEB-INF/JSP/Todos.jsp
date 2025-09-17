<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center" href="#">
            <i class="bi bi-card-checklist me-2" style="font-size: 1.5rem; color: white;"></i>
            Todo App
        </a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link active" href="/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="/add-todo">Add Todo</a></li>
            </ul>
        </div>

        <!-- Right side (flex aligned) -->
        <div class="d-flex align-items-center">
            <!-- Profile Icon -->
            <button class="btn btn-link p-0 border-0 me-3" data-bs-toggle="offcanvas" data-bs-target="#profileSidebar">
                <i class="bi bi-person-circle" style="font-size: 2rem; color: white;"></i>
            </button>

            <!-- Logout -->
            <form action="/logout" method="post" class="m-0">
                <button type="submit" class="btn btn-danger">Logout</button>
            </form>
        </div>
    </div>
</nav>

<!-- Greeting -->
<div class="container mt-3">
    <h4>Welcome, <strong>${profile.username}</strong> 👋</h4>
</div>

<!-- Content -->
<div class="container mt-4">
    <h2 class="mb-3">Your Todo List</h2>

    <!-- Add Todo Button -->
    <div class="mb-3">
        <a href="/add-todo" class="btn btn-success">+ Add Todo</a>
    </div>

    <!-- Todo Table -->
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Completed?</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate.toString()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${todo.done}">✅ Yes</c:when>
                            <c:otherwise>❌ No</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="update-todo?id=${todo.id}" class="btn btn-primary btn-sm">Update</a>
                        <a href="delete-todo?id=${todo.id}" class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Profile Sidebar (Bootstrap Offcanvas) -->
<div class="offcanvas offcanvas-end" tabindex="-1" id="profileSidebar">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title">Your Profile</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <p><strong>Username:</strong> ${profile.username}</p>
        <p><strong>Email:</strong> ${profile.email}</p>

        <hr>
        <a href="/update-profile" class="btn btn-primary w-100 mb-2">Update Profile</a>
        <form action="/logout" method="post">
            <button type="submit" class="btn btn-danger w-100">Logout</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
