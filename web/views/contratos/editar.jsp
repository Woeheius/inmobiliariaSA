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
    <title>Editar Contrato - Inmobiliaria S.A.</title>
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
                    <h4 class="mb-0"><i class="fas fa-edit"></i> Editar Contrato</h4>
                </div>
                <div class="card-body">
                    <form action="ContratoController?action=actualizar" method="POST">
                        <input type="hidden" name="codigo" value="${contrato.codigo}">
                        
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="codigo" class="form-label">Código:</label>
                                <input type="text" class="form-control" id="codigo" value="${contrato.codigo}" readonly>
                            </div>
                            <div class="col-md-6">
                                <label for="tipoContrato" class="form-label">Tipo de Contrato:</label>
                                <select class="form-control" id="tipoContrato" name="tipoContrato" required>
                                    <option value="Venta" ${contrato.tipoContrato == 'Venta' ? 'selected' : ''}>Venta</option>
                                    <option value="Alquiler" ${contrato.tipoContrato == 'Alquiler' ? 'selected' : ''}>Alquiler</option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-12">
                                <label for="descripcion" class="form-label">Descripción:</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required>${contrato.descripcion}</textarea>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="fechaCreacion" class="form-label">Fecha de Creación:</label>
                                <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" value="${contrato.fechaCreacion}" required>
                            </div>
                            <div class="col-md-6">
                                <label for="fechaExpiracion" class="form-label">Fecha de Expiración:</label>
                                <input type="date" class="form-control" id="fechaExpiracion" name="fechaExpiracion" value="${contrato.fechaExpiracion}" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="valor" class="form-label">Valor:</label>
                                <input type="number" class="form-control" id="valor" name="valor" step="0.01" value="${contrato.valor}" required>
                            </div>
                            <div class="col-md-6">
                                <label for="porcentajeComision" class="form-label">Porcentaje de Comisión:</label>
                                <input type="number" class="form-control" id="porcentajeComision" name="porcentajeComision" step="0.01" value="${contrato.porcentajeComision}" required>
                            </div>
                        </div>

                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i> Actualizar
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