import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Combo extends Producto{

    private Set<Producto > productos;

    public Combo(String descripcion) {
        super(descripcion);
        this.productos = new HashSet<>();
    }

    public void agregarProducto(Producto ...p){
        Collections.addAll(this.productos,p);
    }
    @Override
    public int stock() {
        //return this.productos.stream().mapToInt(Producto::stock).min().getAsInt();
        return Collections.min(this.productos, Comparator.comparing(Producto::stock)).stock();
    }

    @Override
    public double precio() {
        return this.productos.stream().mapToDouble(Producto::precio).sum();
    }
}
