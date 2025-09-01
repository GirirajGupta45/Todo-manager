<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Todo</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 70vh;
    }

    .todo-form {
        background-color: #fff;
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 0 6px 20px rgba(0,0,0,0.15);
        max-width: 450px;
        width: 100%;
        box-sizing: border-box;
    }

    .todo-form h2 {
        text-align: center;
        margin-bottom: 25px;
        color: #333;
        font-weight: 600;
    }

    .todo-form label {
        display: block;
        margin-bottom: 6px;
        font-weight: 500;
        color: #555;
    }

    .todo-form input,
    .todo-form textarea,
    .todo-form select {
        width: 100%;
        padding: 10px 12px;
        margin-bottom: 18px;
        border-radius: 6px;
        border: 1px solid #ccc;
        font-size: 1em;
        box-sizing: border-box;
    }

    .todo-form textarea {
        resize: vertical;
    }

    .todo-form button {
        width: 100%;
        padding: 12px 0;
        background-color: #28a745;
        border: none;
        color: white;
        border-radius: 6px;
        font-size: 1em;
        font-weight: 500;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .todo-form button:hover {
        background-color: #218838;
    }

    .error {
        color: red;
        font-size: 0.85em;
        margin-top: -12px;
        margin-bottom: 12px;
        display: block;
    }
</style>


</head>
<body>
    <div class="todo-form">
        <h2>Add New Todo</h2>
        <form:form method="POST" modelAttribute="todo" action="/add-todo">


            <!-- Todo Description -->
            <form:label path="description">Description:</form:label>
            <form:textarea path="description" rows="4"/>
            <form:errors path="description" cssClass="error"/>

            <!-- Due Date -->
            <form:label path="targetDate">Due Date:</form:label>
            <form:input path="targetDate" type="date"/>
            <form:errors path="targetDate" cssClass="error"/>

            <!-- Submit Button -->
            <button type="submit">Add Todo</button>
        </form:form>
    </div>
</body>
</html>
