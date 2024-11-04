// Archivo: src/java/modeloDAO/InmuebleDAO.java
package modeloDAO;

import modeloDTO.Inmueble;
import java.util.ArrayList;
import java.util.List;

public class InmuebleDAO {
    private List<Inmueble> listaInmueblesPrueba;

    public InmuebleDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaInmueblesPrueba = new ArrayList<>();
        
        Inmueble inmueble1 = new Inmueble("INM001", "Casa en el centro", "Casa", "Venta", 150000.0, "En oferta", 2, 120.0, "Antioquia", "Medellín", "Calle 10 #10-20");
        Inmueble inmueble2 = new Inmueble("INM002", "Apartamento en la playa", "Apartamento", "Alquiler", 2000.0, "Disponible", 1, 80.0, "Atlántico", "Cartagena", "Av. Playa 123");
        
        listaInmueblesPrueba.add(inmueble1);
        listaInmueblesPrueba.add(inmueble2);
    }

    public List<Inmueble> listar() {
        return listaInmueblesPrueba;
    }

    public boolean agregar(Inmueble inmueble) {
        return listaInmueblesPrueba.add(inmueble);
    }

    public boolean actualizar(Inmueble inmueble) {
        for (int i = 0; i < listaInmueblesPrueba.size(); i++) {
            if (listaInmueblesPrueba.get(i).getCodigo().equals(inmueble.getCodigo())) {
                listaInmueblesPrueba.set(i, inmueble);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String codigo) {
        return listaInmueblesPrueba.removeIf(inmueble -> inmueble.getCodigo().equals(codigo));
    }

    public Inmueble listarPorCodigo(String codigo) {
        return listaInmueblesPrueba.stream().filter(i -> i.getCodigo().equals(codigo)).findFirst().orElse(null);
    }
}
