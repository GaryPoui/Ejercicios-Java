package fravega;

public abstract class TipoProducto {
    private Impuesto[] impuestos;
    private int cantidadImpuestos;

    protected TipoProducto(IVA iva) {
        if (iva == null) {
            throw new IllegalArgumentException("El IVA es obligatorio.");
        }
        impuestos = new Impuesto[2];
        impuestos[0] = iva;
        cantidadImpuestos = 1;
    }

    public void agregarImpuesto(Impuesto impuesto) {
        if (impuesto == null) {
            throw new IllegalArgumentException("El impuesto es obligatorio.");
        }
        for (int i = 0; i < cantidadImpuestos; i++) {
            if (impuestos[i].getNombre().equalsIgnoreCase(impuesto.getNombre())) {
                throw new IllegalArgumentException("Ese impuesto ya esta agregado.");
            }
        }
        if (cantidadImpuestos == impuestos.length) {
            ampliarImpuestos();
        }
        impuestos[cantidadImpuestos] = impuesto;
        cantidadImpuestos++;
    }

    private void ampliarImpuestos() {
        Impuesto[] nuevos = new Impuesto[impuestos.length * 2];
        for (int i = 0; i < cantidadImpuestos; i++) {
            nuevos[i] = impuestos[i];
        }
        impuestos = nuevos;
    }

    public Impuesto[] getImpuestos() {
        Impuesto[] copia = new Impuesto[cantidadImpuestos];
        for (int i = 0; i < cantidadImpuestos; i++) {
            copia[i] = impuestos[i];
        }
        return copia;
    }
}
