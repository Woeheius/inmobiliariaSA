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
    <script>
        function toggleFields() {
            const tipoServicio = document.getElementById('tipoServicio').value;
            const codigoClienteField = document.getElementById('codigoCliente');
            const porcentajeComisionField = document.getElementById('porcentajeComision');

            if (tipoServicio === 'administrar') {
                codigoClienteField.value = '000'; // Asigna el valor '000' para inmobiliaria
                codigoClienteField.disabled = true; // Bloquea el campo
                porcentajeComisionField.value = '0'; // Asigna 0 al porcentaje de comisión
                porcentajeComisionField.disabled = true; // Bloquea el campo
            } else if (tipoServicio === 'adquirir') {
                codigoClienteField.disabled = false; // Desbloquea el campo
                porcentajeComisionField.disabled = false; // Desbloquea el campo
            }
        }
    </script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#"><i class="fas fa-building"></i> Inmobiliaria S.A.</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="ContratoController?action=listar">
                <i class="fas fa-arrow-left"></i> Volver a Contratos
            </a>
        </li>
    </ul>
</nav>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4 class="mb-0"><i class="fas fa-file-contract"></i> Registrar Nuevo Contrato</h4>
                </div>
                <div class="card-body">
                    <form action="ContratoController?action=agregar" method="POST">
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="codigo" class="form-label">Código del Contrato:</label>
                                <input type="text" class="form-control" id="codigo" name="codigo" required>
                            </div>
                            <div class="col-md-6">
                                <label for="cedulaAgente" class="form-label">Cédula del Agente:</label>
                                <input type="text" class="form-control" id="cedulaAgente" name="cedulaAgente" value="${sessionScope.agente.cedula}" readonly>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="codigoCliente" class="form-label">Código del Cliente:</label>
                                <select class="form-control" id="codigoCliente" name="codigoCliente " required>
                                    <option value="">Seleccione un cliente</option>
                                    <option value="cliente1">Cliente 1</option>
                                    <option value="cliente2">Cliente 2</option>
                                    <option value="cliente3">Cliente 3</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="fechaCreacion" class="form-label">Fecha de Creación:</label>
                                <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="fechaExpiracion" class="form-label">Fecha de Expiración:</label>
                                <input type="date" class="form-control" id="fechaExpiracion" name="fechaExpiracion" required>
                            </div>
                            <div class="col-md-6">
                                <label for="tipoContrato" class="form-label">Tipo de Contrato:</label>
                                <select class="form-control" id="tipoContrato" name="tipoContrato" required>
                                    <option value="venta">Venta</option>
                                    <option value="alquiler">Alquiler</option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="tipoServicio" class="form-label">Tipo de Servicio:</label>
                                <select class="form-control" id="tipoServicio" name="tipoServicio" required onchange="toggleFields()">
                                    <option value="administrar">Administrar</option>
                                    <option value="adquirir">Adquirir</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="valor" class="form-label">Valor:</label>
                                <input type="number" class="form-control" id="valor" name="valor" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="porcentajeComision" class="form-label">Porcentaje de Comisión:</label>
                                <input type="number" class="form-control" id="porcentajeComision" name="porcentajeComision" required>
                            </div>
                            <div class="col-md-6">
                                <label for="codigoInmueble" class="form-label">Código del Inmueble:</label>
                                <select class="form-control" id="codigoInmueble" name="codigoInmueble" required>
                                    <option value="">Seleccione un inmueble</option>
                                    <option value="inmueble1">Inmueble 1</option>
                                    <option value="inmueble2">Inmueble 2</option>
                                    <option value="inmueble3">Inmueble 3</option>
                                </select>
                            </div>
                        </div>

                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i> Registrar
                            </button>
                            <a href="ContratoController?action=listar" class="btn btn-secondary">
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