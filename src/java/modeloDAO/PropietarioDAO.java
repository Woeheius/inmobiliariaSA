package modeloDAO;

import config.Conexion;
import modeloDTO.Propietario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    private Conexion conexion = new Conexion();

    public List<Propietario> listar() {
        List<Propietario> lista = new ArrayList<>();
        String sql = "SELECT * FROM propietario";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Propietario propietario = new Propietario();
                propietario.setId(rs.getInt("id"));
                propietario.setCedula(rs.getString("cedula"));
                propietario.setNombreCompleto(rs.getString("nombre_completo"));
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                propietario.setFechaExpedicion(rs.getString("fecha_expedicion"));
                propietario.setCorreo(rs.getString("correo_electronico"));
                propietario.setNumeroContacto1(rs.getString("numero_contacto1"));
                propietario.setNumeroContacto2(rs.getString("numero_contacto2"));
                lista.add(propietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Propietario propietario) {
        String sql = "INSERT INTO propietario (cedula, nombre_completo, direccion, fecha_nacimiento, fecha_expedicion, correo_electronico, numero_contacto1, numero_contacto2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, propietario.getCedula());
            ps.setString(2, propietario.getNombreCompleto());
            ps.setString(3, propietario.getDireccion());
            ps.setString(4, propietario.getFechaNacimiento());
            ps.setString(5, propietario.getFechaExpedicion());
            ps.setString(6, propietario.getCorreo());
            ps.setString(7, propietario.getNumeroContacto1());
            ps.setString(8, propietario.getNumeroContacto2());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Propietario listarPorId(int id) {
        Propietario propietario = null;
        String sql = "SELECT * FROM propietario WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                propietario = new Propietario();
                propietario.setId(rs.getInt("id"));
                propietario.setCedula(rs.getString("cedula"));
                propietario.setNombreCompleto(rs.getString("nombre_completo"));
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                propietario.setFechaExpedicion(rs.getString("fecha_expedicion"));
                propietario.setCorreo(rs.getString("correo_electronico"));
                propietario.setNumeroContacto1(rs.getString("numero_contacto1"));
                propietario.setNumeroContacto2(rs.getString("numero_contacto2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propietario;
    }

    public boolean actualizar(Propietario propietario) {
        String sql = "UPDATE propietario SET cedula = ?, nombre_completo = ?, direccion = ?, fecha_nacimiento = ?, fecha_expedicion = ?, correo_electronico = ?, numero_contacto1 = ?, numero_contacto2 = ? WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, propietario.getCedula());
            ps.setString(2, propietario.getNombreCompleto());
            ps.setString(3, propietario.getDireccion());
            ps.setString(4, propietario.getFechaNacimiento());
            ps.setString(5, propietario.getFechaExpedicion());
            ps.setString(6, propietario.getCorreo());
            ps.setString(7, propietario.getNumeroContacto1());
            ps.setString(8, propietario.getNumeroContacto2());
            ps.setInt(9, propietario.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM propietario WHERE id = ?";
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
