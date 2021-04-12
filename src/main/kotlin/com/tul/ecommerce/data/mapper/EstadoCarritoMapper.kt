package com.tul.ecommerce.data.mapper

import com.tul.ecommerce.data.dto.CarritoDTO
import com.tul.ecommerce.data.dto.EstadoCarritoDTO
import com.tul.ecommerce.data.entity.CarritoEntity
import com.tul.ecommerce.data.entity.EstadoCarritoEntity

object EstadoCarritoMapper {
    /*fun toEntity(estadoCarritoDTO: EstadoCarritoDTO) : EstadoCarritoEntity {
        return EstadoCarritoEntity().apply {
            this.uuidEstadoCarrito = estadoCarritoDT
            this.idCarrito = carritoDTO.idCarrito
            this.estadoCarritoByEstadoCarrito = EstadoCarritoEntity().apply {
                this.uuidEstadoCarrito = carritoDTO.estadoCarrito
            }
        }
    }*/
    fun toDTO(estadoCarritoEntity: EstadoCarritoEntity?): EstadoCarritoDTO{
        return EstadoCarritoDTO(uuid_estado_carrito = estadoCarritoEntity?.uuidEstadoCarrito,
        codigo = estadoCarritoEntity?.codigo, descripcion = estadoCarritoEntity?.descripcion)
    }
}