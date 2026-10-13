package fravega;

public class TipoHogar extends TipoProducto {
    public TipoHogar(IVA iva, EI ei) {
        super(iva);
        agregarImpuesto(ei);
    }
}
