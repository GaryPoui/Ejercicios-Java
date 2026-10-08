# Frávega en Java

Es un programa de consola para registrar ventas y calcular precios con impuestos.
Necesita Java 17 o superior y no usa librerías externas.

## Cómo ejecutarlo

En Linux se puede usar `./ejecutar.sh` y en Windows `ejecutar.bat`.
También se puede compilar y ejecutar desde la terminal:

```sh
mkdir -p out
javac -encoding UTF-8 -d out src/*.java test/*.java
java -cp out Main
java -cp out Pruebas
```

En Windows, usar `mkdir out` si la carpeta todavía no existe.

## Cómo funciona

Cada producto tiene un código, un nombre, un precio base y un tipo.
Todos pagan IVA. Los electrónicos también pagan EO y los productos de hogar, EI.

Las fórmulas son:

- IVA = precio base × porcentaje. El porcentaje inicial es 0,21.
- EO = (0,5 × precio base) / (4 × ganancias impositivas). Las ganancias iniciales son 4.
- EI = precio base / 4 + 0,3 × ganancias impositivas. Las ganancias iniciales son 3,50.

En el enunciado aparecen INA y El. Se interpretan como IVA y EI.
Los impuestos se calculan sobre el precio base. El total se redondea a dos
decimales usando `BigDecimal`.

Cuando una sucursal vende un producto, guarda la venta y avisa a contabilidad,
al depósito y al contador de ventas. Contabilidad y depósito se simulan con
mensajes en consola porque no están los datos para conectarse a las APIs reales.
Si falla un aviso, la venta queda guardada y se avisa a los demás.

El contador agrupa las ventas por día y no cuenta dos veces la misma venta.
Los porcentajes y las ganancias se pueden cambiar. Ese cambio afecta a los
precios nuevos, pero las ventas anteriores conservan el importe cobrado.
Los datos se guardan en memoria y se pierden al cerrar el programa.

## Clases principales

- `Producto` y `TipoProducto`: guardan los datos del producto y sus impuestos adicionales.
- `Impuesto`, `IVA`, `EO` y `EI`: definen los cálculos de los impuestos (Strategy).
- `CalculadorPrecio`: suma el precio base y los impuestos.
- `Sucursal`, `Venta` y `ObservadorVenta`: registran las ventas y los avisos (Observer).
- `AdaptadorContabilidad` y `AdaptadorDeposito`: conectan los avisos con cada API (Adapter).
- `ContadorVentas`: cuenta las ventas por fecha.

## Ejemplo y pruebas

Con un precio base de $1000, el televisor cuesta $1241,25 y la mesa $1461,05.
`Main` vende los dos productos, muestra el conteo y cambia los parámetros de
los impuestos para mostrar cómo se actualizan los precios.

`Pruebas` revisa los cálculos, los avisos, el conteo, los valores inválidos y
que las ventas anteriores mantengan su importe. Una prueba provoca un fallo
simulado para comprobar que los demás avisos se siguen enviando.
