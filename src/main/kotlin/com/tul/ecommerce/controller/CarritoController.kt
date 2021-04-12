package com.tul.ecommerce.controller

import com.tul.ecommerce.data.dto.CarritoDTO
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

    @PostMapping(value = ["/checkout"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun editCarrito(@RequestBody carritoDTO: CarritoDTO): ResponseEntity<CarritoDTO?> =
        ResponseEntity<CarritoDTO?>(this.carritosService.checkoutCarrito(carritoDTO), HttpStatus.OK)
}