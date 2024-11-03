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
    <title>Administrar Clientes - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f8ff; font-family: Arial, sans-serif; }
        .navbar-custom { background-color: #007bff; }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link { color: white; }
        .nav-link:hover { color: #d1ecf1; }
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
    <h2 class="text-primary mb-4">Listado de Clientes</h2>
    <div class="mb-3">
        <a href="ClienteController?action=registrar" class="btn btn-primary">Agregar Nuevo Cliente</a>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Cédula</th>
                <th>Nombre Completo</th>
                <th>Dirección</th>
                <th>Correo</th>
                <th>Contacto 1</th>
                <th>Contacto 2</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cliente" items="${listaClientes}">
                <tr>
                    <td>${cliente.cedula}</td>
                    <td>${cliente.nombreCompleto}</td>
                    <td>${cliente.direccion}</td>
                    <td>${cliente.correo}</td>
                    <td>${cliente.numeroContacto1}</td>
                    <td>${cliente.numeroContacto2}</td>
                    <td>
                        <a href="ClienteController?action=editar&id=${cliente.id}" class="btn btn-warning btn-sm">Editar</a>
                        <a href="ClienteController?action=eliminar&id=${cliente.id}" class="btn btn-danger btn-sm">Eliminar</a>
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
