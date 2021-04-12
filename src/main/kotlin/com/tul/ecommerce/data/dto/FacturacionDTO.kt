package com.tul.ecommerce.data.dto

import java.math.BigDecimal
import java.util.*

class FacturacionDTO {
    private var uuid_facturacion : UUID? = null
    private var carrito : UUID? = null
    private var subtotal: BigDecimal? = null
    private var descuento: BigDecimal? = null
    private var total: BigDecimal? = null
}