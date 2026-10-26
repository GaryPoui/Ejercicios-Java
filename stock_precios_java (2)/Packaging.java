public class Packaging extends ItemDecorator {

    private double monto;

    public Packaging(Item item, double monto) {
        super(item);

        if (monto < 0) {
            this.monto = 0;
        } else {
            this.monto = monto;
        }
    }

    @Override
    public double calcularPrecio() {
        return item.calcularPrecio() + monto;
    }

}
