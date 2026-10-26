import java.util.ArrayList;
import java.util.List;

public class Combo implements Item {

    private List<Item> items;

    public Combo() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        if (item != null) {
            items.add(item);
        }
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

        int stockCombo = items.get(0).calcularStock();

        for (int i = 1; i < items.size(); i++) {
            int stockActual = items.get(i).calcularStock();

            if (stockActual < stockCombo) {
                stockCombo = stockActual;
            }
        }

        return stockCombo;
    }
}
