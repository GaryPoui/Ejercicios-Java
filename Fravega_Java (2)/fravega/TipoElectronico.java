package fravega;

public class TipoElectronico extends TipoProducto {
    public TipoElectronico(IVA iva, EO eo) {
        super(iva);
        agregarImpuesto(eo);
    }
}
