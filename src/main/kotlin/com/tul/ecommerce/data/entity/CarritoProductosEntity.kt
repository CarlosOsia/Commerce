package com.tul.ecommerce.data.entity

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "carrito_productos", schema = "public")
class CarritoProductosEntity {
    @get:Column(name = "uuid_carrito_producto", nullable = false)
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var uuidCarritoProducto: UUID? = null

    @get:Basic
    var carritoUUID: UUID? = null

    @get:Basic
    var productoUUID: UUID? = null

    @get:Column(name = "cantidad", nullable = false, precision = 0)
    @get:Basic
    var cantidad: BigInteger? = null

    @get:JoinColumn(name = "carrito", referencedColumnName = "uuid_carrito", nullable = false)
    @get:ManyToOne
    var carritoByCarrito: CarritoEntity? = null

    @get:JoinColumn(name = "producto", referencedColumnName = "uuid_producto", nullable = false)
    @get:ManyToOne
    var productosByProducto: ProductosEntity? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as CarritoProductosEntity
        return uuidCarritoProducto == that.uuidCarritoProducto &&
                carritoUUID == that.carritoUUID &&
                productoUUID == that.productoUUID &&
                cantidad == that.cantidad
    }

    override fun hashCode(): Int {
        return Objects.hash(uuidCarritoProducto, carritoUUID, productoUUID, cantidad)
    }
}