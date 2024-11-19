package modeloDAO;

import config.Conexion;
import modeloDTO.Propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    private Connection conexion;

    public PropietarioDAO() {
        Conexion conn = new Conexion();
        this.conexion = conn.getConexion();
    }

    public List<Propietario> listar() {
        List<Propietario> listaPropietarios = new ArrayList<>();
        String sql = "SELECT * FROM propietario";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Propietario propietario = new Propietario();
                propietario.setCedula(String.valueOf(rs.getInt("cedula_propietario")));
                propietario.setNombreCompleto(rs.getString("nombre_propietario"));
                propietario.setCorreo(rs.getString("correoelectronico_propietario"));
                propietario.setDireccion(rs.getString("direccion_propietario"));
                propietario.setFechaExpedicion(rs.getString("fechadexpedicion_propietario"));
                propietario.setNumeroContacto1(String.valueOf(rs.getInt("contacto1_propietario")));
                propietario.setNumeroContacto2(String.valueOf(rs.getInt("contacto2_propietario")));
                listaPropietarios.add(propietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPropietarios;
    }

    public boolean agregar(Propietario propietario) {
        String sql = "INSERT INTO propietario (cedula_propietario, nombre_propietario, correoelectronico_propietario, direccion_propietario, fechadexpedicion_propietario, contacto1_propietario, contacto2_propietario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(propietario.getCedula()));
            ps.setString(2, propietario.getNombreCompleto());
            ps.setString(3, propietario.getCorreo());
            ps.setString(4, propietario.getDireccion());
            ps.setString(5, propietario.getFechaExpedicion());
            ps.setInt(6, Integer.parseInt(propietario.getNumeroContacto1()));
            ps.setInt(7, Integer.parseInt(propietario.getNumeroContacto2()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Propietario propietario) {
        String sql = "UPDATE propietario SET nombre_propietario = ?, correoelectronico_propietario = ?, direccion_propietario = ?, fechadexpedicion_propietario = ?, contacto1_propietario = ?, contacto2_propietario = ? WHERE cedula_propietario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, propietario.getNombreCompleto());
            ps.setString(2, propietario.getCorreo());
            ps.setString(3, propietario.getDireccion());
            ps.setString(4, propietario.getFechaExpedicion());
            ps.setInt(5, Integer.parseInt(propietario.getNumeroContacto1()));
            ps.setInt(6, Integer.parseInt(propietario.getNumeroContacto2()));
            ps.setInt(7, Integer.parseInt(propietario.getCedula()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String cedula) {
        String sql = "DELETE FROM propietario WHERE cedula_propietario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(cedula));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Propietario listarPorCedula(String cedula) {
        Propietario propietario = null;
        String sql = "SELECT * FROM propietario WHERE cedula_propietario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(cedula));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                propietario = new Propietario();
                propietario.setCedula(String.valueOf(rs.getInt("cedula_propietario")));
                propietario.setNombreCompleto(rs.getString("nombre_propietario"));
                propietario.setCorreo(rs.getString("correoelectronico_propietario"));
                propietario.setDireccion(rs.getString("direccion_propietario"));
                propietario.setFechaExpedicion(rs.getString("fechadexpedicion_propietario"));
                propietario.setNumeroContacto1(String.valueOf(rs.getInt("contacto1_propietario")));
                propietario.setNumeroContacto2(String.valueOf(rs.getInt("contacto2_propietario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propietario;
    }
}