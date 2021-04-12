package com.tul.ecommerce.data.repositories

import com.tul.ecommerce.data.entity.EstadoCarritoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EstadoCarritoRepository : JpaRepository<EstadoCarritoEntity, UUID> {

    fun findFirstByCodigo(@Param("codigo") codigo: String?): EstadoCarritoEntity?
}