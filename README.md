# Proyecto-Integrador

ROSETAS CAKESHOP

```mermaid

 class Producto {
  +int idProducto
  +String nombre
  +String tipo
}

class Cliente {
  +int idCliente
  +String nombre
}

class Movimiento {
  +int idMovimiento
  +double saldoAnterior
  +double movimiento
  +double saldoFinal
  +calcularSaldo() : double
}

class SistemaRosetas {
  +main() : void
}

' Relaciones
Cliente "1" -- "*" Producto : tiene
Producto "1" -- "*" Movimiento : registra
SistemaRosetas "1" -- "*" Cliente : gestiona
```
