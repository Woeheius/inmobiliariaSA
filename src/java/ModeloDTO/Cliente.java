package modeloDTO;

public class Cliente {
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    private String fechaNacimiento;
    private String fechaExpedicion;
    private String correo;
    private String numeroContacto1;
    private String numeroContacto2;
    
        public Cliente() {}

    // Constructor con parámetros
    public Cliente(String cedula, String nombreCompleto, String direccion, 
                   String fechaNacimiento, String fechaExpedicion, String correo,
                   String numeroContacto1, String numeroContacto2) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaExpedicion = fechaExpedicion;
        this.correo = correo;
        this.numeroContacto1 = numeroContacto1;
        this.numeroContacto2 = numeroContacto2;
    }

    // Getters y Setters

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getFechaExpedicion() { return fechaExpedicion; }
    public void setFechaExpedicion(String fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNumeroContacto1() { return numeroContacto1; }
    public void setNumeroContacto1(String numeroContacto1) { this.numeroContacto1 = numeroContacto1; }

    public String getNumeroContacto2() { return numeroContacto2; }
    public void setNumeroContacto2(String numeroContacto2) { this.numeroContacto2 = numeroContacto2; }
}
