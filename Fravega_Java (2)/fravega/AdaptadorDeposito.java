package fravega;

public class AdaptadorDeposito implements ObservadorVenta {
    private ApiDeposito api;

    public AdaptadorDeposito(ApiDeposito api) {
        if (api == null) {
            throw new IllegalArgumentException("La API de deposito es obligatoria.");
        }
        this.api = api;
    }

    public void alVender(Venta venta) {
        api.prepararProducto(venta.getSucursal(), venta.getProducto().getCodigo());
    }
}
