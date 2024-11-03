package controller;

import modeloDAO.InmuebleDAO;
import modeloDTO.Inmueble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InmuebleController")
public class InmuebleController extends HttpServlet {
    private InmuebleDAO inmuebleDAO = new InmuebleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listar":
                request.setAttribute("listaInmuebles", inmuebleDAO.listar());
                request.getRequestDispatcher("views/inmuebles/listar.jsp").forward(request, response);
                break;
            case "registrar":
                request.getRequestDispatcher("views/inmuebles/registrar.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("inmueble", inmuebleDAO.listarPorId(id));
                request.getRequestDispatcher("views/inmuebles/editar.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                inmuebleDAO.eliminar(idEliminar);
                response.sendRedirect("InmuebleController?action=listar");
                break;
            default:
                response.sendRedirect("InmuebleController?action=listar");
                break;
        }
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
            inmueble.setId(Integer.parseInt(request.getParameter("id")));
            inmuebleDAO.actualizar(inmueble);
        }

        response.sendRedirect("InmuebleController?action=listar");
    }
}
