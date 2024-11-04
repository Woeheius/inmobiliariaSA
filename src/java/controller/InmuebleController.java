package controller;

import modeloDAO.InmuebleDAO;
import modeloDTO.Inmueble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/InmuebleController")
public class InmuebleController extends HttpServlet {
    private InmuebleDAO inmuebleDAO = new InmuebleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarInmuebles(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/inmuebles/registrar.jsp").forward(request, response);
                break;
            case "editar":
                String id = request.getParameter("codigo");
                Inmueble inmueble = inmuebleDAO.listarPorCodigo(id);
                request.setAttribute("inmueble", inmueble);
                request.getRequestDispatcher("views/inmuebles/editar.jsp").forward(request, response);
                break;
            case "eliminar":
                String idEliminar = request.getParameter("codigo");
                inmuebleDAO.eliminar(idEliminar);
                response.sendRedirect("InmuebleController?action=listar");
                break;
            default:
                listarInmuebles(request, response);
                break;
        }
    }

    private void listarInmuebles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Inmueble> listaInmuebles = inmuebleDAO.listar();
        request.setAttribute("listaInmuebles", listaInmuebles);
        request.getRequestDispatcher("views/inmuebles/InmueblePrincipal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        Inmueble inmueble = new Inmueble();
        inmueble.setCodigo(request.getParameter("codigo"));
        inmueble.setDescripcion(request.getParameter("descripcion"));
        inmueble.setTipoInmueble(request.getParameter("tipoInmueble"));
        inmueble.setModalidad(request.getParameter("modalidad"));
        inmueble.setPrecio(Double.parseDouble(request.getParameter("precio")));
        inmueble.setEstado(request.getParameter("estado"));
        inmueble.setCantidadBanos(Integer.parseInt(request.getParameter("cantidadBanos")));
        inmueble.setTamano(Double.parseDouble(request.getParameter("tamano")));
        inmueble.setDepartamento(request.getParameter("departamento"));
        inmueble.setCiudad(request.getParameter("ciudad"));
        inmueble.setDireccion(request.getParameter("direccion"));

        if ("agregar".equals(action)) {
            inmuebleDAO.agregar(inmueble);
        } else if ("actualizar".equals(action)) {
            inmueble.setCodigo(request.getParameter("codigo"));
            inmuebleDAO.actualizar(inmueble);
        }

        response.sendRedirect("InmuebleController?action=listar");
    }
}
