package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloDTO.Inmueble;

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
                inmueble.setPrecio(rs.getDouble("preciofinal_Inmueble"));
                inmueble.setTamano(rs.getDouble("tamañoenmetroscuadrados_Inmueble"));
                inmueble.setCiudad(rs.getString("codigo_ciudad"));
                listaInmuebles.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaInmuebles;
    }

    public boolean agregar(Inmueble inmueble) {
        String sql = "INSERT INTO Inmueble (codigo_Inmueble, direccion_Inmueble, descripcion_Inmueble, tipodeinmueble_Inmueble, modalidaddecomercializacion_Inmueble, preciofinal_Inmueble, cantidaddebaños_Inmueble, tamañoenmetroscuadrados_Inmueble, codigo_ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(inmueble.getCodigo()));
            ps.setString(2, inmueble.getDireccion());
            ps.setString(3, inmueble.getDescripcion());
            ps.setString(4, inmueble.getTipoInmueble());
            ps.setString(5, inmueble.getModalidad());
            ps.setDouble(6, inmueble.getPrecio());
            ps.setInt(7, inmueble.getCantidadBanos());
            ps.setDouble(8, inmueble.getTamano());
            ps.setInt(9, Integer.parseInt(inmueble.getCiudad()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Inmueble inmueble) {
        String sql = "UPDATE Inmueble SET direccion_Inmueble = ?, descripcion_Inmueble = ?, tipodeinmueble_Inmueble = ?, modalidaddecomercializacion_Inmueble = ?, preciofinal_Inmueble = ?, cantidaddebaños_Inmueble = ?, tamañoenmetroscuadrados_Inmueble = ?, codigo_ciudad = ?, tipo_inmueble = ? WHERE codigo_Inmueble = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, inmueble.getDireccion());
            ps.setString(2, inmueble.getDescripcion());
            ps.setString(3, inmueble.getTipoInmueble());
            ps.setString(4, inmueble.getModalidad());
            ps.setDouble(5, inmueble.getPrecio());
            ps.setInt(6, inmueble.getCantidadBanos());
            ps.setDouble(7, inmueble.getTamano());
            ps.setInt(8, Integer.parseInt(inmueble.getCiudad()));
           // ps.setString(9, inmueble.getTipoPropiedad());// Agregar el nuevo campo
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
    public List<Inmueble> listarInmueblesClientes() {
        List<Inmueble> listaInmueblesClientes = new ArrayList<>();
        String sql = "SELECT i.* FROM Inmueble i JOIN tienepropietario tp ON i.codigo_Inmueble = tp.codigo_Inmueble";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setCodigo(String.valueOf(rs.getInt("codigo_Inmueble")));
                inmueble.setDireccion(rs.getString("direccion_Inmueble"));
                inmueble.setDescripcion(rs.getString("descripcion_Inmueble"));
                inmueble.setTipoInmueble(rs.getString("tipodeinmueble_Inmueble"));
                inmueble.setModalidad(rs.getString("modalidaddecomercializacion_Inmueble"));
                inmueble.setPrecio(rs.getDouble("preciofinal_Inmueble"));
                inmueble.setTamano(rs.getDouble("tamañoenmetroscuadrados_Inmueble"));
                inmueble.setCiudad(rs.getString("codigo_ciudad"));
                listaInmueblesClientes.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaInmueblesClientes;
    }

    public List<Inmueble> listarInmueblesInmobiliaria() {
        List<Inmueble> listaInmueblesInmobiliaria = new ArrayList<>();
        String sql = "SELECT i.* FROM Inmueble i JOIN Inmuebleconpropietario icp ON i.codigo_Inmueble = icp.codigo_Inmueble";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setCodigo(String.valueOf(rs.getInt("codigo_Inmueble")));
                inmueble.setDireccion(rs.getString("direccion_Inmueble"));
                inmueble.setDescripcion(rs.getString("descripcion_Inmueble"));
                inmueble.setTipoInmueble(rs.getString("tipodeinmueble_Inmueble"));
                inmueble.setModalidad(rs.getString("modalidaddecomercializacion_Inmueble"));
                inmueble.setPrecio(rs.getDouble("preciofinal_Inmueble"));
                inmueble.setTamano(rs.getDouble("tamañoenmetroscuadrados_Inmueble"));
                inmueble.setCiudad(rs.getString("codigo_ciudad"));
                listaInmueblesInmobiliaria.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaInmueblesInmobiliaria;
    }

}