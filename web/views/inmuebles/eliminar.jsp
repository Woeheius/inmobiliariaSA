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
    <title>Eliminar Inmueble - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-danger mb-4">Eliminar Inmueble</h2>
    <p>¿Estás seguro de que deseas eliminar el inmueble con código <strong>${inmueble.codigo}</strong>?</p>
    <form action="InmuebleController?action=eliminar" method="post">
        <input type="hidden" name="id" value="${inmueble.id}">
        <button type="submit" class="btn btn-danger">Eliminar</button>
        <a href="InmuebleController?action=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
