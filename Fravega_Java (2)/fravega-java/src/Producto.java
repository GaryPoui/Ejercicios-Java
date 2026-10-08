import java.math.BigDecimal;
import java.util.Objects;

public final class Producto {

    private final String codigo;
    private final String nombre;
    private final BigDecimal precioBase;
    private final TipoProducto tipo;

    public Producto(String codigo, String nombre, BigDecimal precioBase, TipoProducto tipo) {
        this.codigo = Validacion.texto(codigo);
        this.nombre = Validacion.texto(nombre);
        this.precioBase = Validacion.noNegativo(precioBase);
        this.tipo = Objects.requireNonNull(tipo);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public TipoProducto getTipo() {
        return tipo;
    }
}
