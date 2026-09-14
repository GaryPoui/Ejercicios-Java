public class Main {

    public static void main(String[] args) {

        Producto hamburguesa = new Producto("Hamburguesa", 5000, 10);
        Producto papas = new Producto("Papas", 2000, 5);
        Producto gaseosa = new Producto("Gaseosa", 1500, 20);

        Combo combo = new Combo();
        combo.agregarItem(hamburguesa);
        combo.agregarItem(papas);
        combo.agregarItem(gaseosa);

        System.out.println("Precio combo: $" + combo.calcularPrecio());
        System.out.println("Stock combo: " + combo.calcularStock());

        Item comboConPackaging = new Packaging(combo, 500);
        System.out.println("Combo + packaging: $" + comboConPackaging.calcularPrecio());

        Item comboFinal = new Descuento(comboConPackaging, 1000);
        System.out.println("Combo + packaging + descuento: $" + comboFinal.calcularPrecio());
        System.out.println("Stock final: " + comboFinal.calcularStock());
    }
}
