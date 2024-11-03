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
    <title>Registrar Cliente - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="text-primary mb-4">Registrar Nuevo Cliente</h2>
    <form action="ClienteController?action=agregar" method="post">
        <!-- Campos de cliente -->
        <div class="form-group">
            <label for="cedula">Cédula</label>
            <input type="text" id="cedula" name="cedula" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="nombreCompleto">Nombre Completo</label>
            <input type="text" id="nombreCompleto" name="nombreCompleto" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input type="text" id="direccion" name="direccion" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="correo">Correo Electrónico</label>
            <input type="email" id="correo" name="correo" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="numeroContacto1">Contacto 1</label>
            <input type="text" id="numeroContacto1" name="numeroContacto1" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="numeroContacto2">Contacto 2</label>
            <input type="text" id="numeroContacto2" name="numeroContacto2" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary btn-block">Registrar Cliente</button>
    </form>
</div>
</body>
</html>
