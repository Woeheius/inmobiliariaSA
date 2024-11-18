<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Verifica si el usuario está autenticado; si no, redirige a login
    if (session.getAttribute("cliente") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú Principal - Inmobiliaria S.A.</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- CSS en línea -->
    <style>
        body {
            background-color: #f0f8ff;
            font-family: Arial, sans-serif;
        }
        .navbar-custom {
            background-color: #007bff;
        }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link {
            color: white;
        }
        .nav-link:hover {
            color: #d1ecf1;
        }
        .container-menu {
            padding: 2rem;
            text-align: center;
        }
        .btn-menu {
            margin: 0.5rem;
            padding: 1rem 2rem;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1.1rem;
            transition: background-color 0.3s ease;
        }
        .btn-menu:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<!-- Barra de navegación -->
<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="LogoutController">Cerrar Sesión</a>
            </li>
        </ul>
    </div>
</nav>

<!-- Contenido principal del menú -->
<div class="container-menu">
    <h2 class="text-primary mb-4">Bienvenido, <%= ((modeloDTO.Cliente) session.getAttribute("cliente")).getNombreCompleto() %></h2>
    <p class="lead">Selecciona una de las opciones para comenzar:</p>
    
    <!-- Botones de menú -->
    <div class="d-flex justify-content-center flex-wrap">
        <a href="ContratoController?action=vistaContratos" class="btn btn-menu">Administrar Contratos</a>
        <a href="InmuebleController?action=vistaInmuebles" class="btn btn-menu">Administrar Inmuebles</a>
    </div>
</div>

<!-- Bootstrap JS, Popper.js, y jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>