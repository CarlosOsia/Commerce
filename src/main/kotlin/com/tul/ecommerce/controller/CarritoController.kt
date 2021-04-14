package com.tul.ecommerce.controller

import com.tul.ecommerce.data.dto.CarritoDTO
import com.tul.ecommerce.data.dto.CarritoProductosDTO
import com.tul.ecommerce.data.service.carrito.CarritosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/carrito"])
class CarritoController @Autowired constructor(private val carritosService: CarritosService) {


    @PostMapping(value = ["/add"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addCarrito(): ResponseEntity<CarritoDTO?> =
        ResponseEntity<CarritoDTO?>(this.carritosService.agregarCarrito(), HttpStatus.CREATED)

    @PutMapping(value = ["/checkout"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun editCarrito(@RequestBody carritoDTO: CarritoDTO): ResponseEntity<CarritoDTO?> =
        ResponseEntity<CarritoDTO?>(this.carritosService.checkoutCarrito(carritoDTO), HttpStatus.OK)

    @PostMapping(value = ["/addproduct"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addProduct(@RequestBody carritoProductosDTO: CarritoProductosDTO): ResponseEntity<CarritoProductosDTO?> =
        ResponseEntity<CarritoProductosDTO?>(this.carritosService.agregarProducto(carritoProductosDTO), HttpStatus.OK)

    @GetMapping(value = ["/listproduct"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listProduct(@RequestBody carritoDTO: CarritoDTO): ResponseEntity<List<CarritoProductosDTO>> =
        ResponseEntity<List<CarritoProductosDTO>>(this.carritosService.listAllProducts(carritoDTO.uuidCarrito), HttpStatus.OK)

    @PutMapping(value = ["/editproduct"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun editProduct(@RequestBody carritoProductosDTO: CarritoProductosDTO): ResponseEntity<CarritoProductosDTO> =
        ResponseEntity<CarritoProductosDTO>(this.carritosService.modificarProducto(carritoProductosDTO), HttpStatus.OK)

    @DeleteMapping(value = ["/deleteproduct"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteProduct(@RequestBody carritoProductosDTO: CarritoProductosDTO){
        this.carritosService.eliminarProducto(carritoProductosDTO)
    }

}