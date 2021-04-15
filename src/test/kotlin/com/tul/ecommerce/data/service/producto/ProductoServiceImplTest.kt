package com.tul.ecommerce.data.service.producto


import com.tul.ecommerce.data.dto.ProductoDTO
import com.tul.ecommerce.data.dto.TipoProductoDTO
import com.tul.ecommerce.data.entity.ProductosEntity
import com.tul.ecommerce.data.entity.TipoProductosEntity
import com.tul.ecommerce.data.exceptions.CrudException
import com.tul.ecommerce.data.mapper.TipoProductoMapper
import com.tul.ecommerce.data.repositories.CarritoProductoRepository
import com.tul.ecommerce.data.repositories.ProductosRepository
import com.tul.ecommerce.data.repositories.TipoProductoRepository

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.*

internal class ProductoServiceImplTest {

    private val productosRepository: ProductosRepository = Mockito.mock(ProductosRepository::class.java)
    private val tipoProductoRepository: TipoProductoRepository = Mockito.mock(TipoProductoRepository::class.java)
    private val carritoProductoRepository: CarritoProductoRepository = Mockito.mock(CarritoProductoRepository::class.java)
    private val productoServiceImpl = ProductoServiceImpl(productosRepository, tipoProductoRepository, carritoProductoRepository)

    @Test(expected = CrudException::class)
    fun `Intentar agregar producto con sku, nombre o descripcion vacio arrojar excepcion`() {
        val productosDTO = ProductoDTO()
        productoServiceImpl.agregarProducto(productosDTO)
    }

    @Test
    fun `Agregar producto`() {
        val productosDTO = ProductoDTO(sku = "sku de prueba",
            descripcion = "descripcion de prueba",
            nombre = "producto1",
            tipoProductoDTO = TipoProductoDTO(codigo = "SIMPLE")
        )

        val tipoProducto = TipoProductosEntity().apply {
            this.uuidTipoProducto = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
            this.codigo = "SIMPLE"
            this.descripcion = "Simple"
        }
        `when`(tipoProductoRepository.findFirstByCodigo(anyString())).thenReturn(
            tipoProducto
        )
        `when`(productosRepository.save(any(ProductosEntity::class.java))).thenReturn(
            ProductosEntity().apply {
                this.uuidProducto = UUID.fromString("789e4567-e89b-12d3-a456-426614174000")
                this.nombre = productosDTO.nombre
                this.descripcion = productosDTO.descripcion
                this.sku = productosDTO.sku
                this.tipoProductosByTipoProducto = tipoProducto
            }
        )
        val product = productoServiceImpl.agregarProducto(productosDTO)
        assertEquals(ProductoDTO(
            sku = "sku de prueba",
            descripcion = "descripcion de prueba",
            nombre = "producto1",
            tipoProductoDTO = TipoProductoMapper.toDTO(tipoProducto),
            uuidProducto = UUID.fromString("789e4567-e89b-12d3-a456-426614174000"),
            tipoProducto = tipoProducto.uuidTipoProducto
        ), product)
    }

    @Test(expected = CrudException::class)
    fun `buscar producto sin enviar sku o uuis arrojar excepcion`() {
        val productosDTO = ProductoDTO()
        productoServiceImpl.consultarProducto(productosDTO)
    }

    @Test
    fun `consultar producto`() {
        val productosDTO = ProductoDTO(
            sku = "sku de prueba",
            descripcion = "descripcion de prueba",
            nombre = "producto1",
            uuidProducto = UUID.fromString("789e4567-e89b-12d3-a456-426614174000"),
            tipoProductoDTO = TipoProductoDTO()
        )
        `when`(productosRepository.findFirstBySku(productosDTO.sku)).thenReturn(
            ProductosEntity().apply {
                this.uuidProducto = productosDTO.uuidProducto
                this.nombre = productosDTO.nombre
                this.descripcion = productosDTO.descripcion
                this.sku = productosDTO.sku
                this.tipoProductosByTipoProducto = TipoProductosEntity().apply {this.codigo = "SIMPLE"}
            }
        )
        val product = productoServiceImpl.consultarProducto(productosDTO)
        assertEquals(productosDTO, product)
    }

}