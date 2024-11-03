package modeloDAO;

import config.Conexion;
import modeloDTO.Inmueble;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InmuebleDAO {
    private Conexion conexion = new Conexion();

    public List<Inmueble> listar() {
        List<Inmueble> lista = new ArrayList<>();
        String sql = "SELECT * FROM inmueble";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
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
                lista.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
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
            e.printStackTrace();
        }
        return false;
    }

    public Inmueble listarPorId(int id) {
        Inmueble inmueble = null;
        String sql = "SELECT * FROM inmueble WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                inmueble = new Inmueble();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmueble;
    }

    public boolean actualizar(Inmueble inmueble) {
        String sql = "UPDATE inmueble SET codigo = ?, descripcion = ?, tipo_inmueble = ?, modalidad_comercializacion = ?, precio_final = ?, estado = ?, cantidad_banos = ?, tamano_m2 = ?, departamento = ?, ciudad = ?, direccion = ? WHERE id = ?";
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
            ps.setInt(12, inmueble.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM inmueble WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
