package com.tul.ecommerce.data.dto

import java.math.BigInteger
import java.util.*

class CarritoProductosDTO (
    var uuid_carrito_producto : UUID? = null,
    var carrito : UUID? = null,
    var producto : UUID? = null,
    var cantidad: BigInteger? = null
)