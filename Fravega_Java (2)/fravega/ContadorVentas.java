package fravega;

public class ContadorVentas implements ObservadorVenta {
    private Venta[] ventas = new Venta[2];
    private int cantidadVentas;

    public void alVender(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta es obligatoria.");
        }
        for (int i = 0; i < cantidadVentas; i++) {
            if (ventas[i] == venta) {
                return;
            }
        }
        if (cantidadVentas == ventas.length) {
            ampliarVentas();
        }
        ventas[cantidadVentas] = venta;
        cantidadVentas++;
    }

    private void ampliarVentas() {
        Venta[] nuevas = new Venta[ventas.length * 2];
        for (int i = 0; i < cantidadVentas; i++) {
            nuevas[i] = ventas[i];
        }
        ventas = nuevas;
    }

    public int cantidadEn(String fecha) {
        Validacion.texto(fecha);
        int cantidad = 0;
        for (int i = 0; i < cantidadVentas; i++) {
            if (ventas[i].getFecha().equals(fecha)) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
