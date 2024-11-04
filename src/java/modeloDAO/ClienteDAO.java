// Archivo: src/java/modeloDAO/ClienteDAO.java
package modeloDAO;

import modeloDTO.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private List<Cliente> listaClientesPrueba;

    public ClienteDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaClientesPrueba = new ArrayList<>();
        
        Cliente cliente1 = new Cliente("123456789", "Carlos Ruiz", "Av. Siempre Viva 742", "1980-01-01", "2000-01-01", "carlos.ruiz@ejemplo.com", "1234567890", "0987654321");
        Cliente cliente2 = new Cliente("987654321", "Lucía Gomez", "Calle Falsa 123", "1990-02-02", "2010-02-02", "lucia.gomez@ejemplo.com", "1122334455", "5566778899");
        
        listaClientesPrueba.add(cliente1);
        listaClientesPrueba.add(cliente2);
    }

    public List<Cliente> listar() {
        return listaClientesPrueba;
    }

    public boolean agregar(Cliente cliente) {
        return listaClientesPrueba.add(cliente);
    }

    public boolean actualizar(Cliente cliente) {
        for (int i = 0; i < listaClientesPrueba.size(); i++) {
            if (listaClientesPrueba.get(i).getCedula().equals(cliente.getCedula())) {
                listaClientesPrueba.set(i, cliente);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String cedula) {
        return listaClientesPrueba.removeIf(cliente -> cliente.getCedula().equals(cedula));
    }

    public Cliente listarPorCedula(String cedula) {
        return listaClientesPrueba.stream().filter(c -> c.getCedula().equals(cedula)).findFirst().orElse(null);
    }
}
