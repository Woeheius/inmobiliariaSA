<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%
    if (session.getAttribute("agente") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Propietarios - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f8ff; font-family: Arial, sans-serif; }
        .navbar-custom { background-color: #007bff; }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link { color: white; }
        .container { padding: 2rem; }
        .btn-primary { background-color: #007bff; border: none; }
        .table-responsive { margin-top: 20px; }
        .table th { background-color: #007bff; color: white; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="menuAgente.jsp">Volver al Menú</a></li>
        <li class="nav-item"><a class="nav-link" href="LogoutController">Cerrar Sesión</a></li>
    </ul>
</nav>

<div class="container">
    <h2 class="text-primary mb-4">Gestión de Propietarios</h2>
    <a href="PropietarioController?action=registrar" class="btn btn-primary mb-3">Registrar Nuevo Propietario</a>
    
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Cédula</th>
            <th>Nombre Completo</th>
            <th>Dirección</th>
            <th>Fecha de Nacimiento</th>
            <th>Fecha de Expedición</th>
            <th>Correo</th>
            <th>Número de Contacto 1</th>
            <th>Número de Contacto 2</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="propietario" items="${listaPropietarios}">
            <tr>
                 <td>${propietario.cedula}</td>
                 <td>${propietario.nombreCompleto}</td>
                 <td>${propietario.direccion}</td>
                 <td>${propietario.fechaNacimiento}</td>
                 <td>${propietario.fechaExpedicion}</td>
                 <td>${propietario.correo}</td>
                 <td>${propietario.numeroContacto1}</td>
                 <td>${propietario.numeroContacto2}</td>
                 <td>
                    <a href="PropietarioController?action=editar&id=${propietario.id}" class="btn btn-sm btn-primary">
                        <i class="fas fa-edit"></i> Editar
                    </a>
                    <a href="PropietarioController?action=eliminar&cedula=${propietario.cedula}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar este propietario?')">
                        <i class="fas fa-trash"></i> Eliminar
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    </div>
</div>

<!-- Font Awesome para los iconos -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>