package modeloDAO;

import config.Conexion;
import modeloDTO.Inmueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InmuebleDAO {
    private Connection conexion;

    public InmuebleDAO() {
        Conexion conn = new Conexion();
        this.conexion = conn.getConexion();
    }

    public List<Inmueble> listar() {
        List<Inmueble> listaInmuebles = new ArrayList<>();
        String sql = "SELECT * FROM Inmueble";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setCodigo(String.valueOf(rs.getInt("codigo_Inmueble")));
                inmueble.setDireccion(rs.getString("direccion_Inmueble"));
                inmueble.setDescripcion(rs.getString("descripcion_Inmueble"));
                inmueble.setTipoInmueble(rs.getString("tipodeinmueble_Inmueble"));
                inmueble.setModalidad(rs.getString("modalidaddecomercializacion_Inmueble"));
                inmueble.setPrecio(rs.getInt("preciofinal_Inmueble"));
                inmueble.setTamano(rs.getInt("tamañoenmetroscuadrados_Inmueble"));
                inmueble.setCiudad(String.valueOf(rs.getInt("codigo_ciudad")));
                listaInmuebles.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaInmuebles;
    }

    public boolean agregar(Inmueble inmueble) {
        String sql = "INSERT INTO Inmueble (codigo_Inmueble, direccion_Inmueble, descripcion_Inmueble, tipodeinmueble_Inmueble, modalidaddecomercializacion_Inmueble, preciofinal_Inmueble, fotodeinmueble_Inmueble, cantidaddebaños_Inmueble, tamañoenmetroscuadrados_Inmueble, codigo_ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(inmueble.getCodigo()));
            ps.setString(2, inmueble.getDireccion());
            ps.setString(3, inmueble.getDescripcion());
            ps.setString(4, inmueble.getTipoInmueble());
            ps.setString(5, inmueble.getModalidad());
            ps.setInt(6, (int) inmueble.getPrecio());
            ps.setInt(9, (int) inmueble.getTamano());
            ps.setInt(10, Integer.parseInt(inmueble.getCiudad()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Inmueble inmueble) {
        String sql = "UPDATE Inmueble SET direccion_Inmueble = ?, descripcion_Inmueble = ?, tipodeinmueble_Inmueble = ?, modalidaddecomercializacion_Inmueble = ?, preciofinal_Inmueble = ?, fotodeinmueble_Inmueble = ?, cantidaddebaños_Inmueble = ?, tamañoenmetroscuadr ados_Inmueble = ?, codigo_ciudad = ? WHERE codigo_Inmueble = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, inmueble.getDireccion());
            ps.setString(2, inmueble.getDescripcion());
            ps.setString(3, inmueble.getTipoInmueble());
            ps.setString(4, inmueble.getModalidad());
            ps.setInt(5, (int) inmueble.getPrecio());
            ps.setInt(8, (int) inmueble.getTamano());
            ps.setInt(9, Integer.parseInt(inmueble.getCiudad()));
            ps.setInt(10, Integer.parseInt(inmueble.getCodigo()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM Inmueble WHERE codigo_Inmueble = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Inmueble listarPorCodigo(String codigo) {
        Inmueble inmueble = null;
        String sql = "SELECT * FROM Inmueble WHERE codigo_Inmueble = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                inmueble = new Inmueble();
                inmueble.setCodigo(String.valueOf(rs.getInt("codigo_Inmueble")));
                inmueble.setDireccion(rs.getString("direccion_Inmueble"));
                inmueble.setDescripcion(rs.getString("descripcion_Inmueble"));
                inmueble.setTipoInmueble(rs.getString("tipodeinmueble_Inmueble"));
                inmueble.setModalidad(rs.getString("modalidaddecomercializacion_Inmueble"));
                inmueble.setPrecio(rs.getInt("preciofinal_Inmueble"));
                inmueble.setTamano(rs.getInt("tamañoenmetroscuadrados_Inmueble"));
                inmueble.setCiudad(String.valueOf(rs.getInt("codigo_ciudad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmueble;
    }
}