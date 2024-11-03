package modeloDAO;

import config.Conexion;
import modeloDTO.Inmueble;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InmuebleDAO {
    private Conexion conexion = new Conexion();
    private List<Inmueble> listaInmueblesPrueba;

    public InmuebleDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaInmueblesPrueba = new ArrayList<>();

        Inmueble inmueble1 = new Inmueble(1, "INM001", "Casa en el centro", "Casa", "Venta", 150000.0, "En oferta", 2, 120.0, "Antioquia", "Medellín", "Calle 10 #10-20");
        Inmueble inmueble2 = new Inmueble(2, "INM002", "Apartamento en la playa", "Apartamento", "Alquiler", 2000.0, "Disponible", 1, 80.0, "Atlántico", "Cartagena", "Av. Playa 123");

        listaInmueblesPrueba.add(inmueble1);
        listaInmueblesPrueba.add(inmueble2);
    }

    public List<Inmueble> listar() {
        List<Inmueble> listaInmuebles = new ArrayList<>();
        String sql = "SELECT * FROM inmueble";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                System.out.println("Conexión a la base de datos fallida. Usando datos de prueba.");
                return listaInmueblesPrueba;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Inmueble inmueble = new Inmueble();
                    inmueble.setId(rs.getInt("id"));
                    inmueble.setCodigo(rs.getString("codigo"));
                    inmueble.setDescripcion(rs.getString("descripcion"));
                    inmueble.setTipoInmueble(rs.getString("tipo_inmueble"));
                    inmueble.setModalidad(rs.getString("modalidad_comercializacion"));
                    inmueble.setPrecio(rs.getDouble("precio_final"));
                    inmueble.setEstado(rs.getString("estado"));
                    inmueble.setCantidadBanos(rs.getInt("cantidad_banos"));
                    inmueble.setTamano(rs.getDouble("tamano_m2"));
                    inmueble.setDepartamento(rs.getString("departamento"));
                    inmueble.setCiudad(rs.getString("ciudad"));
                    inmueble.setDireccion(rs.getString("direccion"));
                    listaInmuebles.add(inmueble);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar inmuebles en la base de datos: " + e.getMessage());
            return listaInmueblesPrueba;
        }
        return listaInmuebles;
    }

    public boolean agregar(Inmueble inmueble) {
        String sql = "INSERT INTO inmueble (codigo, descripcion, tipo_inmueble, modalidad_comercializacion, precio_final, estado, cantidad_banos, tamano_m2, departamento, ciudad, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, inmueble.getCodigo());
            ps.setString(2, inmueble.getDescripcion());
            ps.setString(3, inmueble.getTipoInmueble());
            ps.setString(4, inmueble.getModalidad());
            ps.setDouble(5, inmueble.getPrecio());
            ps.setString(6, inmueble.getEstado());
            ps.setInt(7, inmueble.getCantidadBanos());
            ps.setDouble(8, inmueble.getTamano());
            ps.setString(9, inmueble.getDepartamento());
            ps.setString(10, inmueble.getCiudad());
            ps.setString(11, inmueble.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar inmueble en la base de datos. Agregando a lista de prueba.");
            inmueble.setId(listaInmueblesPrueba.size() + 1);
            return listaInmueblesPrueba.add(inmueble);
        }
    }

    public boolean actualizar(Inmueble inmueble) {
        String sql = "UPDATE inmueble SET codigo = ?, descripcion = ?, tipo_inmueble = ?, modalidad_comercializacion = ?, precio_final = ?, estado = ?, cantidad_banos = ?, tamano_m2 = ?, departamento = ?, ciudad = ?, direccion = ? WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                for (int i = 0; i < listaInmueblesPrueba.size(); i++) {
                    if (listaInmueblesPrueba.get(i).getId() == inmueble.getId()) {
                        listaInmueblesPrueba.set(i, inmueble);
                        return true;
                    }
                }
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, inmueble.getCodigo());
                ps.setString(2, inmueble.getDescripcion());
                ps.setString(3, inmueble.getTipoInmueble());
                ps.setString(4, inmueble.getModalidad());
                ps.setDouble(5, inmueble.getPrecio());
                ps.setString(6, inmueble.getEstado());
                ps.setInt(7, inmueble.getCantidadBanos());
                ps.setDouble(8, inmueble.getTamano());
                ps.setString(9, inmueble.getDepartamento());
                ps.setString(10, inmueble.getCiudad());
                ps.setString(11, inmueble.getDireccion());
                ps.setInt(12, inmueble.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar inmueble: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM inmueble WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                return listaInmueblesPrueba.removeIf(inmueble -> inmueble.getId() == id);
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar inmueble: " + e.getMessage());
            return false;
        }
    }

    public Inmueble listarPorId(int id) {
        String sql = "SELECT * FROM inmueble WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                return listaInmueblesPrueba.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Inmueble inmueble = new Inmueble();
                    inmueble.setId(rs.getInt("id"));
                    inmueble.setCodigo(rs.getString("codigo"));
                    inmueble.setDescripcion(rs.getString("descripcion"));
                    inmueble.setTipoInmueble(rs.getString("tipo_inmueble"));
                    inmueble.setModalidad(rs.getString("modalidad_comercializacion"));
                    inmueble.setPrecio(rs.getDouble("precio_final"));
                    inmueble.setEstado(rs.getString("estado"));
                    inmueble.setCantidadBanos(rs.getInt("cantidad_banos"));
                    inmueble.setTamano(rs.getDouble("tamano_m2"));
                    inmueble.setDepartamento(rs.getString("departamento"));
                    inmueble.setCiudad(rs.getString("ciudad"));
                    inmueble.setDireccion(rs.getString("direccion"));
                    return inmueble;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener inmueble por ID: " + e.getMessage());
        }
        return null;
    }
}
