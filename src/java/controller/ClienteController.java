package controller;

import modeloDAO.ClienteDAO;
import modeloDTO.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarClientes(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/clientes/registrar.jsp").forward(request, response);
                break;
            case "editar":
                String cedula = request.getParameter("cedula");
                Cliente cliente = clienteDAO.listarPorCedula(cedula);
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("views/clientes/editar.jsp").forward(request, response);
                break;
case "eliminar":
    String cedulaEliminar = request.getParameter("cedula");
    clienteDAO.eliminar(cedulaEliminar);
    response.sendRedirect("ClienteController?action=listar");
    break;
            default:
                listarClientes(request, response);
                break;
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = clienteDAO.listar();
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("views/clientes/ClientePrincipal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        Cliente cliente = new Cliente();
        cliente.setCedula(request.getParameter("cedula"));
        cliente.setNombreCompleto(request.getParameter("nombreCompleto"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setFechaNacimiento(request.getParameter("fechaNacimiento"));
        cliente.setFechaExpedicion(request.getParameter("fechaExpedicion"));
        cliente.setCorreo(request.getParameter("correo"));
        cliente.setNumeroContacto1(request.getParameter("numeroContacto1"));
        cliente.setNumeroContacto2(request.getParameter("numeroContacto2"));

        if ("agregar".equals(action)) {
            clienteDAO.agregar(cliente);
        } else if ("actualizar".equals(action)) {
            clienteDAO.actualizar(cliente);
        }

        response.sendRedirect("ClienteController?action=listar");
    }
}