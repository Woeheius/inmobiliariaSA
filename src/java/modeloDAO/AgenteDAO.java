package modeloDAO;

import config.Conexion;
import modeloDTO.Agente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenteDAO {
    private Conexion conexion = new Conexion();
    private List<Agente> listaAgentesPrueba;

    public AgenteDAO() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        listaAgentesPrueba = new ArrayList<>();
        
        Agente agente1 = new Agente();
        agente1.setId(1);
        agente1.setCedula("123456789");
        agente1.setLogin("jperez");
        agente1.setNombreCompleto("Juan Perez");
        agente1.setCorreo("juan.perez@ejemplo.com");
        agente1.setCelular("1234567890");
        agente1.setFechaNacimiento("1990-01-01");
        agente1.setFechaExpedicion("2010-01-01");
        agente1.setDireccion("Calle Falsa 123");
        agente1.setPassword("12345");

        Agente agente2 = new Agente();
        agente2.setId(2);
        agente2.setCedula("987654321");
        agente2.setLogin("mlopez");
        agente2.setNombreCompleto("Maria Lopez");
        agente2.setCorreo("maria.lopez@ejemplo.com");
        agente2.setCelular("0987654321");
        agente2.setFechaNacimiento("1985-05-05");
        agente2.setFechaExpedicion("2005-05-05");
        agente2.setDireccion("Avenida Siempre Viva 742");
        agente2.setPassword("54321");

        listaAgentesPrueba.add(agente1);
        listaAgentesPrueba.add(agente2);
    }

    public List<Agente> listar() {
    List<Agente> listaAgentes = new ArrayList<>();
    String sql = "SELECT * FROM agente_comercial";

    try (Connection con = conexion.getConnection()) {
        if (con == null) {
            System.out.println("Conexión a la base de datos fallida. Usando datos de prueba.");
            return listaAgentesPrueba; // Retorna los datos de prueba directamente
        }
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Agente agente = new Agente();
                agente.setId(rs.getInt("id"));
                agente.setCedula(rs.getString("cedula"));
                agente.setLogin(rs.getString("login"));
                agente.setNombreCompleto(rs.getString("nombre_completo"));
                agente.setCorreo(rs.getString("correo_electronico"));
                agente.setCelular(rs.getString("celular"));
                agente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                agente.setFechaExpedicion(rs.getString("fecha_expedicion"));
                agente.setDireccion(rs.getString("direccion"));
                agente.setPassword(rs.getString("contrasena"));
                listaAgentes.add(agente);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al listar agentes en la base de datos: " + e.getMessage());
        return listaAgentesPrueba; // Retorna los datos de prueba en caso de error en la base de datos
    }
    return listaAgentes;
}
    public boolean agregar(Agente agente) {
        String sql = "INSERT INTO agente_comercial (cedula, login, nombre_completo, correo_electronico, celular, fecha_nacimiento, fecha_expedicion, direccion, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, agente.getCedula());
            ps.setString(2, agente.getLogin());
            ps.setString(3, agente.getNombreCompleto());
            ps.setString(4, agente.getCorreo());
            ps.setString(5, agente.getCelular());
            ps.setString(6, agente.getFechaNacimiento());
            ps.setString(7, agente.getFechaExpedicion());
            ps.setString(8, agente.getDireccion());
            ps.setString(9, agente.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar agente en la base de datos. Agregando a lista de prueba.");
            agente.setId(listaAgentesPrueba.size() + 1);
            return listaAgentesPrueba.add(agente);
        }
    }

    public boolean actualizar(Agente agente) {
        String sql = "UPDATE agente_comercial SET cedula = ?, login = ?, nombre_completo = ?, correo_electronico = ?, celular = ?, fecha_nacimiento = ?, fecha_expedicion = ?, direccion = ?, contrasena = ? WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                for (int i = 0; i < listaAgentesPrueba.size(); i++) {
                    if (listaAgentesPrueba.get(i).getId() == agente.getId()) {
                        listaAgentesPrueba.set(i, agente);
                        return true;
                    }
                }
                return false;
            }
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, agente.getCedula());
                ps.setString(2, agente.getLogin());
                ps.setString(3, agente.getNombreCompleto());
                ps.setString(4, agente.getCorreo());
                ps.setString(5, agente.getCelular());
                ps.setString(6, agente.getFechaNacimiento());
                ps.setString(7, agente.getFechaExpedicion());
                ps.setString(8, agente.getDireccion());
                ps.setString(9, agente.getPassword());
                ps.setInt(10, agente.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar agente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM agente_comercial WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                return listaAgentesPrueba.removeIf(agente -> agente.getId() == id);
            }
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar agente: " + e.getMessage());
            return false;
        }
    }

    public Agente listarPorId(int id) {
        String sql = "SELECT * FROM agente_comercial WHERE id = ?";

        try (Connection con = conexion.getConnection()) {
            if (con == null) {
                return listaAgentesPrueba.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
            }
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Agente agente = new Agente();
                    agente.setId(rs.getInt("id"));
                    agente.setCedula(rs.getString("cedula"));
                    agente.setLogin(rs.getString("login"));
                    agente.setNombreCompleto(rs.getString("nombre_completo"));
                    agente.setCorreo(rs.getString("correo_electronico"));
                    agente.setCelular(rs.getString("celular"));
                    agente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                    agente.setFechaExpedicion(rs.getString("fecha_expedicion"));
                    agente.setDireccion(rs.getString("direccion"));
                    agente.setPassword(rs.getString("contrasena"));
                    return agente;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener agente por ID: " + e.getMessage());
        }
        return null;}
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
