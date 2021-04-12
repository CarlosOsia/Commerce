package com.tul.ecommerce.data.repositories

import com.tul.ecommerce.data.entity.CarritoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CarritosRepository : JpaRepository<CarritoEntity, UUID> {
}