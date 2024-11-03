package modeloDAO;

import config.Conexion;
import modeloDTO.Contrato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {
    private Conexion conexion = new Conexion();

    public List<Contrato> listar() {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contrato";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(rs.getInt("id"));
                contrato.setCodigo(rs.getString("codigo"));
                contrato.setDescripcion(rs.getString("descripcion"));
                contrato.setTipoContrato(rs.getString("tipo_contrato"));
                contrato.setFechaCreacion(rs.getString("fecha_creacion"));
                contrato.setFechaExpiracion(rs.getString("fecha_expiracion"));
                contrato.setValor(rs.getDouble("valor"));
                contrato.setPorcentajeComision(rs.getDouble("porcentaje_comision"));
                lista.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Contrato contrato) {
        String sql = "INSERT INTO contrato (codigo, descripcion, tipo_contrato, fecha_creacion, fecha_expiracion, valor, porcentaje_comision) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, contrato.getCodigo());
            ps.setString(2, contrato.getDescripcion());
            ps.setString(3, contrato.getTipoContrato());
            ps.setString(4, contrato.getFechaCreacion());
            ps.setString(5, contrato.getFechaExpiracion());
            ps.setDouble(6, contrato.getValor());
            ps.setDouble(7, contrato.getPorcentajeComision());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Contrato listarPorId(int id) {
        Contrato contrato = null;
        String sql = "SELECT * FROM contrato WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contrato = new Contrato();
                contrato.setId(rs.getInt("id"));
                contrato.setCodigo(rs.getString("codigo"));
                contrato.setDescripcion(rs.getString("descripcion"));
                contrato.setTipoContrato(rs.getString("tipo_contrato"));
                contrato.setFechaCreacion(rs.getString("fecha_creacion"));
                contrato.setFechaExpiracion(rs.getString("fecha_expiracion"));
                contrato.setValor(rs.getDouble("valor"));
                contrato.setPorcentajeComision(rs.getDouble("porcentaje_comision"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrato;
    }

    public boolean actualizar(Contrato contrato) {
        String sql = "UPDATE contrato SET codigo = ?, descripcion = ?, tipo_contrato = ?, fecha_creacion = ?, fecha_expiracion = ?, valor = ?, porcentaje_comision = ? WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, contrato.getCodigo());
            ps.setString(2, contrato.getDescripcion());
            ps.setString(3, contrato.getTipoContrato());
            ps.setString(4, contrato.getFechaCreacion());
            ps.setString(5, contrato.getFechaExpiracion());
            ps.setDouble(6, contrato.getValor());
            ps.setDouble(7, contrato.getPorcentajeComision());
            ps.setInt(8, contrato.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM contrato WHERE id = ?";
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
