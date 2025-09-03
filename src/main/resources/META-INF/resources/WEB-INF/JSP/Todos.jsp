<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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
                <li class="nav-item"><a class="nav-link" href="/add-todo">Add Todo</a></li>
            </ul>
        </div>
    </div>
</nav>



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
                    <th>Username</th>
                    <th>Target Date</th>
                    <th>Completed?</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="todo" items="${todos}">
                    <tr>
                        <td>${todo.description}</td>
                        <td>${todo.username}</td>
                       <td>${todo.targetDate.toString()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${todo.done}">
                                    ✅ Yes
                                </c:when>
                                <c:otherwise>
                                    ❌ No
                                </c:otherwise>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>