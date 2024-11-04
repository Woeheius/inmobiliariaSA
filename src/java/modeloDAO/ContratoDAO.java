// Archivo: src/java/modeloDAO/ContratoDAO.java
package modeloDAO;

import modeloDTO.Contrato;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {
    private List<Contrato> listaContratosPrueba;

    public ContratoDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaContratosPrueba = new ArrayList<>();
        
        // Añadimos algunos contratos de prueba
        Contrato contrato1 = new Contrato("C001", "Contrato de Venta", "Venta", "2023-01-01", "2024-01-01", 100000.0, 5.0);
        Contrato contrato2 = new Contrato("C002", "Contrato de Alquiler", "Alquiler", "2023-02-01", "2024-02-01", 5000.0, 2.0);
        
        listaContratosPrueba.add(contrato1);
        listaContratosPrueba.add(contrato2);
    }

    public List<Contrato> listar() {
        return listaContratosPrueba;
    }

    public boolean agregar(Contrato contrato) {
        return listaContratosPrueba.add(contrato);
    }

    public boolean actualizar(Contrato contrato) {
        for (int i = 0; i < listaContratosPrueba.size(); i++) {
            if (listaContratosPrueba.get(i).getCodigo().equals(contrato.getCodigo())) {
                listaContratosPrueba.set(i, contrato);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String codigo) {
        return listaContratosPrueba.removeIf(contrato -> contrato.getCodigo().equals(codigo));
    }

    public Contrato listarPorCodigo(String codigo) {
        return listaContratosPrueba.stream()
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}