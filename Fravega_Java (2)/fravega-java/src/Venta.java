import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Venta {

    private final UUID id = UUID.randomUUID();
    private final String sucursal;
    private final Producto producto;
    private final BigDecimal importe;
    private final LocalDateTime fecha;

    public Venta(String sucursal, Producto producto, BigDecimal importe, LocalDateTime fecha) {
        this.sucursal = Validacion.texto(sucursal);
        this.producto = Objects.requireNonNull(producto);
        this.importe = Validacion.noNegativo(importe);
        this.fecha = Objects.requireNonNull(fecha);
    }

    public UUID getId() {
        return id;
    }

    public String getSucursal() {
        return sucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
