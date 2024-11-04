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
    <title>Registrar Inmueble - Inmobiliaria S.A.</title>
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
        <li class="nav-item"><a class="nav-link" href="InmuebleController?action=listar">Volver a Inmuebles</a></li>
    </ul>
</nav>

<div class="container">
    <h2 class="text-primary mb-4">Registrar Nuevo Inmueble</h2>
    <form action="InmuebleController?action=agregar" method="post">
        <div class="form-group">
            <label for="codigo">Código</label>
            <input type="text" id="codigo" name="codigo" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input type="text" id="descripcion" name="descripcion" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="tipoInmueble">Tipo de Inmueble</label>
            <input type="text" id="tipoInmueble" name="tipoInmueble" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="modalidad">Modalidad</label>
            <input type="text" id="modalidad" name="modalidad" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="precio">Precio</label>
            <input type="number" id="precio" name="precio" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="estado">Estado</label>
            <input type="text" id="estado" name="estado" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="cantidadBanos">Cantidad de Baños</label>
            <input type="number" id="cantidadBanos" name="cantidadBanos" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="tamano">Tamaño (m²)</label>
            <input type="number" step="0.01" id="tamano" name="tamano" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="departamento">Departamento</label>
            <input type="text" id="departamento" name="departamento" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="ciudad">Ciudad</label>
            <input type="text" id="ciudad" name="ciudad" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input type="text" id="direccion" name="direccion" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary btn-block">Registrar Inmueble</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
