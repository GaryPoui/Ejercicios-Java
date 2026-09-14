import java.util.ArrayList;
import java.util.List;

public class Combo implements Item {

    private List<Item> items;

    public Combo() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        items.add(item);
    }

    @Override
    public double calcularPrecio() {
        double precioTotal = 0;

        for (Item item : items) {
            precioTotal += item.calcularPrecio();
        }

        return precioTotal;
    }

    @Override
    public int calcularStock() {
        if (items.isEmpty()) {
            return 0;
        }

        int stockMinimo = Integer.MAX_VALUE;

        for (Item item : items) {
            stockMinimo = Math.min(stockMinimo, item.calcularStock());
        }

        return stockMinimo;
    }
}
