package com.tul.ecommerce.data.mapper

import com.tul.ecommerce.data.dto.CarritoDTO
import com.tul.ecommerce.data.entity.CarritoEntity
import com.tul.ecommerce.data.entity.EstadoCarritoEntity
import java.math.BigDecimal

object CarritoMapper {
    fun toDTO(carritoEntity: CarritoEntity, total: BigDecimal? = BigDecimal(0)) : CarritoDTO {
        return CarritoDTO(uuidCarrito = carritoEntity.uuidCarrito,
        idCarrito = carritoEntity.idCarrito,
        creacionCarrito = carritoEntity.creacionCarrito,
        estadoCarrito = carritoEntity.estadoCarritoByEstadoCarrito?.uuidEstadoCarrito,
        estadoCarritoDTO = EstadoCarritoMapper.toDTO(carritoEntity.estadoCarritoByEstadoCarrito),
        total = total)
    }

    fun toEntity(carritoDTO: CarritoDTO, estadoCarritoEntity: EstadoCarritoEntity? = null) : CarritoEntity {
        return CarritoEntity().apply {
            this.uuidCarrito = carritoDTO.uuidCarrito
            this.idCarrito = carritoDTO.idCarrito
            this.creacionCarrito = carritoDTO.creacionCarrito
            this.estadoCarritoByEstadoCarrito = estadoCarritoEntity
        }
    }
}