<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- CSS en línea -->
    <style>
        body { background-color: #f0f8ff; font-family: Arial, sans-serif; }
        .navbar-custom { background-color: #007bff; }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link { color: white; }
        .nav-link:hover { color: #d1ecf1; }
        .container-menu { padding: 2rem; }
        .btn-menu { margin: 0.5rem; padding: 1rem 2rem; background-color: #007bff; color: white; border: none; border-radius: 5px; font-size: 1.1rem; }
        .btn-menu:hover { background-color: #0056b3; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="menuAgente.jsp">Volver al Menú Principal</a></li>
    </ul>
</nav>

<div class="container mt-4">
    <h2 class="text-primary mb-4">Listado de Inmuebles</h2>
    <div class="mb-3">
        <a href="InmuebleController?action=registrar" class="btn btn-menu">Agregar Nuevo Inmueble</a>
    </div>
    <c:if test="${empty listaInmuebles}">
        <p>La lista de inmuebles está vacía</p>
    </c:if>
    <c:if test="${not empty listaInmuebles}">
        <p>Número de inmuebles: ${listaInmuebles.size()}</p>
    </c:if>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Código</th>
                <th>Descripción</th>
                <th>Tipo de Inmueble</th>
                <th>Modalidad</th>
                <th>Precio</th>
                <th>Estado</th>
                <th>Cantidad de Baños</th>
                <th>Tamaño (m²)</th>
                <th>Departamento</th>
                <th>Ciudad</th>
                <th>Dirección</th>
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
                    <td>${inmueble.cantidadBanos}</td>
                    <td>${inmueble.tamano}</td>
                    <td>${inmueble.departamento}</td>
                    <td>${inmueble.ciudad}</td>
                    <td>${inmueble.direccion}</td>
                    <td>
                        <a href="InmuebleController?action=editar&codigo=${inmueble.codigo}" class="btn btn-warning btn-sm">Editar</a>
    <a href="InmuebleController?action=eliminar&codigo=${inmueble.codigo}" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro de que desea eliminar este inmueble?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
