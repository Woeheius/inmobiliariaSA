// Archivo: src/java/modeloDAO/PropietarioDAO.java
package modeloDAO;

import modeloDTO.Propietario;
import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    private List<Propietario> listaPropietariosPrueba;

    public PropietarioDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaPropietariosPrueba = new ArrayList<>();
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
        return listaPropietariosPrueba.stream().filter(p -> p.getCedula().equals(cedula)).findFirst().orElse(null);
    }
}
