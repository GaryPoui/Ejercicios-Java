# Ejercicios-Java

## Ejercicio de stock y precios

La solución está en `stock_precios_java (2)`.

### Cómo funciona

- `Producto` tiene nombre, precio y stock.
- `Combo` contiene una lista de `Item`, por eso puede contener productos u otros combos.
- El precio de un combo es la suma de los precios de sus elementos.
- El stock de un combo es el menor stock de sus elementos. Si no tiene elementos, es cero.
- `Descuento` resta un monto fijo y evita que el precio final sea negativo.
- `Packaging` suma un monto fijo al precio.
- Los descuentos y los packaging no cambian el stock.
- Se pueden encadenar varios descuentos y packaging.

### Diagrama de clases simplificado

```text
                 <<interface>>
                      Item
              + calcularPrecio()
              + calcularStock()
                 /          \
                /            \
          Producto          Combo
                         contiene List<Item>
                              |
                              | implementa
                        ItemDecorator
                         /          \
                        /            \
                  Descuento       Packaging
```

### Patrones utilizados

- **Composite:** `Producto` y `Combo` implementan `Item`. Así se puede tratar un producto, un combo o un combo de combos de la misma forma.
- **Decorator:** `Descuento` y `Packaging` envuelven un `Item` y modifican su precio sin cambiar la clase original. Al envolver un decorador con otro, los descuentos son acumulables.

El ejemplo de `Main` muestra un combo, un combo anidado, dos descuentos acumulables y un packaging.
