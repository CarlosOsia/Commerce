package com.tul.ecommerce.data.dto

import java.math.BigDecimal
import java.sql.Timestamp
import java.util.*

class ProductoDTO {
    private var uuid_producto : UUID? = null
    private var tipoProducto : UUID? = null
    private var sku: String? = null
    private var precio: BigDecimal? = null
    private var nombre: String? = null
    private var descripcion: String? = null
    private var creacionProducto: Timestamp? = null
    private var activo: Boolean? = null
}