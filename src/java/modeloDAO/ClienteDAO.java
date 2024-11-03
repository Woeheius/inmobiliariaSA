package modeloDAO;

import config.Conexion;
import modeloDTO.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Conexion conexion = new Conexion();

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombreCompleto(rs.getString("nombre_completo"));
                // Mapea los demás campos
                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Cliente cliente) {
        String sql = "INSERT INTO cliente (cedula, nombre_completo, direccion) VALUES (?, ?, ?)";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cliente listarPorId(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombreCompleto(rs.getString("nombre_completo"));
                // Mapea los demás campos
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET cedula = ?, nombre_completo = ?, direccion = ? WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getDireccion());
            ps.setInt(4, cliente.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
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
