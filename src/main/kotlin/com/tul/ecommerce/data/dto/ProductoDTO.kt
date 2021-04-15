package com.tul.ecommerce.data.dto

import com.tul.ecommerce.data.enum.TipoProductoEnum
import java.math.BigDecimal
import java.sql.Timestamp
import java.util.*

data class ProductoDTO (
    var uuidProducto : UUID? = null,
    var tipoProducto : UUID? = null,
    var tipoProductoDTO: TipoProductoDTO? = null,
    var sku: String? = null,
    var precio: BigDecimal? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var creacionProducto: Timestamp? = null,
    var activo: Boolean? = null
){
    fun obtenerPrecio(): BigDecimal? {
        if(tipoProductoDTO?.codigo.equals(TipoProductoEnum.SIMPLE.codigo)){
            return precio
        }
        return precio?.divide(BigDecimal(2))
    }


}