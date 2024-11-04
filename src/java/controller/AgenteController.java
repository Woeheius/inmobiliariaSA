package controller;

import modeloDAO.AgenteDAO;
import modeloDTO.Agente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AgenteController")
public class AgenteController extends HttpServlet {
    private AgenteDAO agenteDAO = new AgenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarAgentes(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/agentes/registrar.jsp").forward(request, response);
                break;
            case "editar":
                String cedula = request.getParameter("cedula");
                Agente agente = agenteDAO.listarPorCedula(cedula);
                request.setAttribute("agente", agente);
                request.getRequestDispatcher("views/agentes/editar.jsp").forward(request, response);
                break;
            case "eliminar":
                String cedulaEliminar = request.getParameter("cedula");
                agenteDAO.eliminar(cedulaEliminar);
                response.sendRedirect("AgenteController?action=listar");
                break;
            default:
                listarAgentes(request, response);
                break;
        }
    }

    private void listarAgentes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Agente> listaAgentes = agenteDAO.listar();
        request.setAttribute("listaAgentes", listaAgentes);
        request.getRequestDispatcher("views/agentes/AgentePrincipal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        Agente agente = new Agente();
        agente.setCedula(request.getParameter("cedula"));
        agente.setLogin(request.getParameter("login"));
        agente.setNombreCompleto(request.getParameter("nombreCompleto"));
        agente.setCorreo(request.getParameter("correo"));
        agente.setCelular(request.getParameter("celular"));
        agente.setFechaNacimiento(request.getParameter("fechaNacimiento"));
        agente.setFechaExpedicion(request.getParameter("fechaExpedicion"));
        agente.setDireccion(request.getParameter("direccion"));
        agente.setPassword(request.getParameter("password"));

        if ("agregar".equals(action)) {
            agenteDAO.agregar(agente);
        } else if ("actualizar".equals(action)) {
            agenteDAO.actualizar(agente);
        }

        response.sendRedirect("AgenteController?action=listar");
    }
}
