package controller;

import modeloDAO.PropietarioDAO;
import modeloDTO.Propietario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PropietarioController")
public class PropietarioController extends HttpServlet {
    private PropietarioDAO propietarioDAO = new PropietarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listar":
                request.setAttribute("listaPropietarios", propietarioDAO.listar());
                request.getRequestDispatcher("views/propietarios/listar.jsp").forward(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/propietarios/registrar.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("propietario", propietarioDAO.listarPorId(id));
                request.getRequestDispatcher("views/propietarios/editar.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                propietarioDAO.eliminar(idEliminar);
                response.sendRedirect("PropietarioController?action=listar");
                break;
            default:
                response.sendRedirect("PropietarioController?action=listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

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
            propietarioDAO.agregar(propietario);
        } else if ("actualizar".equals(action)) {
            propietario.setId(Integer.parseInt(request.getParameter("id")));
            propietarioDAO.actualizar(propietario);
        }

        response.sendRedirect("PropietarioController?action=listar");
    }
}
