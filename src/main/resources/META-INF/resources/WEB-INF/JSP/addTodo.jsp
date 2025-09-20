<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Todo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

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

        .checkbox-group {
            margin-bottom: 18px;
            display: flex;
            align-items: center;
            gap: 8px;
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
        <h2>Enter Todo Details</h2>
        <form:form method="POST" modelAttribute="todo" >

            <!-- Todo Description -->
            <form:label path="description">Description:</form:label>
            <form:textarea path="description" rows="4"/>
            <form:errors path="description" cssClass="error"/>

            <!-- Category Dropdown (NEW) -->
            <form:label path="category">Category:</form:label>
            <form:select path="category">
                <form:option value="Personal" label="Personal"/>
                <form:option value="Work" label="Work"/>
                <form:option value="Others" label="Others"/>
            </form:select>
            <form:errors path="category" cssClass="error"/>

            <!-- Due Date -->
           <form:label path="targetDateTime">Due Date & Time:</form:label>
           <form:input path="targetDateTime" type="datetime-local"
               value="${todo.targetDateTime != null ? todo.targetDateTime.toString() : ''}"/>
           <form:errors path="targetDateTime" cssClass="error"/>

            <script>
                flatpickr("#targetDateTime", {
                    enableTime: true,
                    dateFormat: "d-m-Y H:i",   // dd-mm-yyyy HH:MM
                    time_24hr: true
                });
            </script>

            <!-- Mark as Completed -->
            <div style="margin-bottom: 18px; display: flex; align-items: center;">
                <form:checkbox path="done" id="doneCheckbox" style="width: 18px; height: 18px;"/>
                <label for="doneCheckbox" style="margin-left: 10px; font-weight: 500; color: #555; font-size: 0.95em;">
                    Mark as Completed
                </label>
            </div>
            <form:errors path="done" cssClass="error"/>

     <div style="display: flex; justify-content: space-between; gap: 10px;">
         <button type="button" onclick="window.location.href='/todos'"
                 style="width: 100%; padding: 12px 0; background-color: #007bff;
                        border: none; color: white; border-radius: 6px;
                        font-size: 1em; font-weight: 500; cursor: pointer;
                        transition: background-color 0.3s ease;">
             Go Back
         </button>
         <button type="submit">Submit</button>
     </div>


        </form:form>
    </div>
</body>
</html>
