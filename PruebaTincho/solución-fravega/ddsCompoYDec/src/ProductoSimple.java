public class ProductoSimple extends Producto{

    private double precio;
    private int stock;

    public ProductoSimple(String descripcion) {
        super(descripcion);
    }
    public ProductoSimple(String descripcion, double precio, int stock) {
        super(descripcion);
        this.precio = precio;
        this.stock=stock;
    }

    @Override
    public int stock() {
        return this.stock;
    }

    @Override
    public double precio() {
        return this.precio;
    }
}
