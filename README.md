# Eccomerce
Proyecto java Spring boot con hibernate:
Enunciado 
Los servicios a desarrollar estarán basados en 2 grandes puntos del mundo eCommerce, por un lado las
compras y por el otro el Checkout (gestión del Carrito de compras).
 Compras realizadas (GET): El servicio deberá brindar el detalle de las compras realizadas por un
usuario en particular (identificado por dni).
o Filtros: se podrá filtrar por un período (From-To). Si se le envía solo Fecha-From, el
servicio deberá devolver todas las compras a partir de la fecha indicada.
o Ordenamiento: El servicio podrá ser solicitado según 2 tipos de orden: fechas y montos.
 Gestión del Carrito: Se deberán contemplar las acciones para poder llevar adelante el uso de un
carrito de compras, como ser:
o Crear y eliminar un carrito.
o Agregar y eliminar productos de un carrito.
o Consultar el estado de un carrito. Esta acción devolverá el total de productos que
contiene.
o Finalización de un carrito por compra. Esta acción cerrará el carrito, dando el valor final
del mismo (con promociones aplicadas si correspondiesen).Existen dos tipos de carritos, común y especial. Este hecho se determinará con un flag sobre el servicio de
creación del carrito (isSpecial:true) además de acompañarse la fecha de creación del mismo.
El cliente puede realizar varias compras en el mismo día.
No es necesario desarrollar ningún tipo de ABM de los productos, ni de clientes. Los productos enviados
al agregarse al carrito se tomarán como “válidos” y el precio indicado será el que se tome como válido
(además no se tiene en cuenta stocks de los mismos, así que no hace falta que realices control de este
punto).
Los productos deberán tener precios mayores a $100.
Para calcular el valor del carrito se debe tener en cuenta:
 Si se compran más de 3 productos se realizará un descuento de $100 para carritos comunes y de
$150 para carritos especiales.
 Si el cliente en un determinado mes calendario, realizó compras por más de $5.000, pasa a ser
considerado VIP y por lo tanto, en su próxima compra, se le aplicará un “descuento especial” de
$500 pesos, solo si la compra supera los $2000 (acumulable con el descuento del punto 1).
 Si el cliente “compra” 4 productos iguales (quantity:4), el sistema aplicará un descuento extra,
realizando la promoción 4x3 en dicho producto.
Observación: Sería bueno que en la determinación de si un cliente es vip o no, se consuma el servicio de
“compras realizadas” para poder iterar y determinar dicha situación (obteniendo el “mes” para el cálculo
sobre la fecha de finalización del carrito).
