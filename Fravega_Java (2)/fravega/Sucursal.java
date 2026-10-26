package fravega;

public class Sucursal {
    private String nombre;
    private String fecha;
    private ObservadorVenta[] observadores = new ObservadorVenta[2];
    private int cantidadObservadores;
    private Venta[] ventas = new Venta[2];
    private int cantidadVentas;

    public Sucursal(String nombre, String fecha) {
        this.nombre = Validacion.texto(nombre);
        this.fecha = Validacion.texto(fecha);
    }

    public void setFecha(String fecha) {
        this.fecha = Validacion.texto(fecha);
    }

    public void suscribir(ObservadorVenta observador) {
        if (observador == null) {
            throw new IllegalArgumentException("El observador es obligatorio.");
        }
        for (int i = 0; i < cantidadObservadores; i++) {
            if (observadores[i] == observador) {
                return;
            }
        }
        if (cantidadObservadores == observadores.length) {
            ampliarObservadores();
        }
        observadores[cantidadObservadores] = observador;
        cantidadObservadores++;
    }

    private void ampliarObservadores() {
        ObservadorVenta[] nuevos = new ObservadorVenta[observadores.length * 2];
        for (int i = 0; i < cantidadObservadores; i++) {
            nuevos[i] = observadores[i];
        }
        observadores = nuevos;
    }

    public void desuscribir(ObservadorVenta observador) {
        for (int i = 0; i < cantidadObservadores; i++) {
            if (observadores[i] == observador) {
                for (int j = i; j < cantidadObservadores - 1; j++) {
                    observadores[j] = observadores[j + 1];
                }
                cantidadObservadores--;
                observadores[cantidadObservadores] = null;
                return;
            }
        }
    }

    public Venta vender(Producto producto) {
        Venta venta = new Venta(nombre, producto, fecha);
        if (cantidadVentas == ventas.length) {
            ampliarVentas();
        }
        ventas[cantidadVentas] = venta;
        cantidadVentas++;
        avisarObservadores(venta);
        return venta;
    }

    private void ampliarVentas() {
        Venta[] nuevas = new Venta[ventas.length * 2];
        for (int i = 0; i < cantidadVentas; i++) {
            nuevas[i] = ventas[i];
        }
        ventas = nuevas;
    }

    private void avisarObservadores(Venta venta) {
        ObservadorVenta[] destinatarios = new ObservadorVenta[cantidadObservadores];
        for (int i = 0; i < cantidadObservadores; i++) {
            destinatarios[i] = observadores[i];
        }
        for (int i = 0; i < destinatarios.length; i++) {
            try {
                destinatarios[i].alVender(venta);
            } catch (RuntimeException error) {
                System.out.println("No se pudo avisar a un observador: " + error.getMessage());
            }
        }
    }

    public Venta[] getVentas() {
        Venta[] copia = new Venta[cantidadVentas];
        for (int i = 0; i < cantidadVentas; i++) {
            copia[i] = ventas[i];
        }
        return copia;
    }
}
