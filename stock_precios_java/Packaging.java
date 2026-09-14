public class Packaging extends ItemDecorator {

    private double precio;

    public Packaging(Item item, double precio) {
        super(item);
        this.precio = precio;
    }

    @Override
    public double calcularPrecio() {
        return item.calcularPrecio() + precio;
    }

    @Override
    public int calcularStock() {
        return item.calcularStock();
    }
}
