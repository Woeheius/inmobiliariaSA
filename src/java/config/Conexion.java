package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_base_datos"; // Cambia por tu URL
    private static final String USER = "usuario"; // Cambia por tu usuario de base de datos
    private static final String PASSWORD = "contraseña"; // Cambia por tu contraseña

    public Connection getConnection() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
