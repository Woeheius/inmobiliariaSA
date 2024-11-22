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
    private static final long serialVersionUID = 1L;
    private InmuebleDAO inmuebleDAO;

    public InmuebleController() {
        inmuebleDAO = new InmuebleDAO();
    }

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    
    if ("listar".equals(action)) {
        // Obtener listas de inmuebles de clientes y de la inmobiliaria
        List<Inmueble> listaInmueblesClientes = inmuebleDAO.listarInmueblesClientes();
        List<Inmueble> listaInmueblesInmobiliaria = inmuebleDAO.listarInmueblesInmobiliaria();
        
        // Agregar las listas al request
        request.setAttribute("listaInmueblesClientes", listaInmueblesClientes);
        request.setAttribute("listaInmueblesInmobiliaria", listaInmueblesInmobiliaria);
        
        // Redirigir a la vista correspondiente
        request.getRequestDispatcher("views/inmuebles/InmueblePrincipal.jsp").forward(request, response);
    } else if ("registrar".equals(action)) {
        request.getRequestDispatcher("views/inmuebles/registrar.jsp").forward(request, response);
    } else if ("editar".equals(action)) {
        String codigo = request.getParameter("codigo");
        Inmueble inmueble = inmuebleDAO.listarPorCodigo(codigo);
        request.setAttribute("inmueble", inmueble);
        request.getRequestDispatcher("views/inmuebles/editar.jsp").forward(request, response);
    } else if ("eliminar".equals(action)) {
        String codigoEliminar = request.getParameter("codigo");
        inmuebleDAO.eliminar(codigoEliminar);
        response.sendRedirect("InmuebleController?action=listar");
    } else {
        response.sendRedirect("InmuebleController?action=listar");
    }
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Inmueble inmueble = new Inmueble();
        
        // Obtener los datos del inmueble desde el formulario
        inmueble.setCodigo(request.getParameter("codigo"));
        inmueble.setDireccion(request.getParameter("direccion"));
        inmueble.setDescripcion(request.getParameter("descripcion"));
        inmueble.setTipoInmueble(request.getParameter("tipoInmueble"));
        inmueble.setModalidad(request.getParameter("modalidad"));
        inmueble.setPrecio(Double.parseDouble(request.getParameter("precio")));
        inmueble.setCantidadBanos(Integer.parseInt(request.getParameter("cantidadBanos")));
        inmueble.setTamano(Double.parseDouble(request.getParameter("tamano")));
        inmueble.setCiudad(request.getParameter("ciudad"));

        if ("agregar".equals(action)) {
            inmuebleDAO.agregar(inmueble);
            response.sendRedirect("InmuebleController?action=listar");
        } else if ("actualizar".equals(action)) {
            inmuebleDAO.actualizar(inmueble);
            response.sendRedirect("InmuebleController?action=listar");
        } else {
            response.sendRedirect("InmuebleController?action=listar");
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
}