package com.tul.ecommerce.data.mapper

import com.tul.ecommerce.data.dto.ProductoDTO
import com.tul.ecommerce.data.entity.ProductosEntity
import com.tul.ecommerce.data.entity.TipoProductosEntity
import java.util.*

object ProductoMapper {
    fun toDTO(productosEntity: Optional<ProductosEntity>): ProductoDTO {
        return ProductoDTO(
            uuid_producto = productosEntity.get().uuidProducto,
            nombre = productosEntity.get().nombre,
            descripcion = productosEntity.get().descripcion,
            precio = productosEntity.get().precio,
            sku = productosEntity.get().sku,
            creacionProducto = productosEntity.get().creacionProducto,
            tipoProducto = productosEntity.get().tipoProductosByTipoProducto?.uuidTipoProducto,
            tipoProductoDTO = TipoProductoMapper.toDTO(productosEntity.get().tipoProductosByTipoProducto),
            activo = productosEntity.get().activo
        )
    }

    fun toDTO(productosEntity: ProductosEntity): ProductoDTO {
        return toDTO(Optional.of(productosEntity))
    }

    fun toEntity(productoDTO: ProductoDTO, tipoProductosEntity: TipoProductosEntity? = null): ProductosEntity {
        return ProductosEntity().apply {
            this.uuidProducto = productoDTO.uuid_producto
            this.nombre = productoDTO.nombre
            this.descripcion = productoDTO.descripcion
            this.precio = productoDTO.precio
            this.sku = productoDTO.sku
            this.creacionProducto = productoDTO.creacionProducto
            this.tipoProductosByTipoProducto = tipoProductosEntity
            this.activo = productoDTO.activo
        }
    }
}