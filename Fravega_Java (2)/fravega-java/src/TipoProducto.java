import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TipoProducto {

    private final String nombre;
    private final List<Impuesto> adicionales = new ArrayList<>();

    public TipoProducto(String nombre) {
        this.nombre = Validacion.texto(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarImpuesto(Impuesto impuesto) {
        Objects.requireNonNull(impuesto);

        if (impuesto instanceof IVA || "IVA".equalsIgnoreCase(impuesto.getNombre())) {
            throw new IllegalArgumentException("El IVA ya se aplica obligatoriamente");
        }

        for (Impuesto adicional : adicionales) {
            if (adicional.getNombre().equalsIgnoreCase(impuesto.getNombre())) {
                throw new IllegalArgumentException("Impuesto repetido");
            }
        }
        adicionales.add(impuesto);
    }

    public List<Impuesto> getImpuestosAdicionales() {
        return List.copyOf(adicionales);
    }
}
