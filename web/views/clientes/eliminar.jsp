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
    <title>Eliminar Cliente - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="ClienteController?action=listar">Volver a Clientes</a></li>
    </ul>
</nav>
<div class="container mt-4">
    <h2 class="text-danger mb-4">Eliminar Cliente</h2>
    <p>¿Estás seguro de que deseas eliminar al cliente <strong>${cliente.nombreCompleto}</strong>?</p>
    <form action="ClienteController?action=eliminar" method="post">
        <input type="hidden" name="id" value="${cliente.id}">
        <button type="submit" class="btn btn-danger">Eliminar</button>
        <a href="ClienteController?action=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
