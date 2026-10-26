package fravega;

public class IVA implements Impuesto {
    private double porcentaje;

    public IVA(double porcentaje) {
        setPorcentaje(porcentaje);
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = Validacion.noNegativo(porcentaje);
    }

    public String getNombre() {
        return "IVA";
    }

    public double calcular(double precioBase) {
        return Validacion.noNegativo(precioBase) * porcentaje;
    }
}
