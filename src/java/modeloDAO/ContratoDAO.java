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
    String sqlContrato = "INSERT INTO contrato (codigo_contrato, fechadecreacion_contrato, fechadeexpedicion_contrato, descripcion_contrato, valordeventaoalquiler_contrato) VALUES (?, ?, ?, ?, ?)";
    String sqlContratocliente = "INSERT INTO contratocliente (codigo_contrato) VALUES (?)";
    String sqlTieneCliente = "INSERT INTO tienecliente (codigo_contrato, codigo_Inmueble, cedula_cliente) VALUES (?, ?, ?)";
    
    String sqlContratoconpropietario = "INSERT INTO contratoconpropietario (codigo_contrato, porcentajedecomision_contratoconpropietario) VALUES (?, ?)";
    String sqlTienePropietario = "INSERT INTO tienepropietario (codigo_contrato, codigo_Inmueble, cedula_propietario) VALUES (?, ?, ?)";

    try {
        // Inicia la transacción
        conexion.setAutoCommit(false);
        
        // Insertar en la tabla contrato
        try (PreparedStatement psContrato = conexion.prepareStatement(sqlContrato)) {
            psContrato.setInt(1, Integer.parseInt(contrato.getCodigo()));
            psContrato.setString(2, contrato.getFechaCreacion());
            psContrato.setString(3, contrato.getFechaExpiracion());
            psContrato.setString(4, contrato.getDescripcion());
            psContrato.setInt(5, (int) contrato.getValor());
            psContrato.executeUpdate();
        }

        // Verifica el tipo de servicio y realiza las inserciones correspondientes
        if ("administrar".equals(contrato.getTipoContrato())) {
            // Inserta en la tabla contratocliente
            try (PreparedStatement psContratocliente = conexion.prepareStatement(sqlContratocliente)) {
                psContratocliente.setInt(1, Integer.parseInt(contrato.getCodigo()));
                psContratocliente.executeUpdate();
            }

            // Inserta en la tabla tienecliente
            try (PreparedStatement psTieneCliente = conexion.prepareStatement(sqlTieneCliente)) {
                psTieneCliente.setInt(1, Integer.parseInt(contrato.getCodigo()));
                psTieneCliente.setInt(2, Integer.parseInt(contrato.getCodigoInmueble())); // Código del inmueble
                psTieneCliente.setInt(3, Integer.parseInt(contrato.getCedulaCliente())); // Cédula del cliente
                psTieneCliente.executeUpdate();
            }
        } else if ("adquirir".equals(contrato.getTipoContrato())) {
            // Inserta en la tabla contratoconpropietario
            try (PreparedStatement psContratoconpropietario = conexion.prepareStatement(sqlContratoconpropietario)) {
                psContratoconpropietario.setInt(1, Integer.parseInt(contrato.getCodigo()));
                psContratoconpropietario.setInt(2, 0); // Porcentaje de comisión se establece en 0
                psContratoconpropietario.executeUpdate();
            }

            // Inserta en la tabla tienepropietario
            try (PreparedStatement psTienePropietario = conexion.prepareStatement(sqlTienePropietario)) {
                psTienePropietario.setInt(1, Integer.parseInt(contrato.getCodigo()));
                psTienePropietario.setInt(2, Integer.parseInt(contrato.getCodigoInmueble())); // Código del inmueble
                psTienePropietario.setInt(3, Integer.parseInt(contrato.getCedulaCliente())); // Cédula del propietario
                psTienePropietario.executeUpdate();
            }
        }

        // Si todo es exitoso, confirma la transacción
        conexion.commit();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        try {
            // Si hay un error, deshace la transacción
            conexion.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        return false;
    } finally {
        try {
            // Restaura el auto-commit
            conexion.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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