package com.tul.ecommerce.data.repositories

import com.tul.ecommerce.data.entity.CarritoEntity
import com.tul.ecommerce.data.entity.CarritoProductosEntity
import com.tul.ecommerce.data.entity.ProductosEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface CarritoProductoRepository : JpaRepository<CarritoProductosEntity, UUID> {
    @Query(value = "select cp.productosByProducto as productosCarrito from carrito_productos cp  where  cp.carritoByCarrito.uuidCarrito = :uuid")
    fun getTotalCarrito(uuid: UUID?): List<ProductosEntity>

    @Query(value = "select cp.cantidad from carrito_productos cp  where cp.carritoByCarrito.uuidCarrito = :carrito and cp.productosByProducto.uuidProducto = :producto")
    fun getQuantity(carrito: UUID?, producto: UUID?): BigDecimal

    @Query(value = "select count(cp.productosByProducto.uuidProducto) from carrito_productos cp where cp.productosByProducto.uuidProducto = :producto")
    fun getCountProductoInCarrito(@Param("producto") producto: UUID?): Int

    @Query(value = "select cp from carrito_productos cp where cp.productosByProducto.sku = :producto and cp.carritoByCarrito.idCarrito = :carrito")
    fun findFirstByCarritoAndProducto(carrito: Long?, producto: String?) : CarritoProductosEntity?

    @Query(value = "select cp from carrito_productos cp where cp.carritoByCarrito.uuidCarrito = :carrito")
    fun findAllByCarrito(@Param ("carrito") carrito: UUID?) : List<CarritoProductosEntity>

}