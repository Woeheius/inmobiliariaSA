package controller;

import modeloDAO.ContratoDAO;
import modeloDAO.InmuebleDAO;
import modeloDAO.ClienteDAO;
import modeloDAO.AgenteDAO;
import modeloDTO.Contrato;
import modeloDTO.Inmueble;
import modeloDTO.Cliente;
import modeloDTO.Agente;

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
    private InmuebleDAO inmuebleDAO = new InmuebleDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private AgenteDAO agenteDAO = new AgenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarContratos(request, response);
                break;
            case "registrar":
                cargarDatosParaRegistro(request, response);
                break;
            case "editar":
                cargarDatosParaEdicion(request, response);
                break;
            case "eliminar":
                eliminarContrato(request, response);
                break;
            case "reporte":
                generarReporte(request, response);
                break;
            case "vistaContratos": // Nueva acción para redirigir a la vista de contratos
                redirigirVistaContratos(request, response);
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

    private void cargarDatosParaRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Inmueble> listaInmuebles = inmuebleDAO.listar();
        List<Cliente> listaClientes = clienteDAO.listar();
        List<Agente> listaAgentes = agenteDAO.listar();
        
        request.setAttribute("listaInmuebles", listaInmuebles);
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaAgentes", listaAgentes);
        
        request.getRequestDispatcher("views/contratos/registrar.jsp").forward(request, response);
    }

    private void cargarDatosParaEdicion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Contrato contrato = contratoDAO.listarPorCodigo(codigo);
        
        List<Inmueble> listaInmuebles = inmuebleDAO.listar();
        List<Cliente> listaClientes = clienteDAO.listar();
        List<Agente> listaAgentes = agenteDAO.listar();
        
        request.setAttribute("contrato", contrato);
        request.setAttribute("listaInmuebles", listaInmuebles);
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaAgentes", listaAgentes);
        
        request.getRequestDispatcher("views/contratos/editar.jsp").forward(request, response);
    }

    private void eliminarContrato(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigoEliminar = request.getParameter("codigo");
        contratoDAO.eliminar(codigoEliminar);
        response.sendRedirect("ContratoController?action=listar");
    }

    private void generarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoReporte = request.getParameter("tipoReporte");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        List<Contrato> reporteContratos = null;
        String mensaje = "";

        switch (tipoReporte) {
            case "reportePorAgente":
                String cedulaAgente = request.getParameter("cedulaAgente");
                reporteContratos = contratoDAO.listarPorAgente(cedulaAgente);
                break;
            case "reportePorCliente":
                String cedulaCliente = request.getParameter("cedulaCliente");
                reporteContratos = contratoDAO.listarPorCliente(cedulaCliente);
                break;
 case "reportePorInmueble":
                String codigoInmueble = request.getParameter("codigoInmueble");
                reporteContratos = contratoDAO.listarPorInmueble(codigoInmueble);
                break;
            case "reportePorTipoYFecha":
                String tipoContrato = request.getParameter("tipoContrato");
                reporteContratos = contratoDAO.listarPorTipoYFecha(tipoContrato, fechaInicio, fechaFin);
                break;
        }

        if (reporteContratos != null && !reporteContratos.isEmpty()) {
            double valorTotal = reporteContratos.stream().mapToDouble(Contrato::getValor).sum();
            double promedioValor = valorTotal / reporteContratos.size();

            request.setAttribute("reporteContratos", reporteContratos);
            request.setAttribute("valorTotal", String.format("%.2f", valorTotal));
            request.setAttribute("promedioValor", String.format("%.2f", promedioValor));
        } else {
            mensaje = "No se encontraron contratos para los criterios seleccionados.";
            request.setAttribute("mensaje", mensaje);
        }

        request.getRequestDispatcher("views/contratos/reporteContratos.jsp").forward(request, response);
    }

    private void redirigirVistaContratos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/contratos/contratoController.jsp").forward(request, response);
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
        contrato.setCodigoInmueble(request.getParameter("codigoInmueble"));
        contrato.setCedulaCliente(request.getParameter("cedulaCliente"));
        contrato.setCedulaAgente(request.getParameter("cedulaAgente"));

        if ("agregar".equals(action)) {
            contratoDAO.agregar(contrato);
        } else if ("actualizar".equals(action)) {
            contratoDAO.actualizar(contrato);
        }

        response.sendRedirect("ContratoController?action=listar");
    }
}