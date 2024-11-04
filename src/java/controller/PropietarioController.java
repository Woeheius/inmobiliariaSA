package controller;

import modeloDAO.PropietarioDAO;
import modeloDTO.Propietario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PropietarioController")
public class PropietarioController extends HttpServlet {
    private static final PropietarioDAO propietarioDAO = new PropietarioDAO();

 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    switch (action) {
        case "listar":
            listarPropietarios(request, response);
            break;
        case "registrar":
            request.getRequestDispatcher("views/propietarios/registrar.jsp").forward(request, response);
            break;
        case "editar":
            String cedula = request.getParameter("cedula");
            Propietario propietario = propietarioDAO.listarPorCedula(cedula);
            request.setAttribute("propietario", propietario);
            request.getRequestDispatcher("views/propietarios/editar.jsp").forward(request, response);
            break;
case "eliminar":
    String cedulaEliminar = request.getParameter("cedula");
    propietarioDAO.eliminar(cedulaEliminar);
    response.sendRedirect("PropietarioController?action=listar");
    break;
        default:
            listarPropietarios(request, response);
            break;
    }
}

    private void listarPropietarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Propietario> listaPropietarios = propietarioDAO.listar();
        request.setAttribute("listaPropietarios", listaPropietarios);
        request.getRequestDispatcher("views/propietarios/PropietarioPrincipal.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    // Crear un nuevo objeto Propietario
    Propietario propietario = new Propietario();
    propietario.setCedula(request.getParameter("cedula"));
    propietario.setNombreCompleto(request.getParameter("nombreCompleto"));
    propietario.setDireccion(request.getParameter("direccion"));
    propietario.setFechaNacimiento(request.getParameter("fechaNacimiento"));
    propietario.setFechaExpedicion(request.getParameter("fechaExpedicion"));
    propietario.setCorreo(request.getParameter("correo"));
    propietario.setNumeroContacto1(request.getParameter("numeroContacto1"));
    propietario.setNumeroContacto2(request.getParameter("numeroContacto2"));

    if ("agregar".equals(action)) {
        // Lógica para agregar un nuevo propietario
        propietarioDAO.agregar(propietario);
        response.sendRedirect("PropietarioController?action=listar");
    } else if ("actualizar".equals(action)) {
        // Lógica para actualizar un propietario existente
        propietarioDAO.actualizar(propietario);
        response.sendRedirect("PropietarioController?action=listar");
    } else if ("eliminar".equals(action)) {
        // Lógica para eliminar un propietario
        String cedulaEliminar = request.getParameter("cedula");
        propietarioDAO.eliminar(cedulaEliminar);
        response.sendRedirect("PropietarioController?action=listar");
    } else {
        // Manejo de acciones no reconocidas
        response.sendRedirect("PropietarioController?action=listar");
    }
}
}