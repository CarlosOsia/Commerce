package com.tul.ecommerce.data.entity

import com.tul.ecommerce.data.enum.TipoProductoEnum
import jdk.nashorn.internal.ir.annotations.Ignore
import java.math.BigDecimal
import java.sql.Timestamp
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "productos", schema = "public", catalog = "ecommerce")
class ProductosEntity {
    @get:Column(name = "uuid_producto", nullable = false)
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var uuidProducto: UUID? = null

    @get:Column(name = "sku", nullable = false, length = -1)
    @get:Basic
    var sku: String? = null

    @get:Column(name = "precio", nullable = false, precision = 0)
    @get:Basic
    var precio: BigDecimal? = null

    @get:Column(name = "nombre", nullable = false, length = -1)
    @get:Basic
    var nombre: String? = null

    @get:Column(name = "descripcion", nullable = false, length = -1)
    @get:Basic
    var descripcion: String? = null

    @get:Column(name = "creacion_producto", nullable = false, insertable = false, updatable = false)
    @get:Basic
    var creacionProducto: Timestamp? = null

    @get:Column(name = "activo", nullable = false, insertable = false)
    @get:Basic
    var activo: Boolean? = null

    @get:Transient
    @get:Ignore
    var tipoProductoEnum: TipoProductoEnum? = null

    @PostLoad
    fun fillTransient() {
        this.tipoProductoEnum = TipoProductoEnum.of(this.tipoProductosByTipoProducto?.codigo)
    }

    @get:OneToMany(mappedBy = "productosByProducto")
    var carritoProductosByUuidProducto: Collection<CarritoProductosEntity>? = null

    @get:JoinColumn(name = "tipo_producto", referencedColumnName = "uuid_tipo_producto", nullable = false)
    @get:ManyToOne
    var tipoProductosByTipoProducto: TipoProductosEntity? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ProductosEntity
        return uuidProducto == that.uuidProducto &&
                sku == that.sku &&
                precio == that.precio &&
                nombre == that.nombre &&
                descripcion == that.descripcion &&
                creacionProducto == that.creacionProducto &&
                activo == that.activo
    }

    override fun hashCode(): Int {
        return Objects.hash(uuidProducto, sku, precio, nombre, descripcion, creacionProducto, activo)
    }
}