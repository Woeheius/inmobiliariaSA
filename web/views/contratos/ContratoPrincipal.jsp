<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Contratos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col">
                <h2>Gestión de Contratos</h2>
            </div>
            <div class="col text-end">
                <a href="ContratoController?action=registrar" class="btn btn-primary">Nuevo Contrato</a>
            </div>
        </div>

        <!-- Filtros de Búsqueda -->
        <div class="card mb-4">
            <div class="card-header">
                <h5>Filtros de Búsqueda</h5>
            </div>
            <div class="card-body">
                <form action="ContratoController" method="get" class="row g-3">
                    <div class="col-md-3">
                        <select name="tipoReporte" class="form-select">
                            <option value="">Seleccione tipo de reporte</option>
                            <option value="reportePorAgente">Por Agente</option>
                            <option value="reportePorCliente">Por Cliente</option>
                            <option value="reportePorInmueble">Por Inmueble</option>
                            <option value="reportePorTipoYFecha">Por Tipo y Fecha</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <input type="date" name="fechaInicio" class="form-control" placeholder="Fecha Inicio">
                    </div>
                    <div class="col-md-3">
                        <input type="date" name="fechaFin" class="form-control" placeholder="Fecha Fin">
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary">Filtrar</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Tabla de Contratos -->
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Tipo</th>
                        <th>Descripción</th>
                        <th>Inmueble</th>
                        <th>Cliente</th>
                        <th>Agente</th>
                        <th>Valor</th>
                        <th>Fecha Creación</th>
                        <th>Fecha Expiración</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="contrato" items="${listaContratos}">
                        <tr>
                            <td>${contrato.codigo}</td>
                            <td>${contrato.tipoContrato}</td>
                            <td>${contrato.descripcion}</td>
                            <td>${contrato.codigoInmueble}</td>
                            <td>${contrato.cedulaCliente}</td>
                            <td>${contrato.cedulaAgente}</td>
                            <td>$${contrato.valor}</td>
                            <td>${contrato.fechaCreacion}</td>
                            <td>${contrato.fechaExpiracion}</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a href="ContratoController?action=editar&codigo=${contrato.codigo}" 
                                       class="btn btn-warning btn-sm">Editar</a>
                                    <a href="ContratoController?action=eliminar&codigo=${contrato.codigo}" 
                                       class="btn btn-danger btn-sm" 
                                       onclick="return confirm('¿Está seguro de eliminar este contrato?')">Eliminar</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Totales y Resumen -->
        <div class="card mt-4">
            <div class="card-header">
                <h5>Resumen</h5>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-4">
                        <h6>Total Contratos: ${listaContratos.size()}</h6>
                    </div>
                    <div class="col-md-4">
                        <h6>Total Ventas: $${totalVentas}</h6>
                    </div>
                    <div class="col-md-4">
                        <h6>Total Alquileres: $${totalAlquileres}</h6>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>