<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Contrato</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row">
            <div class="col">
                <h2>Editar Contrato</h2>
            </div>
            <div class="col text-end">
                <a href="ContratoController?action=listar" class="btn btn-secondary">Volver</a>
            </div>
        </div>

        <div class="card mt-4">
            <div class="card-body">
                <form action="ContratoController" method="post" class="needs-validation" novalidate>
                    <input type="hidden" name="action" value="actualizar">
                    <input type="hidden" name="codigo" value="${contrato.codigo}">

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label">Código del Contrato:</label>
                            <input type="text" class="form-control" value="${contrato.codigo}" disabled>
                        </div>

                        <div class="col-md-6">
                            <label for="tipoContrato" class="form-label">Tipo de Contrato:</label>
                            <select class="form-select" id="tipoContrato" name="tipoContrato" required>
                                <option value="">Seleccione un tipo</option>
                                <option value="Venta" ${contrato.tipoContrato == 'Venta' ? 'selected' : ''}>Venta</option>
                                <option value="Alquiler" ${contrato.tipoContrato == 'Alquiler' ? 'selected' : ''}>Alquiler</option>
                            </select>
                            <div class="invalid-feedback">
                                Por favor seleccione el tipo de contrato.
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-12">
                            <label for="descripcion" class="form-label">Descripción:</label>
                            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required>${contrato.descripcion}</textarea>
                            <div class="invalid-feedback">
                                Por favor ingrese una descripción.
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="codigoInmueble" class="form-label">Inmueble:</label>
                            <select class="form-select" id="codigoInmueble" name="codigoInmueble" required>
                                <option value="">Seleccione un inmueble</option>
                                <c:forEach var="inmueble" items="${listaInmuebles}">
                                    <option value="${inmueble.codigo}" 
                                            ${contrato.codigoInmueble == inmueble.codigo ? 'selected' : ''}>
                                        ${inmueble.descripcion} - ${inmueble.direccion}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Por favor seleccione un inmueble.
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label for="cedulaCliente" class="form-label">Cliente:</label>
                            <select class="form-select" id="cedulaCliente" name="cedulaCliente" required>
                                <option value="">Seleccione un cliente</option>
                                <c:forEach var="cliente" items="${listaClientes}">
                                    <option value="${cliente.cedula}" 
                                            ${contrato.cedulaCliente == cliente.cedula ? 'selected' : ''}>
                                        ${cliente.nombreCompleto} - ${cliente.cedula}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Por favor seleccione un cliente.
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="valor" class="form-label">Valor:</label>
                            <input type="number" class="form-control" id="valor" name="valor" 
                                   step="0.01" value="${contrato.valor}" required>
                            <div class="invalid-feedback">
                                Por favor ingrese el valor del contrato.
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label for="porcentajeComision" class="form-label">Porcentaje de Comisión (%):</label>
                            <input type="number" class="form-control" id="porcentajeComision" name="porcentajeComision" 
                                   step="0.01" min="0" max="100" value="${contrato.porcentajeComision}" required>
                            <div class="invalid-feedback">
                                Por favor ingrese el porcentaje de comisión.
                            </div>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="fechaCreacion" class="form-label">Fecha de Creación:</label>
                            <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" 
                                   value="${contrato.fechaCreacion}" required>
                            <div class="invalid-feedback">
                                Por favor seleccione la fecha de creación.
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label for="fechaExpiracion" class="form-label">Fecha de Expiración:</label>
                            <input type="date" class="form-control" id="fechaExpiracion" name="fechaExpiracion" 
                                   value="${contrato.fechaExpiracion}" required>
                            <div class="invalid-feedback">
                                Por favor seleccione la fecha de expiración.
                            </div>
                        </div>
                    </div>

                    <div class="text-end">
                        <button type="submit" class="btn btn-primary">Actualizar Contrato</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Validación del formulario
        (function () {
            'use strict'
            var forms = document.querySelectorAll('.needs-validation')
            Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }
                        form.classList.add('was-validated')
                    }, false)
                })
        })()
    </script>
</body>
</html>