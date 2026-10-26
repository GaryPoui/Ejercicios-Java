public class Producto implements Item {

    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public double calcularPrecio() {
        if (precio < 0) {
            return 0;
        }
        return precio;
    }

    @Override
    public int calcularStock() {
        if (stock < 0) {
            return 0;
        }
        return stock;
    }

    public String getNombre() {
        return nombre;
    }
}
