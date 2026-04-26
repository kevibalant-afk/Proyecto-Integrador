# Proyecto-Integrador

ROSETAS CAKESHOP

``` mermaind

 class Producto{
+ int id_Producto
+ string Nombre
+ string tipo
 }
Cliente{
+ int id_Cliente
+ int id_Producto
+ string Nombre
}
Movimiento{
+ int id_Movimiento
+ int id_Producto
+ double saldoAnterior
+ double movimiento
+ double saldoFinal
}  
SistemaRosetas{
+ int id_Rosetas
+ int id_Cliente
}
Cliente "1" -- "*" Producto : tiene productos
Movimiento "1" -- "*" Producto : tiene uno o varios
SistemaRosetas "1" -- "*" Cliente : gestiona la ejecucion
