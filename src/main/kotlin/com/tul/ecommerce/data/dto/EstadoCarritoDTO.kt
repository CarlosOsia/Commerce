package com.tul.ecommerce.data.dto

import com.tul.ecommerce.data.enum.EstadoCarritoEnum
import java.util.*

data class EstadoCarritoDTO (
    var uuid_estado_carrito : UUID? = null,
    var descripcion : String? = null,
    var codigo : String? = EstadoCarritoEnum.PENDIENTE.codigo
)