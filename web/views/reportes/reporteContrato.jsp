<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte de Contratos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col">
                <h2>Reporte de Contratos</h2>
            </div>
            <div class="col text-end">
                <a href="ContratoController?action=listar" class="btn btn-secondary">Volver</a>
            </div>
        </div>

        <!-- Filtros de Búsqueda -->
        <div class="card mb-4">
            <div class="card-header">
                <h5>Filtros de Búsqueda</h5>
            </div>
            <div class="card-body">
                <form action="ContratoController" method="get" class="row g-3">
                    <input type="hidden" name="action" value="reporte">
                    <div class="col-md-3">
                        <select name="tipoReporte" class="form-select" required>
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
                        <button type="submit" class="btn btn-primary">Generar Reporte</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Resultados del Reporte -->
        <c:if test="${not empty reporteContratos}">
            <div class="card">
                <div class="card-header">
                    <h5>Resultados del Reporte</h5>
                </div>
                <div class="card-body">
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
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="contrato" items="${reporteContratos}">
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
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Resumen del Reporte -->
                    <div class="mt-4">
                        <h5>Resumen del Reporte</h5>
                        <p>Total de Contratos: ${reporteContratos.size()}</p>
                        <p>Valor Total: $${valorTotal}</p>
                        <c:if test="${not empty promedioValor}">
                            <p>Valor Promedio: $${promedioValor}</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- Mensaje si no hay resultados -->
        <c:if test="${empty reporteContratos and not empty mensaje}">
            <div class="alert alert-info" role="alert">
                ${mensaje}
            </div>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>