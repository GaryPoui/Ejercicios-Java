import java.math.BigDecimal;
import java.math.MathContext;

public final class EO implements Impuesto {

    private BigDecimal gananciasImpositivas;

    public EO(BigDecimal ganancias) {
        setGananciasImpositivas(ganancias);
    }

    public void setGananciasImpositivas(BigDecimal ganancias) {
        gananciasImpositivas = Validacion.positivo(ganancias);
    }

    public String getNombre() {
        return "EO";
    }

    public BigDecimal calcular(BigDecimal base) {
        Validacion.noNegativo(base);

        BigDecimal mitadDelPrecio = base.multiply(new BigDecimal("0.5"));
        BigDecimal cuatroVecesGanancias = gananciasImpositivas.multiply(new BigDecimal("4"));

        return mitadDelPrecio.divide(cuatroVecesGanancias, MathContext.DECIMAL128);
    }
}
