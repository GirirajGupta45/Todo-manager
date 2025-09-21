<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<html>
<head>
    <title>Todo App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
   <style>
          body {
              /* Full-screen background image */
              background: url('https://images.unsplash.com/photo-1620912189866-474843ba5c14?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D') no-repeat center center fixed;
              background-size: cover;
              min-height: 100vh;
              font-family: Arial, sans-serif;
          }

          .navbar .form-control {
              width: 280px;
          }

          .form-control, .btn.btn-outline-light.ms-2 {
              margin-top: 5px;
          }

          .sidebar-label {
              font-weight: 500;
              margin-bottom: 5px;
          }

          .sidebar-section {
              margin-bottom: 15px;
          }

          /* Optional: slightly dim background for readability */
          .content-container {
              background-color: rgba(255, 255, 255, 0.95);
              padding: 15px;
              border-radius: 8px;
          }
      </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid d-flex align-items-center">
        <a class="navbar-brand d-flex align-items-center me-3" href="#">
            <i class="bi bi-card-checklist me-2" style="font-size: 1.5rem; color: white;"></i>
            Todo App
        </a>

        <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex align-items-center">
            <li class="nav-item me-2"><a class="nav-link active" href="/">Home</a></li>
            <li class="nav-item me-2"><a class="nav-link" href="/add-todo">Add Todo</a></li>
        </ul>

        <!-- Search -->
        <form class="d-flex me-3" action="/todos" method="get">
            <input class="form-control" type="search" placeholder="Search Todos" name="searchKeyword" value="${searchKeyword}">
            <button class="btn btn-outline-light ms-2" type="submit"><i class="bi bi-search"></i></button>
        </form>

        <!-- Filter Icon -->
        <button class="btn btn-link p-0 border-0 me-3" data-bs-toggle="offcanvas" data-bs-target="#filterSidebar">
            <i class="bi bi-funnel" style="font-size: 1.8rem; color: white;"></i>
        </button>

        <!-- Profile Icon -->
        <button class="btn btn-link p-0 border-0 me-3" data-bs-toggle="offcanvas" data-bs-target="#profileSidebar">
            <i class="bi bi-person-circle" style="font-size: 2rem; color: white;"></i>
        </button>

        <!-- Logout -->
        <form action="/logout" method="post" class="m-0">
            <button type="submit" class="btn btn-danger">Logout</button>
        </form>
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
                <th>Category</th>
                <th>Due Date & Time</th>
                <th>Completed?</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.category}</td>
                    <td>${todo.formattedTargetDateTime}</td>
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

<!-- Profile Sidebar -->
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

<!-- Filter Sidebar -->
<div class="offcanvas offcanvas-end" tabindex="-1" id="filterSidebar">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title">Filter & Sort Todos</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
    </div>
    <div class="offcanvas-body">
        <form action="/filtered-todos" method="POST">
            <!-- Filter by Category -->
            <div class="sidebar-section">
                <label class="sidebar-label">Category</label>
                <select name="filterCategory" class="form-select">
                    <option value="">All</option>
                    <option value="Work" ${filterCategory=='Work'?'selected':''}>Work</option>
                    <option value="Personal" ${filterCategory=='Personal'?'selected':''}>Personal</option>
                    <option value="Others" ${filterCategory=='Others'?'selected':''}>Others</option>
                </select>
            </div>

            <!-- Filter by Status -->
            <div class="sidebar-section">
                <label class="sidebar-label">Status</label>
                <select name="filterStatus" class="form-select">
                    <option value="">All</option>
                    <option value="done" ${filterStatus=='done'?'selected':''}>Completed</option>
                    <option value="notDone" ${filterStatus=='notDone'?'selected':''}>Not Completed</option>
                </select>
            </div>

            <!-- Sort by Target Date -->
            <div class="sidebar-section">
                <label class="sidebar-label">Sort by Target Date</label>
                <select name="sortOrder" class="form-select">
                    <option value="">-- Select --</option>
                    <option value="asc" ${sortOrder=='asc'?'selected':''}>Latest Due First</option>
                    <option value="desc" ${sortOrder=='desc'?'selected':''}>Fartest Due First</option>
                </select>
            </div>

            <div class="d-flex justify-content-between mt-3">
                <button type="submit" class="btn btn-primary">Apply</button>
                <a href="/todos" class="btn btn-secondary">Clear All</a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
