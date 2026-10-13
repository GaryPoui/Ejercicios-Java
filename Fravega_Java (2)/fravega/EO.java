package fravega;

public class EO implements Impuesto {
    private double gananciasImpositivas;

    public EO(double gananciasImpositivas) {
        setGananciasImpositivas(gananciasImpositivas);
    }

    public void setGananciasImpositivas(double gananciasImpositivas) {
        this.gananciasImpositivas = Validacion.positivo(gananciasImpositivas);
    }

    public String getNombre() {
        return "EO";
    }

    public double calcular(double precioBase) {
        return (0.5 * Validacion.noNegativo(precioBase)) / (4 * gananciasImpositivas);
    }
}
