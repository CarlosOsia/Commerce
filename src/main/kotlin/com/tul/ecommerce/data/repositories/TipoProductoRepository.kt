package com.tul.ecommerce.data.repositories

import com.tul.ecommerce.data.entity.TipoProductosEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TipoProductoRepository: JpaRepository<TipoProductosEntity, UUID> {
    fun findFirstByCodigo(@Param("codigo") codigo: String?): TipoProductosEntity?
}