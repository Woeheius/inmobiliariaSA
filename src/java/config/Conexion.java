package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/INMOBILIARIA_SA"; // Cambia localhost y el puerto si es necesario.
    private static final String USER = "postgres"; // Cambia por tu usuario de PostgreSQL.
    private static final String PASSWORD = "santi"; // Cambia por tu contraseña.

    private Connection conexion;

    // Constructor para inicializar la conexión.
    public Conexion() {
        try {
            // Registrar el driver (opcional en versiones modernas de JDBC).
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            log("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            logError("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método para obtener la conexión
    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                log("Conexión cerrada.");
            }
        } catch (SQLException e) {
            logError("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Método para registrar logs de información.
    private void log(String mensaje) {
        Logger.getLogger(Conexion.class.getName()).log(Level.INFO, mensaje);
    }

    // Método para registrar logs de error.
    private void logError(String mensaje) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, mensaje);
    }
}
