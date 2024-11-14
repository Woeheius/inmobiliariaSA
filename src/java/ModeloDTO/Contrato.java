package modeloDTO;

public class Contrato {
    // Campos existentes
    private String codigo;
    private String descripcion;
    private String tipoContrato;
    private String fechaCreacion;
    private String fechaExpiracion;
    private double valor;
    private double porcentajeComision;
    
    // Nuevos campos para relaciones
    private String codigoInmueble;
    private String cedulaCliente;
    private String cedulaAgente;

    // Constructor vacío
    public Contrato() {}

    // Constructor completo
    public Contrato(String codigo, String descripcion, String tipoContrato, 
                   String fechaCreacion, String fechaExpiracion, double valor, 
                   double porcentajeComision, String codigoInmueble, 
                   String cedulaCliente, String cedulaAgente) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoContrato = tipoContrato;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.valor = valor;
        this.porcentajeComision = porcentajeComision;
        this.codigoInmueble = codigoInmueble;
        this.cedulaCliente = cedulaCliente;
        this.cedulaAgente = cedulaAgente;
    }

    // Getters y Setters existentes
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

    // Nuevos Getters y Setters para las relaciones
    public String getCodigoInmueble() { return codigoInmueble; }
    public void setCodigoInmueble(String codigoInmueble) { this.codigoInmueble = codigoInmueble; }

    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }

    public String getCedulaAgente() { return cedulaAgente; }
    public void setCedulaAgente(String cedulaAgente) { this.cedulaAgente = cedulaAgente; }
}