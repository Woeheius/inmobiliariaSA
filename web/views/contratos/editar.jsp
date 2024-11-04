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
    <title>Editar Contrato - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f8ff; font-family: Arial, sans-serif; }
        .navbar-custom { background-color: #007bff; }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link { color: white; }
        .container { padding: 2rem; }
        .form-group { margin-bottom: 1rem; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="ContratoController?action=listar">Volver a la lista</a></li>
        <li class="nav-item"><a class="nav-link" href="LogoutController">Cerrar Sesión</a></li>
    </ul>
</nav>

<div class="container">
    <h2 class="text-primary mb-4">Editar Contrato</h2>

    <form action="ContratoController" method="post">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="codigo" value="${contrato.codigo}">

        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <input type="text" class="form-control" id="descripcion" name="descripcion" value="${contrato.descripcion}" required>
        </div>

        <div class="form-group">
            <label for="tipoContrato">Tipo de Contrato:</label>
            <select class="form-control" id="tipoContrato" name="tipoContrato" required>
                <option value="Venta" ${contrato.tipoContrato == 'Venta' ? 'selected' : ''}>Venta</option>
                <option value="Alquiler" ${contrato.tipoContrato == 'Alquiler' ? 'selected' : ''}>Alquiler</option>
            </select>
        </div>

        <div class="form-group">
            <label for="fechaCreacion">Fecha de Creación:</label>
            <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" value="${contrato.fechaCreacion}" required>
        </div>

        <div class="form-group">
            <label for="fechaExpiracion">Fecha de Expiración:</label>
            <input type="date" class="form-control" id="fechaExpiracion" name="fechaExpiracion" value="${contrato.fechaExpiracion}" required>
        </div>

        <div class="form-group">
            <label for="valor">Valor:</label>
            <input type="number" step="0.01" class="form-control" id="valor" name="valor" value="${contrato.valor}" required>
        </div>

        <div class="form-group">
            <label for="porcentajeComision">Porcentaje de Comisión:</label>
            <input type="number" step="0.01" class="form-control" id="porcentajeComision" name="porcentajeComision" value="${contrato.porcentajeComision}" required>
        </div>

        <button type="submit" class="btn btn-primary">Actualizar Contrato</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>