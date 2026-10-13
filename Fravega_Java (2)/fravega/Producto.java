package fravega;

public class Producto {
    private String codigo;
    private String nombre;
    private double precioBase;
    private TipoProducto tipo;

    public Producto(String codigo, String nombre, double precioBase, TipoProducto tipo) {
        this.codigo = Validacion.texto(codigo);
        this.nombre = Validacion.texto(nombre);
        this.precioBase = Validacion.noNegativo(precioBase);
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de producto es obligatorio.");
        }
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public double calcularPrecio() {
        double total = precioBase;
        Impuesto[] impuestos = tipo.getImpuestos();
        for (int i = 0; i < impuestos.length; i++) {
            total = total + impuestos[i].calcular(precioBase);
        }
        return Validacion.redondearPrecio(total);
    }
}
