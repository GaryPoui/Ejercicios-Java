public class Descuento extends ItemDecorator {

    private double monto;

    public Descuento(Item item, double monto) {
        super(item);

        if (monto < 0) {
            this.monto = 0;
        } else {
            this.monto = monto;
        }
    }

    @Override
    public double calcularPrecio() {
        double precioFinal = item.calcularPrecio() - monto;

        if (precioFinal < 0) {
            return 0;
        }

        return precioFinal;
    }

    @Override
    public int calcularStock() {
        return item.calcularStock();
    }
}
