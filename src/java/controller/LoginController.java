package controller;

import modeloDAO.AgenteDAO;
import modeloDAO.ClienteDAO;
import modeloDTO.Agente;
import modeloDTO.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private AgenteDAO agenteDAO = new AgenteDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("username"); // Login del agente o correo del cliente
        String password = request.getParameter("password"); // Contraseña del agente o cédula del cliente

        // Verificar usuario maestro "admin"
        if ("admin".equals(login) && "admin".equals(password)) {
            HttpSession session = request.getSession();
            Agente admin = new Agente();
            admin.setLogin("admin");
            admin.setNombreCompleto("Administrador");
            session.setAttribute("agente", admin);
            response.sendRedirect("menuAgente.jsp");
            return;
        }

        // Verificar si es un agente
        Agente agente = agenteDAO.listarPorLogin(login); // Usar login para buscar al agente
        if (agente != null && agente.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("agente", agente);
            response.sendRedirect("menuAgente.jsp");
            return;
        }

        // Verificar si es un cliente
        Cliente cliente = clienteDAO.listarPorCorreo(login); // Buscar cliente por correo
        if (cliente != null && cliente.getCedula().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("cliente", cliente);
            response.sendRedirect("menuCliente.jsp");
            return;
        }

        // Si no se encuentra el usuario
        request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}