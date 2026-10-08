import java.math.BigDecimal;

public final class EI implements Impuesto {

    private BigDecimal gananciasImpositivas;

    public EI(BigDecimal ganancias) {
        setGananciasImpositivas(ganancias);
    }

    public void setGananciasImpositivas(BigDecimal ganancias) {
        gananciasImpositivas = Validacion.noNegativo(ganancias);
    }

    public String getNombre() {
        return "EI";
    }

    public BigDecimal calcular(BigDecimal base) {
        Validacion.noNegativo(base);

        BigDecimal parteDelPrecio = base.divide(new BigDecimal("4"));
        BigDecimal parteDeGanancias = gananciasImpositivas.multiply(new BigDecimal("0.3"));

        return parteDelPrecio.add(parteDeGanancias);
    }
}
