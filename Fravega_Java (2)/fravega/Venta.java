package fravega;

public class Venta {
    private String fecha;
    private double importe;
    private Producto producto;
    private String sucursal;

    public Venta(String sucursal, Producto producto, String fecha) {
        this.sucursal = Validacion.texto(sucursal);
        this.fecha = Validacion.texto(fecha);
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio.");
        }
        this.producto = producto;
        importe = producto.calcularPrecio();
    }

    public String getFecha() {
        return fecha;
    }

    public double getImporte() {
        return importe;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getSucursal() {
        return sucursal;
    }
}
