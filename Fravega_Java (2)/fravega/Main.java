package fravega;

public class Main {
    public static void main(String[] args) {
        IVA iva = new IVA(0.21);
        EO eo = new EO(4);
        EI ei = new EI(3.50);

        TipoElectronico electronico = new TipoElectronico(iva, eo);
        TipoHogar hogar = new TipoHogar(iva, ei);

        Producto televisor = new Producto("TV01", "Televisor", 1000, electronico);
        Producto mesa = new Producto("ME01", "Mesa", 1000, hogar);

        System.out.println("Precio del televisor: $" + televisor.calcularPrecio());
        System.out.println("Precio de la mesa: $" + mesa.calcularPrecio());

        ApiContable apiContable = new ApiContable();
        ApiDeposito apiDeposito = new ApiDeposito();
        AdaptadorContabilidad contabilidad = new AdaptadorContabilidad(apiContable);
        AdaptadorDeposito deposito = new AdaptadorDeposito(apiDeposito);
        ContadorVentas contador = new ContadorVentas();

        Sucursal sucursal = new Sucursal("Liniers", "2026-10-12");
        sucursal.suscribir(contabilidad);
        sucursal.suscribir(deposito);
        sucursal.suscribir(contador);

        System.out.println("\nVentas del primer dia:");
        Venta primeraVenta = sucursal.vender(televisor);
        sucursal.vender(mesa);
        System.out.println("Cantidad: " + contador.cantidadEn("2026-10-12"));

        iva.setPorcentaje(0.25);
        eo.setGananciasImpositivas(5);
        System.out.println("\nNuevo precio del televisor: $" + televisor.calcularPrecio());
        System.out.println("Importe de la primera venta: $" + primeraVenta.getImporte());

        sucursal.setFecha("2026-10-13");
        sucursal.vender(televisor);
        System.out.println("Ventas del segundo dia: " + contador.cantidadEn("2026-10-13"));

        sucursal.desuscribir(contador);
        sucursal.vender(mesa);
        System.out.println("Conteo despues de desuscribir: " + contador.cantidadEn("2026-10-13"));
        System.out.println("Ventas guardadas en la sucursal: " + sucursal.getVentas().length);
    }
}
