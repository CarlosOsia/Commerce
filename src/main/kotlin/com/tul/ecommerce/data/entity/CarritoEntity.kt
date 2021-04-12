package com.tul.ecommerce.data.entity

import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "carrito", schema = "public", catalog = "ecommerce")
class CarritoEntity {
    @get:Column(name = "uuid_carrito", nullable = false, insertable = false, updatable = false)
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var uuidCarrito: UUID? = null

    @get:Column(name = "id_carrito", nullable = false, insertable = false, updatable = false)
    @get:Basic
    var idCarrito: Long? = null

    @get:Column(name = "creacion_carrito", insertable = false, updatable = false)
    @get:Basic
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var creacionCarrito: Timestamp? = null

    @get:JoinColumn(name = "estado_carrito", referencedColumnName = "uuid_estado_carrito")
    @get:ManyToOne
    var estadoCarritoByEstadoCarrito: EstadoCarritoEntity? = null

    @get:OneToMany(mappedBy = "carritoByCarrito")
    var carritoProductosByUuidCarrito: Collection<CarritoProductosEntity>? = null

    @get:OneToMany(mappedBy = "carritoByCarrito")
    var facturacionsByUuidCarrito: Collection<FacturacionEntity>? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as CarritoEntity
        return uuidCarrito == that.uuidCarrito &&
                idCarrito == that.idCarrito &&
                creacionCarrito == that.creacionCarrito
    }

    override fun hashCode(): Int {
        return Objects.hash(uuidCarrito, idCarrito, creacionCarrito)
    }
}