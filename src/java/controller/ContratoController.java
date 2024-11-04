package controller;

import modeloDAO.ContratoDAO;
import modeloDTO.Contrato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ContratoController")
public class ContratoController extends HttpServlet {
    private ContratoDAO contratoDAO = new ContratoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarContratos(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/contratos/registrar.jsp").forward(request, response);
                break;
            case "editar":
                String codigo = request.getParameter("codigo");
                Contrato contrato = contratoDAO.listarPorCodigo(codigo);
                request.setAttribute("contrato", contrato);
                request.getRequestDispatcher("views/contratos/editar.jsp").forward(request, response);
                break;
case "eliminar":
    String codigoEliminar = request.getParameter("codigo");
    contratoDAO.eliminar(codigoEliminar);
    response.sendRedirect("ContratoController?action=listar");
    break;
            default:
                listarContratos(request, response);
                break;
        }
    }

    private void listarContratos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contrato> listaContratos = contratoDAO.listar();
        request.setAttribute("listaContratos", listaContratos);
        request.getRequestDispatcher("views/contratos/ContratoPrincipal.jsp").forward(request, response);
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
            contratoDAO.actualizar(contrato);
        }

        response.sendRedirect("ContratoController?action=listar");
    }
}