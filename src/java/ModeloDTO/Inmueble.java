package modeloDTO;

public class Inmueble {
    private int id;
    private String codigo;
    private String descripcion;
    private String tipoInmueble; // Casa, Apartamento, Finca, Local Comercial
    private String modalidad; // Venta o Alquiler
    private double precio;
    private String estado; // En oferta, Vendido
    private int cantidadBanos;
    private double tamano;
    private String departamento;
    private String ciudad;
    private String direccion;
    
        public Inmueble() {}

    // Constructor con parámetros
    public Inmueble(int id, String codigo, String descripcion, String tipoInmueble, 
                    String modalidad, double precio, String estado, int cantidadBanos, 
                    double tamano, String departamento, String ciudad, String direccion) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoInmueble = tipoInmueble;
        this.modalidad = modalidad;
        this.precio = precio;
        this.estado = estado;
        this.cantidadBanos = cantidadBanos;
        this.tamano = tamano;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getTipoInmueble() { return tipoInmueble; }
    public void setTipoInmueble(String tipoInmueble) { this.tipoInmueble = tipoInmueble; }

    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getCantidadBanos() { return cantidadBanos; }
    public void setCantidadBanos(int cantidadBanos) { this.cantidadBanos = cantidadBanos; }

    public double getTamano() { return tamano; }
    public void setTamano(double tamano) { this.tamano = tamano; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
