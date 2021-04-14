package com.tul.ecommerce.data.dto

import java.math.BigInteger
import java.util.*

class CarritoProductosDTO (
    var uuidCarritoProducto : UUID? = null,
    var cantidad: BigInteger? = BigInteger.ONE,
    var carritoDTO: CarritoDTO,
    var productoDTO: ProductoDTO
)