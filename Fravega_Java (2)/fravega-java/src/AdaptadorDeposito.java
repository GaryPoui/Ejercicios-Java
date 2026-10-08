import java.util.Objects;

public final class AdaptadorDeposito implements ObservadorVenta {

    private final ApiDeposito api;

    public AdaptadorDeposito(ApiDeposito api) {
        this.api = Objects.requireNonNull(api);
    }

    public void alVender(Venta venta) {
        api.prepararProducto(venta.getId(), venta.getSucursal(), venta.getProducto().getCodigo());
    }
}
