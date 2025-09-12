<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome | ToDo App</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background: linear-gradient(135deg, #74ebd5 0%, #9face6 100%);
    }

    .container {
      text-align: center;
      background: white;
      padding: 50px;
      border-radius: 20px;
      box-shadow: 0px 8px 25px rgba(0, 0, 0, 0.2);
      max-width: 400px;
      width: 90%;
      animation: fadeIn 1.2s ease-in-out;
    }

    h1 {
      margin-bottom: 10px;
      color: #4a4a8c;
    }

    p {
      margin-bottom: 30px;
      color: #555;
      font-size: 16px;
    }

    .btn {
      display: inline-block;
      padding: 12px 25px;
      font-size: 16px;
      color: white;
      background: #6c63ff;
      border: none;
      border-radius: 30px;
      text-decoration: none;
      transition: 0.3s;
    }

    .btn:hover {
      background: #5548c8;
      transform: scale(1.05);
    }

    @keyframes fadeIn {
      from { opacity: 0; transform: translateY(-20px); }
      to { opacity: 1; transform: translateY(0); }
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>Welcome to ToDo App ✅</h1>
    <p>Organize your tasks, boost productivity, and never miss a deadline.</p>
    <!-- Direct link to Spring Security login page -->
    <a href="/login" class="btn">Get Started</a>
  </div>
</body>
</html>
