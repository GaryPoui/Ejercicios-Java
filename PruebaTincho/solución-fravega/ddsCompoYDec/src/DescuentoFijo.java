public class DescuentoFijo extends Aplicable{
    private double valor;

    public DescuentoFijo(String descripcion, Producto producto, Double valor) {
        super(descripcion, producto);
        this.valor=valor;
    }


    @Override
    public double precio() {
        return super.producto.precio() - this.valor;
    }
}
