import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        IVA iva = new IVA(new BigDecimal("0.21"));
        EO eo = new EO(new BigDecimal("4"));
        EI ei = new EI(new BigDecimal("3.50"));

        TipoProducto electronico = new TipoProducto("Electrónico");
        electronico.agregarImpuesto(eo);

        TipoProducto hogar = new TipoProducto("Hogar");
        hogar.agregarImpuesto(ei);

        Producto televisor = new Producto("TV01", "Televisor", new BigDecimal("1000"), electronico);
        Producto mesa = new Producto("ME01", "Mesa", new BigDecimal("1000"), hogar);

        CalculadorPrecio calculador = new CalculadorPrecio(iva);

        Clock reloj = Clock.system(ZoneId.of("America/Argentina/Buenos_Aires"));
        Sucursal sucursal = new Sucursal("Liniers", calculador, reloj);

        ContadorVentas contador = new ContadorVentas();

        ApiContable contabilidad = new ApiContable() {
            public void registrarImporte(UUID ventaId, String sucursal, BigDecimal importe) {
                System.out.println("[Contabilidad simulada] " + sucursal + " registra $" + importe);
            }
        };

        ApiDeposito deposito = new ApiDeposito() {
            public void prepararProducto(UUID ventaId, String sucursal, String codigoProducto) {
                System.out.println("[Depósito simulado] " + sucursal + " prepara " + codigoProducto);
            }
        };

        sucursal.suscribir(new AdaptadorContabilidad(contabilidad));
        sucursal.suscribir(new AdaptadorDeposito(deposito));
        sucursal.suscribir(contador);

        System.out.println("Televisor: $" + calculador.calcular(televisor));
        System.out.println("Mesa: $" + calculador.calcular(mesa));

        Venta primera = sucursal.vender(televisor);
        sucursal.vender(mesa);

        System.out.println("Ventas de hoy: " + contador.cantidadEn(LocalDate.now(reloj)));

        iva.setPorcentaje(new BigDecimal("0.25"));
        eo.setGananciasImpositivas(new BigDecimal("5"));
        ei.setGananciasImpositivas(new BigDecimal("4"));

        System.out.println("Televisor con parámetros nuevos: $" + calculador.calcular(televisor));
        System.out.println("Mesa con parámetros nuevos: $" + calculador.calcular(mesa));

        System.out.println("Primera venta conserva: $" + primera.getImporte());

        TipoProducto nuevoTipo = new TipoProducto("Nueva categoría");
        Producto nuevo = new Producto("N01", "Producto nuevo", new BigDecimal("100"), nuevoTipo);

        System.out.println("Nueva categoría, IVA obligatorio: $" + calculador.calcular(nuevo));
    }
}
