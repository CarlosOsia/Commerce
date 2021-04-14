package com.tul.ecommerce.data.repositories

import com.tul.ecommerce.data.entity.ProductosEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.*

@org.springframework.stereotype.Repository
interface ProductosRepository : JpaRepository<ProductosEntity,UUID> {
    fun findFirstByUuidProducto(@Param("uuid_producto") uuid: UUID?): ProductosEntity?
    fun findFirstBySku(@Param("sku") sku: String?): ProductosEntity?
}