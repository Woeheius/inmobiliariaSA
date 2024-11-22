package modeloDAO;

import config.Conexion;
import modeloDTO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conexion;

    public ClienteDAO() {
        Conexion conn = new Conexion();
        this.conexion = conn.getConexion();
    }

    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCedula(String.valueOf(rs.getInt("cedula_cliente")));
                cliente.setNombreCompleto(rs.getString("nombrecompleto_cliente"));
                cliente.setCorreo(rs.getString("correoelectronico_cliente"));
                cliente.setDireccion(rs.getString("direccion_cliente"));
                cliente.setFechaNacimiento(rs.getString("fechadenacimiento_cliente"));
                cliente.setFechaExpedicion(rs.getString("fechadexpedicion_cliente"));
                cliente.setNumeroContacto1(String.valueOf(rs.getInt("contacto1_cliente")));
                cliente.setNumeroContacto2(String.valueOf(rs.getInt("contacto2_cliente")));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

public boolean agregar(Cliente cliente) {
    String sql = "INSERT INTO cliente (cedula_cliente, nombrecompleto_cliente, correoelectronico_cliente, direccion_cliente, fechadenacimiento_cliente, fechadexpedicion_cliente, contacto1_cliente, contacto2_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, Integer.parseInt(cliente.getCedula()));
        ps.setString(2, cliente.getNombreCompleto());
        ps.setString(3, cliente.getCorreo());
        ps.setString(4, cliente.getDireccion());
        ps.setString(5, cliente.getFechaNacimiento());
        ps.setString(6, cliente.getFechaExpedicion());
        ps.setInt(7, Integer.parseInt(cliente.getNumeroContacto1()));
        ps.setInt(8, Integer.parseInt(cliente.getNumeroContacto2()));
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean actualizar(Cliente cliente) {
    String sql = "UPDATE cliente SET nombrecompleto_cliente = ?, correoelectronico_cliente = ?, direccion_cliente = ?, fechadenacimiento_cliente = ?, fechadexpedicion_cliente = ?, contacto1_cliente = ?, contacto2_cliente = ? WHERE cedula_cliente = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, cliente.getNombreCompleto());
        ps.setString(2, cliente.getCorreo());
        ps.setString(3, cliente.getDireccion());
        ps.setString(4, cliente.getFechaNacimiento());
        ps.setString(5, cliente.getFechaExpedicion());
        ps.setInt(6, Integer.parseInt(cliente.getNumeroContacto1()));
        ps.setInt(7, Integer.parseInt(cliente.getNumeroContacto2()));
        ps.setInt(8, Integer.parseInt(cliente.getCedula()));
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean eliminar(String cedula) {
    String sql = "DELETE FROM cliente WHERE cedula_cliente = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, Integer.parseInt(cedula));
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public Cliente listarPorCedula(String cedula) {
    Cliente cliente = null;
    String sql = "SELECT * FROM cliente WHERE cedula_cliente = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, Integer.parseInt(cedula));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setCedula(String.valueOf(rs.getInt("cedula_cliente")));
            cliente.setNombreCompleto(rs.getString("nombrecompleto_cliente"));
            cliente.setCorreo(rs.getString("correoelectronico_cliente"));
            cliente.setDireccion(rs.getString("direccion_cliente"));
            cliente.setFechaNacimiento(rs.getString("fechadenacimiento_cliente"));
            cliente.setFechaExpedicion(rs.getString("fechadexpedicion_cliente"));
            cliente.setNumeroContacto1(String.valueOf(rs.getInt("contacto1_cliente")));
            cliente.setNumeroContacto2(String.valueOf(rs.getInt("contacto2_cliente")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cliente;
}
}