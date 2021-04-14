package com.tul.ecommerce.data.mapper

import com.tul.ecommerce.data.dto.CarritoProductosDTO
import com.tul.ecommerce.data.entity.CarritoEntity
import com.tul.ecommerce.data.entity.CarritoProductosEntity
import com.tul.ecommerce.data.entity.ProductosEntity

object CarritoProductoMapper {
    fun toDTO(carritoProductosEntity: CarritoProductosEntity) : CarritoProductosDTO {
        return CarritoProductosDTO(uuidCarritoProducto = carritoProductosEntity.uuidCarritoProducto,
            cantidad = carritoProductosEntity.cantidad,
            carritoDTO = CarritoMapper.toDTO(carritoProductosEntity.carritoByCarrito ?: CarritoEntity()),
            productoDTO = ProductoMapper.toDTO(carritoProductosEntity.productosByProducto ?: ProductosEntity())
        )
    }

    fun toEntity(carritoProductosDTO: CarritoProductosDTO) : CarritoProductosEntity {
        return CarritoProductosEntity().apply {
            this.uuidCarritoProducto = carritoProductosDTO.uuidCarritoProducto
            this.cantidad = carritoProductosDTO.cantidad
            this.carritoByCarrito = CarritoMapper.toEntity(carritoProductosDTO.carritoDTO)
            this.productosByProducto = ProductoMapper.toEntity(carritoProductosDTO.productoDTO)
        }
    }
}