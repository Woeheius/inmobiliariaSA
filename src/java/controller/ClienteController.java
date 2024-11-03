package controller;

import modeloDAO.ClienteDAO;
import modeloDTO.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listar":
                request.setAttribute("listaClientes", clienteDAO.listar());
                request.getRequestDispatcher("views/clientes/listar.jsp").forward(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/clientes/registrar.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("cliente", clienteDAO.listarPorId(id));
                request.getRequestDispatcher("views/clientes/editar.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                clienteDAO.eliminar(idEliminar);
                response.sendRedirect("ClienteController?action=listar");
                break;
            default:
                response.sendRedirect("ClienteController?action=listar");
                break;
        }
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
            cliente.setId(Integer.parseInt(request.getParameter("id")));
            clienteDAO.actualizar(cliente);
        }

        response.sendRedirect("ClienteController?action=listar");
    }
}
