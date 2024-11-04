<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .container { padding: 2rem; max-width: 600px; }
        .btn-primary { background-color: #007bff; border: none; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="ClienteController?action=listar">Volver a Clientes</a></li>
    </ul>
</nav>

<div class="container">
    <h2 class="text-primary mb-4">Editar Cliente</h2>
    <form action="ClienteController?action=actualizar" method="post">
        <input type="hidden" name="id" value="${cliente.id}">
        <div class="form-group">
            <label for="cedula">Cédula</label>
            <input type="text" id="cedula" name="cedula" class="form-control" value="${cliente.cedula}" required>
        </div>
        <div class="form-group">
            <label for="nombreCompleto">Nombre Completo</label>
            <input type="text" id="nombreCompleto" name="nombreCompleto" class="form-control" value="${cliente.nombreCompleto}" required>
        </div>
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input type="text" id="direccion" name="direccion" class="form-control" value="${cliente.direccion}" required>
        </div>
        <div class="form-group">
            <label for="fechaNacimiento">Fecha de Nacimiento</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" value="${cliente.fechaNacimiento}" required>
        </div>
        <div class="form-group">
            <label for="fechaExpedicion">Fecha de Expedición</label>
            <input type="date" id="fechaExpedicion" name="fechaExpedicion" class="form-control" value="${cliente.fechaExpedicion}" required>
        </div>
        <div class="form-group">
            <label for="correo">Correo Electrónico</label>
            <input type="email" id="correo" name="correo" class="form-control" value="${cliente.correo}" required>
        </div>
        <div class="form-group">
            <label for="numeroContacto1">Contacto 1</label>
            <input type="text" id="numeroContacto1" name="numeroContacto1" class="form-control" value="${cliente.numeroContacto1}" required>
        </div>
        <div class="form-group">
            <label for="numeroContacto2">Contacto 2</label>
            <input type="text" id="numeroContacto2" name="numeroContacto2" class="form-control" value="${cliente.numeroContacto2}">
        </div>

        <button type="submit" class="btn btn-primary btn-block">Actualizar Cliente</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
