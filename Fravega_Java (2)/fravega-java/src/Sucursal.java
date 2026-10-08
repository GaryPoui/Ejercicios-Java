import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Sucursal {

    private final String nombre;
    private final CalculadorPrecio calculador;
    private final Clock reloj;
    private final List<ObservadorVenta> observadores = new ArrayList<>();
    private final List<Venta> ventas = new ArrayList<>();

    public Sucursal(String nombre, CalculadorPrecio calculador, Clock reloj) {
        this.nombre = Validacion.texto(nombre);
        this.calculador = Objects.requireNonNull(calculador);
        this.reloj = Objects.requireNonNull(reloj);
    }

    public void suscribir(ObservadorVenta observador) {
        Objects.requireNonNull(observador);

        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void desuscribir(ObservadorVenta observador) {
        observadores.remove(observador);
    }

    public Venta vender(Producto producto) {
        Venta venta = new Venta(nombre, producto, calculador.calcular(producto), LocalDateTime.now(reloj));
        ventas.add(venta);

        for (ObservadorVenta observador : List.copyOf(observadores)) {
            try {
                observador.alVender(venta);
            } catch (RuntimeException e) {
                System.err.println("Falló aviso de venta " + venta.getId() + ": " + e.getMessage());
            }
        }

        return venta;
    }

    public List<Venta> getVentas() {
        return List.copyOf(ventas);
    }
}
