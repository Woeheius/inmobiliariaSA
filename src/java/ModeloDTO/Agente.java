package modeloDTO;

public class Agente {
    private int id;
    private String login;
    private String password;
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    private String fechaNacimiento;
    private String fechaExpedicion;
    private String correo;
    private String celular;
    
    public Agente() {}

    // Constructor con parámetros
    public Agente(int id, String login, String password, String cedula, String nombreCompleto, String direccion, 
                  String fechaNacimiento, String fechaExpedicion, String correo, String celular) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaExpedicion = fechaExpedicion;
        this.correo = correo;
        this.celular = celular;
    }


    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

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

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
}
