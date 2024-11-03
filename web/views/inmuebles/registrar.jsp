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
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="InmuebleController?action=listar">Volver a Inmuebles</a></li>
    </ul>
</nav>

<div class="container mt-4">
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
        <button type="submit" class="btn btn-primary btn-block">Registrar Inmueble</button>
    </form>
</div>
</body>
</html>
