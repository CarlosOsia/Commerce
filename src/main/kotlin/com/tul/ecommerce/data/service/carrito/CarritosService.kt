package com.tul.ecommerce.data.service.carrito

import com.tul.ecommerce.data.dto.CarritoDTO
import com.tul.ecommerce.data.dto.CarritoProductosDTO
import java.util.*

interface CarritosService {

    fun agregarCarrito(): CarritoDTO

    fun checkoutCarrito(carritoDTO: CarritoDTO): CarritoDTO

    fun agregarProducto(carritoProductosDTO: CarritoProductosDTO): CarritoProductosDTO

    fun listAllProducts(carritoUUID: UUID?): List<CarritoProductosDTO>

    fun modificarProducto(carritoProductosDTO: CarritoProductosDTO): CarritoProductosDTO

    fun eliminarProducto(carritoProductosDTO: CarritoProductosDTO?)
}