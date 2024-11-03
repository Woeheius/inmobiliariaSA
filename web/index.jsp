<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Inmobiliaria S.A.</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- CSS en línea -->
    <style>
        body {
            background-color: #f0f8ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: Arial, sans-serif;
        }
        .login-container {
            background-color: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        .login-header {
            text-align: center;
            margin-bottom: 1rem;
            color: #007bff;
        }
        .btn-login {
            background-color: #007bff;
            border-color: #007bff;
            color: white;
            transition: background-color 0.3s ease;
        }
        .btn-login:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .form-control {
            border-radius: 4px;
        }
        .error-message {
            color: #d9534f;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2 class="login-header">Iniciar Sesión</h2>
    
    <!-- Muestra un mensaje de error si las credenciales no son válidas -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <p class="text-center error-message"><%= errorMessage %></p>
    <%
        }
    %>
    
    <!-- Formulario de inicio de sesión -->
    <form action="LoginController" method="post">
        <div class="form-group">
            <label for="username">Usuario</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Ingrese su usuario" required>
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Ingrese su contraseña" required>
        </div>
        <button type="submit" class="btn btn-login btn-block">Ingresar</button>
    </form>
</div>

<!-- Bootstrap JS, Popper.js, y jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
