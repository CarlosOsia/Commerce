package com.tul.ecommerce.data.dto

import java.math.BigDecimal
import java.sql.Timestamp
import java.util.*

data class CarritoDTO(
    var uuidCarrito : UUID? = null,
    var idCarrito : Long? = 0,
    val creacionCarrito: Timestamp? = null,
    var estadoCarrito: UUID? = null,
    var estadoCarritoDTO: EstadoCarritoDTO? = null,
    var total: BigDecimal? = BigDecimal(0)
){
    fun initCarritoDTO() : CarritoDTO {
        estadoCarritoDTO = EstadoCarritoDTO()
        return this
    }
}
