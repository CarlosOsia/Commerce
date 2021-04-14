package com.tul.ecommerce.data.service.producto

import com.tul.ecommerce.data.dto.ProductoDTO
import java.util.*

interface ProductoService {
    fun agregarProducto(productoDTO: ProductoDTO): ProductoDTO?

    fun consultarProducto(productoDTO: ProductoDTO): ProductoDTO?

    fun consultarTodos(): List<ProductoDTO>?

    fun modificarProducto(productoDTO: ProductoDTO) : ProductoDTO?

    fun eliminarProducto(productoDTO: ProductoDTO)
}