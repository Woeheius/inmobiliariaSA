package modeloDAO;

import config.Conexion;
import modeloDTO.Contrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {
    private Connection conexion;

    public ContratoDAO() {
        Conexion conn = new Conexion();
        this.conexion = conn.getConexion();
    }

    public List<Contrato> listar() {
        List<Contrato> listaContratos = new ArrayList<>();
        String sql = "SELECT * FROM contrato";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setCodigo(String.valueOf(rs.getInt("codigo_contrato")));
                contrato.setFechaCreacion(rs.getString("fechadecreacion_contrato"));
                contrato.setFechaExpiracion(rs.getString("fechadeexpedicion_contrato"));
                contrato.setDescripcion(rs.getString("descripcion_contrato"));
                contrato.setValor(rs.getInt("valordeventaoalquiler_contrato"));
                listaContratos.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaContratos;
    }

    public boolean agregar(Contrato contrato) {
        String sql = "INSERT INTO contrato (codigo_contrato, fechadecreacion_contrato, fechadeexpedicion_contrato, descripcion_contrato, valordeventaoalquiler_contrato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(contrato.getCodigo()));
            ps.setString(2, contrato.getFechaCreacion());
            ps.setString(3, contrato.getFechaExpiracion());
            ps.setString(4, contrato.getDescripcion());
            ps.setInt(5, (int) contrato.getValor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Contrato contrato) {
        String sql = "UPDATE contrato SET fechadecreacion_contrato = ?, fechadeexpedicion_contrato = ?, descripcion_contrato = ?, valordeventaoalquiler_contrato = ? WHERE codigo_contrato = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, contrato.getFechaCreacion());
            ps.setString(2, contrato.getFechaExpiracion());
            ps.setString(3, contrato.getDescripcion());
            ps.setInt(4, (int) contrato.getValor());
            ps.setInt(5, Integer.parseInt(contrato.getCodigo()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM contrato WHERE codigo_contrato = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contrato listarPorCodigo(String codigo) {
        Contrato contrato = null;
        String sql = "SELECT * FROM contrato WHERE codigo_contrato = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contrato = new Contrato();
                contrato.setCodigo(String.valueOf(rs.getInt("codigo_contrato")));
                contrato.setFechaCreacion(rs.getString("fechadecreacion_contrato"));
                contrato.setFechaExpiracion(rs.getString("fechadeexpedicion_contrato"));
                contrato.setDescripcion(rs.getString("descripcion_contrato"));
                contrato.setValor(rs.getInt("valordeventaoalquiler_contrato"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrato;
    }
}