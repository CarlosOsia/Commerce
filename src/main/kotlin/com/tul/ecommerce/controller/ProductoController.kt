package com.tul.ecommerce.controller

import com.tul.ecommerce.data.dto.ProductoDTO
import com.tul.ecommerce.data.service.producto.ProductoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/producto"])
class ProductoController @Autowired constructor(private val productoService: ProductoService) {

    @PostMapping(value = ["/add"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addProducto(@RequestBody productoDTO: ProductoDTO) : ResponseEntity<ProductoDTO> =
        ResponseEntity<ProductoDTO>(this.productoService.agregarProducto(productoDTO), HttpStatus.CREATED)

    @GetMapping(value = ["/get"])
    fun getProducto(@RequestBody productoDTO: ProductoDTO) : ResponseEntity<ProductoDTO> =
        ResponseEntity<ProductoDTO>(this.productoService.consultarProducto(productoDTO), HttpStatus.OK)

    @GetMapping(value = ["/list"])
    fun getAll() : ResponseEntity<List<ProductoDTO>> =
        ResponseEntity<List<ProductoDTO>>(this.productoService.consultarTodos(), HttpStatus.OK)

    @PutMapping(value = ["/edit"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun editProducto(@RequestBody productoDTO: ProductoDTO) : ResponseEntity<ProductoDTO> =
        ResponseEntity<ProductoDTO>(this.productoService.modificarProducto(productoDTO), HttpStatus.OK)

    @DeleteMapping(value = ["/delete"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteProducto(@RequestBody productoDTO: ProductoDTO){
        this.productoService.eliminarProducto(productoDTO)
    }
}