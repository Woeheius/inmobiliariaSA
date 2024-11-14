package modeloDAO;

import modeloDTO.Contrato;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class ContratoDAO {
    private List<Contrato> listaContratosPrueba;

    public ContratoDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaContratosPrueba = new ArrayList<>();
        
        // Añadimos algunos contratos de prueba
        Contrato contrato1 = new Contrato("C001", "Contrato de Venta", "Venta", "2023-01-01", "2024-01-01", 100000.0, 5.0, "INM001", "CLI001", "AG001");
        Contrato contrato2 = new Contrato("C002", "Contrato de Alquiler", "Alquiler", "2023-02-01", "2024-02-01", 5000.0, 2.0, "INM002", "CLI002", "AG002");
        Contrato contrato3 = new Contrato("C003", "Contrato de Venta", "Venta", "2023-03-15", "2024-03-15", 150000.0, 4.5, "INM003", "CLI003", "AG001");
        
        listaContratosPrueba.add(contrato1);
        listaContratosPrueba.add(contrato2);
        listaContratosPrueba.add(contrato3);
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

    // Métodos para reportes

    public List<Contrato> listarPorAgente(String cedulaAgente) {
        return listaContratosPrueba.stream()
                .filter(c -> c.getCedulaAgente().equals(cedulaAgente))
                .collect(Collectors.toList());
    }

    public List<Contrato> listarPorCliente(String cedulaCliente) {
        return listaContratosPrueba.stream()
                .filter(c -> c.getCedulaCliente().equals(cedulaCliente))
                .collect(Collectors.toList());
    }

    public List<Contrato> listarPorInmueble(String codigoInmueble) {
        return listaContratosPrueba.stream()
                .filter(c -> c.getCodigoInmueble().equals(codigoInmueble))
                .collect(Collectors.toList());
    }

    public List<Contrato> listarPorTipoYFecha(String tipoContrato, String fechaInicio, String fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inicio = LocalDate.parse(fechaInicio, formatter);
        LocalDate fin = LocalDate.parse(fechaFin, formatter);

        return listaContratosPrueba.stream()
                .filter(c -> c.getTipoContrato().equals(tipoContrato))
                .filter(c -> {
                    LocalDate fechaCreacion = LocalDate.parse(c.getFechaCreacion(), formatter);
                    return !fechaCreacion.isBefore(inicio) && !fechaCreacion.isAfter(fin);
                })
                .collect(Collectors.toList());
    }
}