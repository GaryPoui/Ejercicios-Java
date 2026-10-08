import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Pruebas {

    private static int verificaciones;

    private static void verificar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
        verificaciones++;
    }

    private static void importe(String esperado, BigDecimal real) {
        verificar(new BigDecimal(esperado).compareTo(real) == 0, esperado + " != " + real);
    }

    private static void rechaza(Runnable accion) {
        try {
            accion.run();
        } catch (IllegalArgumentException e) {
            verificaciones++;
            return;
        }
        throw new AssertionError("Debía rechazar el valor");
    }

    public static void main(String[] args) {
        IVA iva = new IVA(new BigDecimal("0.21"));
        EO eo = new EO(new BigDecimal("4"));
        EI ei = new EI(new BigDecimal("3.5"));
        TipoProducto electronico = new TipoProducto("Electrónico");
        electronico.agregarImpuesto(eo);
        TipoProducto hogar = new TipoProducto("Hogar");
        hogar.agregarImpuesto(ei);
        Producto tv = new Producto("TV", "TV", new BigDecimal("1000"), electronico);
        Producto mesa = new Producto("M", "Mesa", new BigDecimal("1000"), hogar);
        CalculadorPrecio calculador = new CalculadorPrecio(iva);
        importe("210", iva.calcular(tv.getPrecioBase()));
        importe("31.25", eo.calcular(tv.getPrecioBase()));
        importe("251.05", ei.calcular(mesa.getPrecioBase()));
        importe("1241.25", calculador.calcular(tv));
        importe("1461.05", calculador.calcular(mesa));
        Clock reloj = Clock.fixed(Instant.parse("2026-10-08T15:00:00Z"), ZoneOffset.UTC);
        Sucursal sucursal = new Sucursal("Centro", calculador, reloj);
        ContadorVentas contador = new ContadorVentas();
        List<BigDecimal> importes = new ArrayList<>();
        List<String> codigos = new ArrayList<>();
        sucursal.suscribir(new AdaptadorContabilidad((id, sede, total) -> importes.add(total)));
        sucursal.suscribir(new AdaptadorDeposito((id, sede, codigo) -> codigos.add(codigo)));
        sucursal.suscribir(contador);
        sucursal.suscribir(contador);
        Venta primera = sucursal.vender(tv);
        sucursal.vender(mesa);
        verificar(importes.size() == 2 && codigos.equals(List.of("TV", "M")), "Avisos externos");
        importe("1241.25", importes.get(0));
        importe("1461.05", importes.get(1));
        verificar(contador.cantidadEn(LocalDate.of(2026, 10, 8)) == 2, "Conteo diario");
        contador.alVender(primera);
        verificar(contador.cantidadEn(LocalDate.of(2026, 10, 8)) == 2, "Evento duplicado");
        contador.alVender(new Venta("Centro", tv, primera.getImporte(), LocalDateTime.of(2026, 10, 9, 0, 1)));
        verificar(contador.cantidadEn(LocalDate.of(2026, 10, 9)) == 1, "Nuevo día");
        verificar(contador.cantidadEn(LocalDate.of(2026, 10, 10)) == 0, "Día sin ventas");
        iva.setPorcentaje(new BigDecimal("0.25"));
        eo.setGananciasImpositivas(new BigDecimal("5"));
        ei.setGananciasImpositivas(new BigDecimal("4"));
        importe("1275", calculador.calcular(tv));
        importe("1501.2", calculador.calcular(mesa));
        importe("1241.25", primera.getImporte());
        TipoProducto nuevo = new TipoProducto("Nuevo");
        Producto productoNuevo = new Producto("N", "Nuevo", new BigDecimal("100"), nuevo);
        importe("125", calculador.calcular(productoNuevo));
        nuevo.agregarImpuesto(new Impuesto() {
            public String getNombre() {
                return "Adicional futuro";
            }
            public BigDecimal calcular(BigDecimal base) {
                return base.multiply(new BigDecimal("0.10"));
            }
        });
        importe("135", calculador.calcular(productoNuevo));
        importe("0.01", calculador.calcular(new Producto("R", "Redondeo", new BigDecimal("0.01"), new TipoProducto("R"))));
        rechaza(() -> new EO(BigDecimal.ZERO));
        rechaza(() -> iva.setPorcentaje(new BigDecimal("-0.1")));
        rechaza(() -> new Producto("X", "X", new BigDecimal("-1"), hogar));
        rechaza(() -> electronico.agregarImpuesto(eo));
        rechaza(() -> nuevo.agregarImpuesto(iva));
        sucursal.desuscribir(contador);
        sucursal.vender(tv);
        verificar(contador.cantidadEn(LocalDate.of(2026, 10, 8)) == 2, "Desuscripción");
        Sucursal otra = new Sucursal("Otra", calculador, reloj);
        otra.suscribir(v -> {
            throw new IllegalStateException("Fallo simulado esperado");
        });
        otra.suscribir(contador);
        otra.vender(tv);
        verificar(contador.cantidadEn(LocalDate.of(2026, 10, 8)) == 3, "Aislar fallo de observador");
        verificar(otra.getVentas().size() == 1, "Venta registrada pese al fallo externo");
        System.out.println("OK: " + verificaciones + " verificaciones superadas.");
    }
}
