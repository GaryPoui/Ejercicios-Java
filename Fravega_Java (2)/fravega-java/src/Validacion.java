import java.math.BigDecimal;
import java.util.Objects;

final class Validacion {

    private Validacion() {
    }

    static BigDecimal noNegativo(BigDecimal valor) {
        Objects.requireNonNull(valor, "El importe no puede ser nulo");

        if (valor.signum() < 0) {
            throw new IllegalArgumentException("El importe no puede ser negativo");
        }

        return valor;
    }

    static BigDecimal positivo(BigDecimal valor) {
        noNegativo(valor);

        if (valor.signum() == 0) {
            throw new IllegalArgumentException("El valor debe ser mayor que cero");
        }

        return valor;
    }

    static String texto(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El texto es obligatorio");
        }

        return valor;
    }
}
