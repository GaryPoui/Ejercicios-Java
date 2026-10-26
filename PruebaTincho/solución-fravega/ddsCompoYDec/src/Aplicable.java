import java.util.Collections;
import java.util.Comparator;

public abstract class Aplicable extends Producto{

    protected Producto producto;
    public Aplicable(String descripcion, Producto producto) {
        super(descripcion);
        this.producto=producto;
    }

    @Override
    public int stock() {
        return producto.stock();
    }
}
