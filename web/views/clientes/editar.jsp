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
    <title>Editar Cliente - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f8ff; font-family: Arial, sans-serif; }
        .navbar-custom { background-color: #007bff; }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link { color: white; }
        .container { padding: 2rem; }
        .form-group label { font-weight: bold; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="ClienteController?action=listar">Volver a Clientes</a></li>
        <li class="nav-item"><a class="nav-link" href="LogoutController">Cerrar Sesión</a></li>
    </ul>
</nav>

<div class="container">
    <h2 class="text-primary mb-4">Editar Cliente</h2>
    
    <form action="ClienteController?action=actualizar" method="POST">
        <input type="hidden" name="cedula" value="${cliente.cedula}">
        
        <div class="form-group">
            <label for="nombreCompleto">Nombre Completo:</label>
            <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" value="${cliente.nombreCompleto}" required>
        </div>
        
        <div class="form-group">
            <label for="direccion">Dirección:</label>
            <input type="text" class="form-control" id="direccion" name="direccion" value="${cliente.direccion}" required>
        </div>
        
        <div class="form-group">
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${cliente.fechaNacimiento}" required>
        </div>
        
        <div class="form-group">
            <label for="fechaExpedicion">Fecha de Expedición:</label>
            <input type="date" class="form-control" id="fechaExpedicion" name="fechaExpedicion" value="${cliente.fechaExpedicion}" required>
        </div>
        
        <div class="form-group">
            <label for="correo">Correo Electrónico:</label>
            <input type="email" class="form-control" id="correo" name="correo" value="${cliente.correo}" required>
        </div>
        
        <div class="form-group">
            <label for="numeroContacto1">Teléfono Principal:</label>
            <input type="tel" class="form-control" id="numeroContacto1" name="numeroContacto1" value="${cliente.numeroContacto1}" required>
        </div>
        
        <div class="form-group">
            <label for="numeroContacto2">Teléfono Secundario:</label>
            <input type="tel" class="form-control" id="numeroContacto2" name="numeroContacto2" value="${cliente.numeroContacto2}">
        </div>
        
        <button type="submit" class="btn btn-primary">Actualizar Cliente</button>
        <a href="ClienteController?action=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>