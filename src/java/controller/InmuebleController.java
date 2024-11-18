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
    private static final InmuebleDAO inmuebleDAO = new InmuebleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("DoGet - Action recibida: " + action);

        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarInmuebles(request, response);
                break;
            case "registrar":
                System.out.println("Redirigiendo a página de registro");
                request.getRequestDispatcher("views/inmuebles/registrar.jsp").forward(request, response);
                break;
            case "editar":
                String codigo = request.getParameter("codigo");
                System.out.println("Editando inmueble con código: " + codigo);
                Inmueble inmueble = inmuebleDAO.listarPorCodigo(codigo);
                if (inmueble != null) {
                    System.out.println("Inmueble encontrado para editar: " + inmueble.getDescripcion());
                    request.setAttribute("inmueble", inmueble);
                    request.getRequestDispatcher("views/inmuebles/editar.jsp").forward(request, response);
                } else {
                    System.out.println("No se encontró el inmueble para editar");
                    response.sendRedirect("InmuebleController?action=listar");
                }
                break;
            case "eliminar":
                String codigoEliminar = request.getParameter("codigo");
                inmuebleDAO.eliminar(codigoEliminar);
                response.sendRedirect("InmuebleController?action=listar");
                break;
            case "vistaInmuebles": // Nueva acción para redirigir a la vista de inmuebles
                redirigirVistaInmuebles(request, response);
                break;
            default:
                System.out.println("Acción no reconocida, redirigiendo a listar");
                listarInmuebles(request, response);
                break;
        }
    }

    private void listarInmuebles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ejecutando listarInmuebles()");
        List<Inmueble> listaInmuebles = inmuebleDAO.listar();
        System.out.println("Cantidad de inmuebles en la lista: " + (listaInmuebles != null ? listaInmuebles.size() : "null"));
        request.setAttribute("listaInmuebles", listaInmuebles);
        request.getRequestDispatcher("views/inmuebles/InmueblePrincipal.jsp").forward(request, response);
    }

    private void redirigirVistaInmuebles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/inmuebles/inmuebleController.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("DoPost - Action recibida: " + action);

        try {
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
                System.out.println("Agregando nuevo inmueble con código: " + inmueble.getCodigo());
                boolean agregado = inmuebleDAO.agregar(inmueble);
                System.out.println("Resultado de agregar: " + agregado);
            } else if ("actualizar".equals(action)) {
                System.out.println("Actualizando inmueble con código: " + inmueble.getCodigo());
                boolean actualizado = inmuebleDAO.actualizar(inmueble);
                System.out.println("Resultado de actualización: " + actualizado);
            }

            response.sendRedirect("InmuebleController?action=listar");
        } catch (Exception e) {
            System.out.println("Error en doPost: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("InmuebleController?action=listar");
        }
    }
}