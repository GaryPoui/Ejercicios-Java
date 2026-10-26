public class Packaging extends Aplicable{

    private double valor;

    public Packaging(String descripcion, Producto producto, double valor) {
        super(descripcion, producto);
        this.valor = valor;
    }

    @Override
    public double precio() {
        return super.producto.precio() + this.valor;
    }
}
