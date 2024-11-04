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
            <a class="nav-link" href="InmuebleController?action=listar">
                <i class="fas fa-arrow-left"></i> Volver a Inmuebles
            </a>
        </li>
    </ul>
</nav>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4 class="mb-0"><i class="fas fa-home"></i> Registrar Nuevo Inmueble</h4>
                </div>
                <div class="card-body">
                    <form action="InmuebleController?action=agregar" method="POST">
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="codigo" class="form-label">Código:</label>
                                <input type="text" class="form-control" id="codigo" name="codigo" required>
                            </div>
                            <div class="col-md-6">
                                <label for="tipoInmueble" class="form-label">Tipo de Inmueble:</label>
                                <select class="form-control" id="tipoInmueble" name="tipoInmueble" required>
                                    <option value="Casa">Casa</option>
                                    <option value="Apartamento">Apartamento</option>
                                    <option value="Finca">Finca</option>
                                    <option value="Local Comercial">Local Comercial</option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-12">
                                <label for="descripcion" class="form-label">Descripción:</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="modalidad" class="form-label">Modalidad:</label>
                                <select class="form-control" id="modalidad" name="modalidad" required>
                                    <option value="Venta">Venta</option>
                                    <option value="Alquiler">Alquiler</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="precio" class="form-label">Precio:</label>
                                <input type="number" class="form-control" id="precio" name="precio" step="0.01" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="estado" class="form-label">Estado:</label>
                                <select class="form-control" id="estado" name="estado" required>
                                    <option value="En oferta">En oferta</option>
                                    <option value="Vendido">Vendido</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="cantidadBanos" class="form-label">Cantidad de Baños:</label>
                                <input type="number" class="form-control" id="cantidadBanos" name="cantidadBanos" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="tamano" class="form-label">Tamaño (m²):</label>
                                <input type="number" class="form-control" id="tamano" name="tamano" step="0.01" required>
                            </div>
                            <div class="col-md-6">
                                <label for="departamento" class="form-label">Departamento:</label>
                                <input type="text" class="form-control" id="departamento" name="departamento" required>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="ciudad" class="form-label">Ciudad:</label>
                                <input type="text" class="form-control" id="ciudad" name="ciudad" required>
                            </div>
                            <div class="col-md-6">
                                <label for="direccion" class="form-label">Dirección:</label>
                                <input type="text" class="form-control" id="direccion" name="direccion" required>
                            </div>
                        </div>

                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i> Registrar
                            </button>
                            <a href="InmuebleController?action=listar" class="btn btn-secondary">
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