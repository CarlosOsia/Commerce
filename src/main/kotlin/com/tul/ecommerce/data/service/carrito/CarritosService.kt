package com.tul.ecommerce.data.service.carrito

import com.tul.ecommerce.data.dto.CarritoDTO

interface CarritosService {

    fun agregarCarrito(): CarritoDTO

    fun checkoutCarrito(carritoDTO: CarritoDTO): CarritoDTO

}