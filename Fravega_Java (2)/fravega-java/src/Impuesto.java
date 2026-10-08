import java.math.BigDecimal;

public interface Impuesto {

    String getNombre();
    BigDecimal calcular(BigDecimal precioBase);
}
