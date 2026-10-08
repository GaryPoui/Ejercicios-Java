import java.math.BigDecimal;
import java.util.UUID;

public interface ApiContable {

    void registrarImporte(UUID ventaId, String sucursal, BigDecimal importe);
}
