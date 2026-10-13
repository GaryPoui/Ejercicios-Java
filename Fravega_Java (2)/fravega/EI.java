package fravega;

public class EI implements Impuesto {
    private double gananciasImpositivas;

    public EI(double gananciasImpositivas) {
        setGananciasImpositivas(gananciasImpositivas);
    }

    public void setGananciasImpositivas(double gananciasImpositivas) {
        this.gananciasImpositivas = Validacion.noNegativo(gananciasImpositivas);
    }

    public String getNombre() {
        return "EI";
    }

    public double calcular(double precioBase) {
        return Validacion.noNegativo(precioBase) / 4 + 0.3 * gananciasImpositivas;
    }
}
