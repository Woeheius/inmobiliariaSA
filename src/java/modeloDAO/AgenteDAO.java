// Archivo: src/java/modeloDAO/AgenteDAO.java
package modeloDAO;

import modeloDTO.Agente;
import java.util.ArrayList;
import java.util.List;

public class AgenteDAO {
    private List<Agente> listaAgentesPrueba;

    public AgenteDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaAgentesPrueba = new ArrayList<>();
        
        Agente agente1 = new Agente("123456789", "jperez", "12345", "Juan Perez", "Calle Falsa 123", "1990-01-01", "2010-01-01", "juan.perez@ejemplo.com", "1234567890");
        Agente agente2 = new Agente("987654321", "mlopez", "54321", "Maria Lopez", "Avenida Siempre Viva 742", "1985-05-05", "2005-05-05", "maria.lopez@ejemplo.com", "0987654321");
        
        listaAgentesPrueba.add(agente1);
        listaAgentesPrueba.add(agente2);
    }

    public List<Agente> listar() {
        return listaAgentesPrueba;
    }

    public boolean agregar(Agente agente) {
        return listaAgentesPrueba.add(agente);
    }

    public boolean actualizar(Agente agente) {
        for (int i = 0; i < listaAgentesPrueba.size(); i++) {
            if (listaAgentesPrueba.get(i).getCedula().equals(agente.getCedula())) {
                listaAgentesPrueba.set(i, agente);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String cedula) {
        return listaAgentesPrueba.removeIf(agente -> agente.getCedula().equals(cedula));
    }

    public Agente listarPorCedula(String cedula) {
        return listaAgentesPrueba.stream().filter(a -> a.getCedula().equals(cedula)).findFirst().orElse(null);
    }
    
}
