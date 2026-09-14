public abstract class ItemDecorator implements Item {

    protected Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    @Override
    public double calcularPrecio() {
        return item.calcularPrecio();
    }

    @Override
    public int calcularStock() {
        return item.calcularStock();
    }
}
