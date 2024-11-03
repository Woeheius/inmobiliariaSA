package modeloDTO;

public class Contrato {
    private int id;
    private String codigo;
    private String descripcion;
    private String tipoContrato; // Venta o Alquiler
    private String fechaCreacion;
    private String fechaExpiracion;
    private double valor;
    private double porcentajeComision;
    
        public Contrato() {}

    // Constructor con parámetros
    public Contrato(int id, String codigo, String descripcion, String tipoContrato, 
                    String fechaCreacion, String fechaExpiracion, double valor, 
                    double porcentajeComision) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoContrato = tipoContrato;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.valor = valor;
        this.porcentajeComision = porcentajeComision;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(String fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public double getPorcentajeComision() { return porcentajeComision; }
    public void setPorcentajeComision(double porcentajeComision) { this.porcentajeComision = porcentajeComision; }
}
