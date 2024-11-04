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
    <title>Registrar Contrato - Inmobiliaria S.A.</title>
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
        <li class="nav-item"><a class="nav-link" href="ContratoController?action=listar">Volver a Contratos</a></li>
    </ul>
</nav>

<div class="container">
    <h2 class="text-primary mb-4">Registrar Nuevo Contrato</h2>
    <form action="ContratoController?action=agregar" method="post">
        <div class="form-group">
            <label for="codigo">Código</label>
            <input type="text" id="codigo" name="codigo" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input type="text" id="descripcion" name="descripcion" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="tipoContrato">Tipo de Contrato</label>
            <input type="text" id="tipoContrato" name="tipoContrato" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="fechaCreacion">Fecha de Creación</label>
            <input type="date" id="fechaCreacion" name="fechaCreacion" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="fechaExpiracion">Fecha de Expiración</label>
            <input type="date" id="fechaExpiracion" name="fechaExpiracion" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="valor">Valor</label>
            <input type="number" step="0.01" id="valor" name="valor" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="porcentajeComision">Porcentaje de Comisión</label>
            <input type="number" step="0.01" id="porcentajeComision" name="porcentajeComision" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary btn-block">Registrar Contrato</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
