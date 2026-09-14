public class Descuento extends ItemDecorator {

    private double monto;

    public Descuento(Item item, double monto) {
        super(item);
        this.monto = monto;
    }

    @Override
    public double calcularPrecio() {
        double precioFinal = item.calcularPrecio() - monto;
        return Math.max(precioFinal, 0);
    }

    @Override
    public int calcularStock() {
        return item.calcularStock();
    }
}
