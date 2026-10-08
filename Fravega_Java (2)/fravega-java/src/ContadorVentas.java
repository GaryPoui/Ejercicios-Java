import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class ContadorVentas implements ObservadorVenta {

    private final List<Venta> ventas = new ArrayList<>();

    public void alVender(Venta venta) {
        for (Venta registrada : ventas) {
            if (registrada.getId().equals(venta.getId())) {
                return;
            }
        }

        ventas.add(venta);
    }

    public int cantidadEn(LocalDate fecha) {
        int cantidad = 0;

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFecha().toLocalDate();

            if (fechaVenta.equals(fecha)) {
                cantidad++;
            }
        }

        return cantidad;
    }
}
