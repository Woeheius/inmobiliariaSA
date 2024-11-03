package controller;

import modeloDAO.ContratoDAO;
import modeloDTO.Contrato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ContratoController")
public class ContratoController extends HttpServlet {
    private ContratoDAO contratoDAO = new ContratoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listar":
                request.setAttribute("listaContratos", contratoDAO.listar());
                request.getRequestDispatcher("views/contratos/listar.jsp").forward(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/contratos/registrar.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("contrato", contratoDAO.listarPorId(id));
                request.getRequestDispatcher("views/contratos/editar.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                contratoDAO.eliminar(idEliminar);
                response.sendRedirect("ContratoController?action=listar");
                break;
            default:
                response.sendRedirect("ContratoController?action=listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        Contrato contrato = new Contrato();
        contrato.setCodigo(request.getParameter("codigo"));
        contrato.setDescripcion(request.getParameter("descripcion"));
        contrato.setTipoContrato(request.getParameter("tipoContrato"));
        contrato.setFechaCreacion(request.getParameter("fechaCreacion"));
        contrato.setFechaExpiracion(request.getParameter("fechaExpiracion"));
        contrato.setValor(Double.parseDouble(request.getParameter("valor")));
        contrato.setPorcentajeComision(Double.parseDouble(request.getParameter("porcentajeComision")));

        if ("agregar".equals(action)) {
            contratoDAO.agregar(contrato);
        } else if ("actualizar".equals(action)) {
            contrato.setId(Integer.parseInt(request.getParameter("id")));
            contratoDAO.actualizar(contrato);
        }

        response.sendRedirect("ContratoController?action=listar");
    }
}
