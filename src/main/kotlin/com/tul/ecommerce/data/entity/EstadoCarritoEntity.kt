package com.tul.ecommerce.data.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "estado_carrito", schema = "public", catalog = "ecommerce")
class EstadoCarritoEntity {
    @get:Column(name = "uuid_estado_carrito", nullable = false, insertable = false, updatable = false)
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var uuidEstadoCarrito: UUID? = null

    @get:Column(name = "descripcion", nullable = false, length = -1)
    @get:Basic
    var descripcion: String? = null

    @get:Column(name = "codigo", nullable = false, length = -1)
    @get:Basic
    var codigo: String? = null

    @get:OneToMany(mappedBy = "estadoCarritoByEstadoCarrito")
    var carritosByUuidEstadoCarrito: Collection<CarritoEntity>? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as EstadoCarritoEntity
        return uuidEstadoCarrito == that.uuidEstadoCarrito &&
                descripcion == that.descripcion
    }

    override fun hashCode(): Int {
        return Objects.hash(uuidEstadoCarrito, descripcion)
    }
}