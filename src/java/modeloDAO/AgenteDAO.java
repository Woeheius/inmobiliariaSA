package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeloDTO.Agente;

public class AgenteDAO {
    private Connection conexion;
    private static final Logger LOGGER = Logger.getLogger(AgenteDAO.class.getName());

    public AgenteDAO() {
        Conexion conn = new Conexion();
        this.conexion = conn.getConexion();
    }

    // Método para listar todos los agentes comerciales
    public List<Agente> listar() {
        List<Agente> agentes = new ArrayList<>();
        String sql = "SELECT * FROM agentecomercial";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            LOGGER.info("Ejecutando consulta: " + sql);
            while (rs.next()) {
                Agente agente = new Agente();
                agente.setLogin(rs.getString("login_agentecomercial"));
                agente.setPassword(rs.getString("contrasena_agentecomercial"));
                agente.setCedula(String.valueOf(rs.getInt("cedula_agentecomercial")));
                agente.setNombreCompleto(rs.getString("nombrecompleto_agentecomercial"));
                agente.setDireccion(rs.getString("direccion_agentecomercial"));
                agente.setFechaNacimiento(rs.getString("fechadenacimiento_agentecomercial"));
                agente.setFechaExpedicion(rs.getString("fechadexpedicion_agentecomercial"));
                agente.setCorreo(rs.getString("correoelectronico_agentecomercial"));
                agente.setCelular(String.valueOf(rs.getInt("celular_agentecomercial")));
                agentes.add(agente);
            }
            LOGGER.info("Consulta finalizada. Se encontraron " + agentes.size() + " agentes.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar agentes: " + e.getMessage(), e);
        }
        return agentes;
    }

    // Método para insertar un agente comercial
    public boolean insertar(Agente agente) {
        String sql = "INSERT INTO agentecomercial (cedula_agentecomercial, login_agentecomercial, nombrecompleto_agentecomercial, correoelectronico_agentecomercial, celular_agentecomercial, fechadexpedicion_agentecomercial, fechadenacimiento_agentecomercial, direccion_agentecomercial, contrasena_agentecomercial) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(agente.getCedula()));
            ps.setString(2, agente.getLogin());
            ps.setString(3, agente.getNombreCompleto());
            ps.setString(4, agente.getCorreo());
            ps.setInt(5, Integer.parseInt(agente.getCelular()));
            ps.setString(6, agente.getFechaExpedicion());
            ps.setString(7, agente.getFechaNacimiento());
            ps.setString(8, agente.getDireccion());
            ps.setString(9, agente.getPassword());
            LOGGER.info("Ejecutando inserción: " + sql);
            int rows = ps.executeUpdate();
            LOGGER.info("Inserción finalizada. Filas afectadas: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar agente: " + e.getMessage(), e);
            return false;
        }
    }

    // Método para actualizar un agente comercial
    public boolean actualizar(Agente agente) {
        String sql = "UPDATE agentecomercial SET login_agentecomercial = ?, nombrecompleto_agentecomercial = ?, correoelectronico_agentecomercial = ?, celular_agentecomercial = ?, fechadexpedicion_agentecomercial = ?, fechadenacimiento_agentecomercial = ?, direccion_agentecomercial = ?, contrasena_agentecomercial = ? WHERE cedula_agentecomercial = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, agente.getLogin());
            ps.setString(2, agente.getNombreCompleto());
            ps.setString(3, agente.getCorreo());
            ps.setInt(4, Integer.parseInt(agente.getCelular()));
            ps.setString(5, agente.getFechaExpedicion());
            ps.setString(6, agente.getFechaNacimiento());
            ps.setString(7, agente.getDireccion());
            ps.setString(8, agente.getPassword());
            ps.setInt(9, Integer.parseInt(agente.getCedula()));
            LOGGER.info("Ejecutando actualización: " + sql);
            int rows = ps.executeUpdate();
            LOGGER.info("Actualización finalizada. Filas afectadas: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar agente: " + e.getMessage(), e);
            return false;
        }
    }

    // Método para eliminar un agente comercial
    public boolean eliminar(String cedula) {
        String sql = "DELETE FROM agentecomercial WHERE cedula_agentecomercial = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(cedula));
            LOGGER.info("Ejecutando eliminación: " + sql);
            int rows = ps.executeUpdate();
            LOGGER.info("Eliminación finalizada. Filas afectadas: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar agente: " + e.getMessage(), e);
            return false;
        }
    }
    public Agente listarPorCedula(String cedula) {
    Agente agente = null;
    String sql = "SELECT * FROM agentecomercial WHERE cedula_agentecomercial = ?";
    try (PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setInt(1, Integer.parseInt(cedula));
        LOGGER.info("Ejecutando consulta: " + sql + " con cédula = " + cedula);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                agente = new Agente();
                agente.setLogin(rs.getString("login_agentecomercial"));
                agente.setPassword(rs.getString("contrasena_agentecomercial"));
                agente.setCedula(String.valueOf(rs.getInt("cedula_agentecomercial")));
                agente.setNombreCompleto(rs.getString("nombrecompleto_agentecomercial"));
                agente.setDireccion(rs.getString("direccion_agentecomercial"));
                agente.setFechaNacimiento(rs.getString("fechadenacimiento_agentecomercial"));
                agente.setFechaExpedicion(rs.getString("fechadexpedicion_agentecomercial"));
                agente.setCorreo(rs.getString("correoelectronico_agentecomercial"));
                agente.setCelular(String.valueOf(rs.getInt("celular_agentecomercial")));
                LOGGER.info("Agente encontrado: " + agente.getNombreCompleto());
            } else {
                LOGGER.warning("No se encontró un agente con la cédula: " + cedula);
            }
        }
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Error al buscar agente por cédula: " + e.getMessage(), e);
    }
    return agente;
}
}
