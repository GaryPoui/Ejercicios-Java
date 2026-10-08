import java.util.UUID;

public interface ApiDeposito {

    void prepararProducto(UUID ventaId, String sucursal, String codigoProducto);
}
