import java.util.Objects;

public final class AdaptadorContabilidad implements ObservadorVenta {

    private final ApiContable api;

    public AdaptadorContabilidad(ApiContable api) {
        this.api = Objects.requireNonNull(api);
    }

    public void alVender(Venta venta) {
        api.registrarImporte(venta.getId(), venta.getSucursal(), venta.getImporte());
    }
}
