package com.tul.ecommerce.data.service.carrito


import com.tul.ecommerce.data.dto.CarritoDTO
import com.tul.ecommerce.data.dto.EstadoCarritoDTO
import com.tul.ecommerce.data.dto.ProductoDTO
import com.tul.ecommerce.data.dto.TipoProductoDTO
import com.tul.ecommerce.data.entity.CarritoEntity
import com.tul.ecommerce.data.entity.EstadoCarritoEntity
import com.tul.ecommerce.data.entity.ProductosEntity
import com.tul.ecommerce.data.entity.TipoProductosEntity
import com.tul.ecommerce.data.exceptions.CrudException
import com.tul.ecommerce.data.mapper.EstadoCarritoMapper
import com.tul.ecommerce.data.mapper.TipoProductoMapper
import com.tul.ecommerce.data.repositories.CarritoProductoRepository
import com.tul.ecommerce.data.repositories.CarritosRepository
import com.tul.ecommerce.data.repositories.EstadoCarritoRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import java.math.BigDecimal
import java.util.*

internal class CarritosServiceImplTest {

    private val carritosRepository: CarritosRepository = Mockito.mock(CarritosRepository::class.java)
    private val estadoCarritoRepository: EstadoCarritoRepository = Mockito.mock(EstadoCarritoRepository::class.java)
    private val carritoProductoRepository: CarritoProductoRepository = Mockito.mock(CarritoProductoRepository::class.java)

    private val carritosServiceImpl = CarritosServiceImpl(carritosRepository, estadoCarritoRepository, carritoProductoRepository)

    @Test
    fun `agregar Carrito`() {

        val estadoCarritoEntity = EstadoCarritoEntity().apply {
            this.uuidEstadoCarrito = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
            this.codigo = "PENDIENTE"
            this.descripcion = "pendiente"
        }

        val carritoDTO = CarritoDTO(uuidCarrito = UUID.randomUUID(),
            idCarrito = 1,
            estadoCarritoDTO = EstadoCarritoMapper.toDTO(estadoCarritoEntity),
            estadoCarrito = estadoCarritoEntity.uuidEstadoCarrito
        )

        Mockito.`when`(estadoCarritoRepository.findFirstByCodigo(Mockito.anyString())).thenReturn(
            estadoCarritoEntity
        )
        Mockito.`when`(carritosRepository.save(Mockito.any(CarritoEntity::class.java))).thenReturn(
            CarritoEntity().apply {
                this.uuidCarrito = carritoDTO.uuidCarrito
                this.idCarrito = carritoDTO.idCarrito
                this.estadoCarritoByEstadoCarrito = estadoCarritoEntity
            }
        )
        val carrito = carritosServiceImpl.agregarCarrito()
        Assertions.assertEquals(
           carritoDTO, carrito
        )
    }

    @Test(expected = CrudException::class)
    fun `intentar hacer checkout sin enviar la uuid o uuid no existe`() {
        carritosServiceImpl.checkoutCarrito(CarritoDTO())
    }

    @Test
    fun `checkout Carrito`() {
        val estadoCarritoPendienteEntity = EstadoCarritoEntity().apply {
            this.uuidEstadoCarrito = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
            this.codigo = "PENDIENTE"
            this.descripcion = "pendiente"
        }

        val estadoCarritoCompletadoEntity = EstadoCarritoEntity().apply {
            this.uuidEstadoCarrito = UUID.fromString("111e4567-e89b-12d3-a456-426614174000")
            this.codigo = "COMPLETADO"
            this.descripcion = "completado"
        }

        val carritoDTO = CarritoDTO(uuidCarrito = UUID.fromString("888e4567-e89b-12d3-a456-426614174000"),
            idCarrito = 1,
            estadoCarritoDTO = EstadoCarritoMapper.toDTO(estadoCarritoPendienteEntity),
            estadoCarrito = estadoCarritoPendienteEntity.uuidEstadoCarrito
        )

        val tipoProductoSimple = TipoProductosEntity().apply {
            this.uuidTipoProducto = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
            this.codigo = "SIMPLE"
            this.descripcion = "Simple"
        }

        val tipoProductoDescuento = TipoProductosEntity().apply {
            this.uuidTipoProducto = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
            this.codigo = "DESCUENTO"
            this.descripcion = "Descuento"
        }

        val productos: MutableList<ProductosEntity> = mutableListOf(
            ProductosEntity().apply {
                this.tipoProductosByTipoProducto = tipoProductoSimple
                this.precio = BigDecimal(2000)
            },
            ProductosEntity().apply {
                this.tipoProductosByTipoProducto = tipoProductoDescuento
                this.precio = BigDecimal(2000)
            }
        )
        val productosCarrito: List<ProductosEntity> = productos

        Mockito.`when`(carritosRepository.findById(Mockito.any(UUID::class.java))).thenReturn(
            Optional.of(CarritoEntity().apply {
                this.uuidCarrito = carritoDTO.uuidCarrito
                this.idCarrito = carritoDTO.idCarrito
                this.estadoCarritoByEstadoCarrito = estadoCarritoPendienteEntity
            })
        )
        Mockito.`when`(estadoCarritoRepository.findFirstByCodigo(Mockito.anyString())).thenReturn(
            estadoCarritoCompletadoEntity
        )
        Mockito.`when`(carritoProductoRepository.getTotalCarrito(Mockito.any(UUID::class.java))).thenReturn(
            productosCarrito
        )
        Mockito.`when`(carritosRepository.save(Mockito.any(CarritoEntity::class.java))).thenReturn(
            CarritoEntity().apply {
                this.uuidCarrito = carritoDTO.uuidCarrito
                this.idCarrito = carritoDTO.idCarrito
                this.estadoCarritoByEstadoCarrito = estadoCarritoCompletadoEntity
            }
        )
        val carrito = carritosServiceImpl.checkoutCarrito(carritoDTO)
        Assertions.assertEquals(
            CarritoDTO(
                uuidCarrito = UUID.fromString("888e4567-e89b-12d3-a456-426614174000"),
                idCarrito = 1,
                estadoCarritoDTO = EstadoCarritoMapper.toDTO(estadoCarritoCompletadoEntity),
                estadoCarrito = estadoCarritoCompletadoEntity.uuidEstadoCarrito,
                total = BigDecimal(3000)
            ), carrito
        )
    }
}