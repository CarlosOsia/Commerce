package com.tul.ecommerce.data.dto

import com.tul.ecommerce.data.enum.EstadoCarritoEnum
import com.tul.ecommerce.data.enum.TipoProductoEnum
import java.util.*

data class TipoProductoDTO (
    var uuidTipoProducto : UUID? = null,
    var descripcion : String? = null,
    var codigo : String? = TipoProductoEnum.SIMPLE.codigo
)