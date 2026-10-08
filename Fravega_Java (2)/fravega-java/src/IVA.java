import java.math.BigDecimal;

public final class IVA implements Impuesto {

    private BigDecimal porcentaje;

    public IVA(BigDecimal porcentaje) {
        setPorcentaje(porcentaje);
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = Validacion.noNegativo(porcentaje);
    }

    public String getNombre() {
        return "IVA";
    }

    public BigDecimal calcular(BigDecimal base) {
        Validacion.noNegativo(base);

        return base.multiply(porcentaje);
    }
}
