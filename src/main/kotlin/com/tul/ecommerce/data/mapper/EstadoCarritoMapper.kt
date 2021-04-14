package com.tul.ecommerce.data.mapper

import com.tul.ecommerce.data.dto.EstadoCarritoDTO
import com.tul.ecommerce.data.entity.EstadoCarritoEntity

object EstadoCarritoMapper {
    fun toDTO(estadoCarritoEntity: EstadoCarritoEntity?): EstadoCarritoDTO{
        return EstadoCarritoDTO(uuidEstadoCarrito = estadoCarritoEntity?.uuidEstadoCarrito,
        codigo = estadoCarritoEntity?.codigo, descripcion = estadoCarritoEntity?.descripcion)
    }
}