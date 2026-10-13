package fravega;

public class AdaptadorContabilidad implements ObservadorVenta {
    private ApiContable api;

    public AdaptadorContabilidad(ApiContable api) {
        if (api == null) {
            throw new IllegalArgumentException("La API contable es obligatoria.");
        }
        this.api = api;
    }

    public void alVender(Venta venta) {
        api.registrarImporte(venta.getSucursal(), venta.getImporte());
    }
}
