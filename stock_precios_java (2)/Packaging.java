public class Packaging extends ItemDecorator {

    private double monto;

    public Packaging(Item item, double monto) {
        super(item);
        this.monto = monto;
    }

    @Override
    public double calcularPrecio() {
        return item.calcularPrecio() + monto;
    }

}
