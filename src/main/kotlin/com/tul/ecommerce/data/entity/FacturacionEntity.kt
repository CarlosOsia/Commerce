package com.tul.ecommerce.data.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "facturacion", schema = "public", catalog = "ecommerce")
class FacturacionEntity {
    @get:Column(name = "uuid_facturacion", nullable = false)
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var uuidFacturacion: UUID? = null

    @get:Basic
    var carritoUUID: UUID? = null

    @get:Column(name = "subtotal", nullable = false, precision = 0)
    @get:Basic
    var subtotal: BigDecimal? = null

    @get:Column(name = "descuento", nullable = false, precision = 0)
    @get:Basic
    var descuento: BigDecimal? = null

    @get:Column(name = "total", nullable = false, precision = 0)
    @get:Basic
    var total: BigDecimal? = null

    @get:JoinColumn(name = "carrito", referencedColumnName = "uuid_carrito", nullable = false)
    @get:ManyToOne
    var carritoByCarrito: CarritoEntity? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as FacturacionEntity
        return uuidFacturacion == that.uuidFacturacion &&
                carritoUUID == that.carritoUUID &&
                subtotal == that.subtotal &&
                descuento == that.descuento &&
                total == that.total
    }

    override fun hashCode(): Int {
        return Objects.hash(uuidFacturacion, carritoUUID, subtotal, descuento, total)
    }
}