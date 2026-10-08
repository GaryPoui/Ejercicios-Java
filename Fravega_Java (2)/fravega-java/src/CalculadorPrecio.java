import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class CalculadorPrecio {

    private final IVA iva;

    public CalculadorPrecio(IVA iva) {
        this.iva = Objects.requireNonNull(iva);
    }

    public BigDecimal calcular(Producto producto) {
        BigDecimal base = producto.getPrecioBase();
        BigDecimal importeIva = iva.calcular(base);
        BigDecimal total = base.add(importeIva);

        for (Impuesto impuesto : producto.getTipo().getImpuestosAdicionales()) {
            BigDecimal importeImpuesto = impuesto.calcular(base);
            total = total.add(importeImpuesto);
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
