import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductoTest {

    private Producto casco;
    private Producto guantes;
    private Producto chaleco;
    private Producto piloto;
    private Combo comboSimple;

    @BeforeEach
    public void init() {
        this.casco = new ProductoSimple("Casco", 100, 5);
        this.guantes = new ProductoSimple("Guantes", 100, 54);
        this.chaleco = new ProductoSimple("Chaleco", 100, 3);
        this.piloto = new ProductoSimple("Piloto", 100, 2);

        this.comboSimple = new Combo("Combo simple");
        this.comboSimple.agregarProducto(casco, chaleco, guantes);

    }

    @Test
    public void comboSimpleVale300() {
        Assertions.assertEquals(300.0, this.comboSimple.precio());
    }

    @Test
    public void comboRecargadoVale400() {
        Combo comboRecargado = new Combo("Combo recargado");
        comboRecargado.agregarProducto(this.comboSimple, this.piloto);

        Assertions.assertEquals(400.0, comboRecargado.precio());
    }

    @Test
    public void comboSimpleStock(){

        Assertions.assertEquals(3, this.comboSimple.stock());
    }

    @Test
    public void comboSimpleConDescVale200(){
        DescuentoFijo descuentoDe100 = new DescuentoFijo("Desc de 100", this.comboSimple, 100.0);
        Assertions.assertEquals(200.0, descuentoDe100.precio());
        Assertions.assertEquals(3, descuentoDe100.stock());
    }

    @Test
    public void combosConPaquetes(){
        Packaging paqueteParaSimple = new Packaging("Paquete de 50", this.comboSimple, 50);
        Assertions.assertEquals(350, paqueteParaSimple.precio());
        Combo comboRecargado = new Combo("Combo recargado");
        comboRecargado.agregarProducto(paqueteParaSimple, this.piloto);
        //pruebo
        Packaging paqueteGrande = new Packaging("Paquete grande", comboRecargado, 100);
        //pruebo
        DescuentoFijo descuentoDe100 = new DescuentoFijo("Desc de 100", paqueteGrande, 100.00);
        //pruebo
        DescuentoFijo descuentoDe50 = new DescuentoFijo("Desc de 50", descuentoDe100, 50.00);

        Assertions.assertEquals(400.0, descuentoDe50.precio());

    }
}