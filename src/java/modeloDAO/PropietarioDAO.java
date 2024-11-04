package modeloDAO;

import modeloDTO.Propietario;
import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    private static List<Propietario> listaPropietariosPrueba = new ArrayList<>();
    private static boolean datosInicializados = false;

    public PropietarioDAO() {
        if (!datosInicializados) {
            inicializarDatosPrueba();
            datosInicializados = true;
        }
    }

private void inicializarDatosPrueba() {
        Propietario propietario1 = new Propietario();
        propietario1.setCedula("123456789");
        propietario1.setNombreCompleto("Juan Pérez");
        propietario1.setDireccion("Calle 123");
        propietario1.setFechaNacimiento("1980-01-01");
        propietario1.setFechaExpedicion("2000-01-01");
        propietario1.setCorreo("juan@email.com");
        propietario1.setNumeroContacto1("1234567");
        propietario1.setNumeroContacto2("7654321");

        Propietario propietario2 = new Propietario();
        propietario2.setCedula("987654321");
        propietario2.setNombreCompleto("María López");
        propietario2.setDireccion("Avenida 456");
        propietario2.setFechaNacimiento("1985-05-05");
        propietario2.setFechaExpedicion("2005-05-05");
        propietario2.setCorreo("maria@email.com");
        propietario2.setNumeroContacto1("9876543");
        propietario2.setNumeroContacto2("3456789");
        
        listaPropietariosPrueba.add(propietario1);
        listaPropietariosPrueba.add(propietario2);
    }

    public List<Propietario> listar() {
        return listaPropietariosPrueba;
    }

    public boolean agregar(Propietario propietario) {
        return listaPropietariosPrueba.add(propietario);
    }

    public boolean actualizar(Propietario propietario) {
        for (int i = 0; i < listaPropietariosPrueba.size(); i++) {
            if (listaPropietariosPrueba.get(i).getCedula().equals(propietario.getCedula())) {
                listaPropietariosPrueba.set(i, propietario);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String cedula) {
        return listaPropietariosPrueba.removeIf(propietario -> propietario.getCedula().equals(cedula));
    }

    public Propietario listarPorCedula(String cedula) {
        return listaPropietariosPrueba.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }
}