package com.tul.ecommerce.data.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tipo_productos", schema = "public", catalog = "ecommerce")
class TipoProductosEntity {
    @get:Column(name = "uuid_tipo_producto", nullable = false)
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var uuidTipoProducto: UUID? = null

    @get:Column(name = "descripcion", nullable = false, length = -1)
    @get:Basic
    var descripcion: String? = null

    @get:Column(name = "codigo", nullable = false, length = -1)
    @get:Basic
    var codigo: String? = null

    @get:OneToMany(mappedBy = "tipoProductosByTipoProducto")
    var productosByUuidTipoProducto: Collection<ProductosEntity>? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as TipoProductosEntity
        return uuidTipoProducto == that.uuidTipoProducto &&
                descripcion == that.descripcion
    }

    override fun hashCode(): Int {
        return Objects.hash(uuidTipoProducto, descripcion)
    }
}