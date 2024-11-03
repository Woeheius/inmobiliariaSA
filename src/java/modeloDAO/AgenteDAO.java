package modeloDAO;

import config.Conexion;
import modeloDTO.Agente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenteDAO {
    private Conexion conexion = new Conexion();

    public List<Agente> listar() {
        List<Agente> lista = new ArrayList<>();
        String sql = "SELECT * FROM agente_comercial";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Agente agente = new Agente();
                agente.setId(rs.getInt("id"));
                agente.setCedula(rs.getString("cedula"));
                agente.setLogin(rs.getString("login"));
                agente.setNombreCompleto(rs.getString("nombre_completo"));
                // Mapea los demás campos
                lista.add(agente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Agente agente) {
        String sql = "INSERT INTO agente_comercial (login, contrasena, nombre_completo, cedula, direccion, fecha_nacimiento, fecha_expedicion, correo_electronico, celular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, agente.getLogin());
            ps.setString(2, agente.getPassword());
            ps.setString(3, agente.getNombreCompleto());
            ps.setString(4, agente.getCedula());
            ps.setString(5, agente.getDireccion());
            ps.setString(6, agente.getFechaNacimiento());
            ps.setString(7, agente.getFechaExpedicion());
            ps.setString(8, agente.getCorreo());
            ps.setString(9, agente.getCelular());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Agente listarPorId(int id) {
        Agente agente = null;
        String sql = "SELECT * FROM agente_comercial WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                agente = new Agente();
                agente.setId(rs.getInt("id"));
                agente.setCedula(rs.getString("cedula"));
                agente.setLogin(rs.getString("login"));
                agente.setNombreCompleto(rs.getString("nombre_completo"));
                // Mapea los demás campos
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agente;
    }

    public boolean actualizar(Agente agente) {
        String sql = "UPDATE agente_comercial SET login = ?, contrasena = ?, nombre_completo = ?, cedula = ?, direccion = ?, fecha_nacimiento = ?, fecha_expedicion = ?, correo_electronico = ?, celular = ? WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, agente.getLogin());
            ps.setString(2, agente.getPassword());
            ps.setString(3, agente.getNombreCompleto());
            ps.setString(4, agente.getCedula());
            ps.setString(5, agente.getDireccion());
            ps.setString(6, agente.getFechaNacimiento());
            ps.setString(7, agente.getFechaExpedicion());
            ps.setString(8, agente.getCorreo());
            ps.setString(9, agente.getCelular());
            ps.setInt(10, agente.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM agente_comercial WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> obtenerConsolidado(int agenteId, String mes) {
        List<String> consolidado = new ArrayList<>();
        String sql = "SELECT i.codigo, i.descripcion, i.tipo_inmueble, c.tipo_contrato, c.fecha_creacion, cl.nombre_completo " +
                     "FROM contrato c " +
                     "JOIN inmueble i ON c.inmueble_codigo = i.id " +
                     "JOIN cliente cl ON c.cliente_id = cl.id " +
                     "WHERE c.agente_comercial_id = ? AND MONTH(c.fecha_creacion) = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, agenteId);
            ps.setString(2, mes);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String registro = "Inmueble: " + rs.getString("codigo") + ", " +
                                  "Descripción: " + rs.getString("descripcion") + ", " +
                                  "Tipo: " + rs.getString("tipo_inmueble") + ", " +
                                  "Contrato: " + rs.getString("tipo_contrato") + ", " +
                                  "Fecha: " + rs.getString("fecha_creacion") + ", " +
                                  "Cliente: " + rs.getString("nombre_completo");
                consolidado.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consolidado;
    }
    public Agente validarCredenciales(String login, String password) {
        Agente agente = null;
        String sql = "SELECT * FROM agente_comercial WHERE login = ? AND contrasena = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                agente = new Agente();
                agente.setId(rs.getInt("id"));
                agente.setLogin(rs.getString("login"));
                agente.setPassword(rs.getString("contrasena"));
                agente.setNombreCompleto(rs.getString("nombre_completo"));
                agente.setCedula(rs.getString("cedula"));
                agente.setDireccion(rs.getString("direccion"));
                agente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                agente.setFechaExpedicion(rs.getString("fecha_expedicion"));
                agente.setCorreo(rs.getString("correo_electronico"));
                agente.setCelular(rs.getString("celular"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agente;
    }
}
