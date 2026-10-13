package fravega;

public class Validacion {
    public static double noNegativo(double valor) {
        if (valor < 0 || valor != valor || valor > Double.MAX_VALUE) {
            throw new IllegalArgumentException("El valor debe ser un numero no negativo.");
        }
        return valor;
    }

    public static double positivo(double valor) {
        noNegativo(valor);
        if (valor == 0) {
            throw new IllegalArgumentException("El valor debe ser mayor que cero.");
        }
        return valor;
    }

    public static String texto(String valor) {
        if (valor == null || valor.trim().length() == 0) {
            throw new IllegalArgumentException("El texto es obligatorio.");
        }
        return valor;
    }

    public static double redondearPrecio(double precio) {
        noNegativo(precio);
        long centavos = (long) (precio * 100 + 0.5);
        return centavos / 100.0;
    }
}
