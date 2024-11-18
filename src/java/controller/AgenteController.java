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
    private static final AgenteDAO agenteDAO = new AgenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("DoGet - Action recibida: " + action);

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarAgentes(request, response);
                break;
            case "registrar":
                System.out.println("Redirigiendo a página de registro");
                request.getRequestDispatcher("views/agentes/registrar.jsp").forward(request, response);
                break;
            case "editar":
                String cedula = request.getParameter("cedula");
                System.out.println("Editando agente con cédula: " + cedula);
                Agente agente = agenteDAO.listarPorCedula(cedula);
                if (agente != null) {
                    System.out.println("Agente encontrado para editar: " + agente.getNombreCompleto());
                    request.setAttribute("agente", agente);
                    request.getRequestDispatcher("views/agentes/editar.jsp").forward(request, response);
                } else {
                    System.out.println("No se encontró el agente para editar");
                    response.sendRedirect("AgenteController?action=listar");
                }
                break;
case "eliminar":
    String cedulaEliminar = request.getParameter("cedula");
    agenteDAO.eliminar(cedulaEliminar);
    response.sendRedirect("AgenteController?action=listar");
    break;
            default:
                System.out.println("Acción no reconocida, redirigiendo a listar");
                listarAgentes(request, response);
                break;
        }
    }

    private void listarAgentes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ejecutando listarAgentes()");
        List<Agente> listaAgentes = agenteDAO.listar();
        System.out.println("Cantidad de agentes en la lista: " + (listaAgentes != null ? listaAgentes.size() : "null"));
        request.setAttribute("listaAgentes", listaAgentes);
        request.getRequestDispatcher("views/agentes/AgentePrincipal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("DoPost - Action recibida: " + action);

        try {
            Agente agente = new Agente();
            agente.setCedula(request.getParameter("cedula"));
            agente.setLogin(request.getParameter("login"));
            agente.setPassword(request.getParameter("password"));
            agente.setNombreCompleto(request.getParameter("nombreCompleto"));
            agente.setDireccion(request.getParameter("direccion"));
            agente.setFechaNacimiento(request.getParameter("fechaNacimiento"));
            agente.setFechaExpedicion(request.getParameter("fechaExpedicion"));
            agente.setCorreo(request.getParameter("correo"));
            agente.setCelular(request.getParameter("celular"));

            if ("agregar".equals(action)) {
                System.out.println("Agregando nuevo agente con cédula: " + agente.getCedula());
                boolean agregado = agenteDAO.insertar(agente);
                System.out.println("Resultado de agregar: " + agregado);
            } else if ("actualizar".equals(action)) {
                System.out.println("Actualizando agente con cédula: " + agente.getCedula());
                boolean actualizado = agenteDAO.actualizar(agente);
                System.out.println("Resultado de actualización: " + actualizado);
            }

            response.sendRedirect("AgenteController?action=listar");
        } catch (Exception e) {
            System.out.println("Error en doPost: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("AgenteController?action=listar");
        }
    }
}