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
    <title>Registrar Propietario - Inmobiliaria S.A.</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body { 
            background-color: #f0f8ff; 
            font-family: Arial, sans-serif; 
        }
        .navbar-custom { 
            background-color: #007bff; 
        }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link { 
            color: white; 
        }
        .container { 
            padding: 2rem; 
        }
        .card {
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .btn-primary { 
            background-color: #007bff; 
            border: none; 
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#"><i class="fas fa-building"></i> Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="PropietarioController?action=listar">
                <i class="fas fa-arrow-left"></i> Volver a Propietarios
            </a>
        </li>
    </ul>
</nav>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4 class="mb-0"><i class="fas fa-user-plus"></i> Registrar Nuevo Propietario</h4>
                </div>
                <div class="card-body">
                    <form action="PropietarioController?action=agregar" method="POST">
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="cedula" class="form-label">Cédula:</label>
                                <input type="text" class="form-control" id="cedula" name="cedula" required>
                            </div>
                            <div class="col-md-6">
                                <label for="nombreCompleto" class="form-label">Nombre Completo:</label>
                                <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="direccion" class="form-label">Dirección:</label>
                                <input type="text" class="form-control" id="direccion" name="direccion" required>
                            </div>
                            <div class="col-md-6">
                                <label for="correo" class="form-label">Correo:</label>
                                <input type="email" class="form-control" id="correo" name="correo" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
                                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                            </div>
                            <div class="col-md-6">
                                <label for="fechaExpedicion" class="form-label">Fecha de Expedición:</label>
                                <input type="date" class="form-control" id="fechaExpedicion" name="fechaExpedicion" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="numeroContacto1" class="form-label">Número de Contacto 1:</label>
                                <input type="tel" class="form-control" id="numeroContacto1" name="numeroContacto1" required>
                            </div>
                            <div class="col-md-6">
                                <label for="numeroContacto2" class="form-label">Número de Contacto 2:</label>
                                <input type="tel" class="form-control" id="numeroContacto2" name="numeroContacto2">
                            </div>
                        </div>

                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i> Registrar
                            </button>
                            <a href="PropietarioController?action=listar" class="btn btn-secondary">
                                <i class="fas fa-times"></i> Cancelar
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>