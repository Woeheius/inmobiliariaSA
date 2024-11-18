package config;


import config.Conexion;

public class TestConexion {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        if (conexion.getConexion() != null) {
            System.out.println("Conexión exitosa.");
        } else {
            System.out.println("Fallo en la conexión.");
        }
        conexion.cerrarConexion();
    }
}
