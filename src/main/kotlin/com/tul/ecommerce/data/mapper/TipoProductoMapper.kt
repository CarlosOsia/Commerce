package com.tul.ecommerce.data.mapper

import com.tul.ecommerce.data.dto.TipoProductoDTO
import com.tul.ecommerce.data.entity.TipoProductosEntity

object TipoProductoMapper {
    fun toDTO(tipoProductosEntity: TipoProductosEntity?): TipoProductoDTO {
        return TipoProductoDTO( uuid_tipo_producto = tipoProductosEntity?.uuidTipoProducto,
            codigo = tipoProductosEntity?.codigo, descripcion = tipoProductosEntity?.descripcion)
    }

    fun toEntity(tipoProductoDTO: TipoProductoDTO): TipoProductosEntity {
        return TipoProductosEntity().apply {
            this.uuidTipoProducto = tipoProductoDTO.uuid_tipo_producto
            this.codigo = tipoProductoDTO.codigo
            this.descripcion = tipoProductoDTO.descripcion
        }
    }
}