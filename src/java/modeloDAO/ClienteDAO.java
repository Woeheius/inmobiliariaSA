package modeloDAO;

import config.Conexion;
import modeloDTO.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Conexion conexion = new Conexion();
    private List<Cliente> listaClientesPrueba;

    public ClienteDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaClientesPrueba = new ArrayList<>();

        Cliente cliente1 = new Cliente(1, "123456789", "Carlos Ruiz", "Av. Siempre Viva 742", "1980-01-01", "2000-01-01", "carlos.ruiz@ejemplo.com", "1234567890", "0987654321");
        Cliente cliente2 = new Cliente(2, "987654321", "Lucía Gomez", "Calle Falsa 123", "1990-02-02", "2010-02-02", "lucia.gomez@ejemplo.com", "1122334455", "5566778899");

        listaClientesPrueba.add(cliente1);
        listaClientesPrueba.add(cliente2);
    }

    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                System.out.println("Conexión a la base de datos fallida. Usando datos de prueba.");
                return listaClientesPrueba;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setCedula(rs.getString("cedula"));
                    cliente.setNombreCompleto(rs.getString("nombre_completo"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                    cliente.setFechaExpedicion(rs.getString("fecha_expedicion"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setNumeroContacto1(rs.getString("numero_contacto1"));
                    cliente.setNumeroContacto2(rs.getString("numero_contacto2"));
                    listaClientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes en la base de datos: " + e.getMessage());
            return listaClientesPrueba;
        }
        return listaClientes;
    }

    public boolean agregar(Cliente cliente) {
        String sql = "INSERT INTO cliente (cedula, nombre_completo, direccion, fecha_nacimiento, fecha_expedicion, correo, numero_contacto1, numero_contacto2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getFechaNacimiento());
            ps.setString(5, cliente.getFechaExpedicion());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getNumeroContacto1());
            ps.setString(8, cliente.getNumeroContacto2());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente en la base de datos. Agregando a lista de prueba.");
            cliente.setId(listaClientesPrueba.size() + 1);
            return listaClientesPrueba.add(cliente);
        }
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET cedula = ?, nombre_completo = ?, direccion = ?, fecha_nacimiento = ?, fecha_expedicion = ?, correo = ?, numero_contacto1 = ?, numero_contacto2 = ? WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                for (int i = 0; i < listaClientesPrueba.size(); i++) {
                    if (listaClientesPrueba.get(i).getId() == cliente.getId()) {
                        listaClientesPrueba.set(i, cliente);
                        return true;
                    }
                }
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cliente.getCedula());
                ps.setString(2, cliente.getNombreCompleto());
                ps.setString(3, cliente.getDireccion());
                ps.setString(4, cliente.getFechaNacimiento());
                ps.setString(5, cliente.getFechaExpedicion());
                ps.setString(6, cliente.getCorreo());
                ps.setString(7, cliente.getNumeroContacto1());
                ps.setString(8, cliente.getNumeroContacto2());
                ps.setInt(9, cliente.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                return listaClientesPrueba.removeIf(cliente -> cliente.getId() == id);
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente listarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                return listaClientesPrueba.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setCedula(rs.getString("cedula"));
                    cliente.setNombreCompleto(rs.getString("nombre_completo"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                    cliente.setFechaExpedicion(rs.getString("fecha_expedicion"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setNumeroContacto1(rs.getString("numero_contacto1"));
                    cliente.setNumeroContacto2(rs.getString("numero_contacto2"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }
}
