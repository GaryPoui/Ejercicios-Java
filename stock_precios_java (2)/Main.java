public class Main {

    public static void main(String[] args) {

        Producto hamburguesa = new Producto("Hamburguesa", 5000, 10);
        Producto papas = new Producto("Papas", 2000, 5);
        Producto gaseosa = new Producto("Gaseosa", 1500, 20);
        Producto coca = new Producto("Coca", 2000, 10);

        Combo combo = new Combo();
        combo.agregarItem(hamburguesa);
        combo.agregarItem(papas);
        combo.agregarItem(gaseosa);

        System.out.println("Precio combo: $" + combo.calcularPrecio());
        System.out.println("Stock combo: " + combo.calcularStock());

        Combo comboGrande = new Combo();
        comboGrande.agregarItem(combo);
        comboGrande.agregarItem(coca);

        Item precioFinal = new Packaging(
                new Descuento(
                        new Descuento(comboGrande, 1000),
                        500),
                300);

        System.out.println("Precio combo anidado: $" + comboGrande.calcularPrecio());
        System.out.println("Stock combo anidado: " + comboGrande.calcularStock());
        System.out.println("Precio final con descuentos y packaging: $"
                + precioFinal.calcularPrecio());
        System.out.println("Stock final: " + precioFinal.calcularStock());
    }
}
