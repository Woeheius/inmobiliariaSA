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
    <title>Administrar Inmuebles - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="menuAgente.jsp">Volver al Menú Principal</a></li>
    </ul>
</nav>
<div class="container mt-4">
    <h2 class="text-primary mb-4">Listado de Inmuebles</h2>
    <div class="mb-3">
        <a href="InmuebleController?action=registrar" class="btn btn-primary">Agregar Nuevo Inmueble</a>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Código</th>
                <th>Descripción</th>
                <th>Tipo</th>
                <th>Modalidad</th>
                <th>Precio</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="inmueble" items="${listaInmuebles}">
                <tr>
                    <td>${inmueble.codigo}</td>
                    <td>${inmueble.descripcion}</td>
                    <td>${inmueble.tipoInmueble}</td>
                    <td>${inmueble.modalidad}</td>
                    <td>${inmueble.precio}</td>
                    <td>${inmueble.estado}</td>
                    <td>
                        <a href="InmuebleController?action=editar&id=${inmueble.id}" class="btn btn-warning btn-sm">Editar</a>
                        <a href="InmuebleController?action=eliminar&id=${inmueble.id}" class="btn btn-danger btn-sm">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
