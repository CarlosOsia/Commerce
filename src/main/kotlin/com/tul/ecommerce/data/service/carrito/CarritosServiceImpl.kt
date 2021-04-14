package com.tul.ecommerce.data.service.carrito

import com.tul.ecommerce.data.dto.CarritoDTO
import com.tul.ecommerce.data.dto.CarritoProductosDTO
import com.tul.ecommerce.data.enum.EstadoCarritoEnum
import com.tul.ecommerce.data.exceptions.CrudException
import com.tul.ecommerce.data.mapper.CarritoMapper
import com.tul.ecommerce.data.mapper.CarritoProductoMapper
import com.tul.ecommerce.data.mapper.ProductoMapper
import com.tul.ecommerce.data.repositories.CarritoProductoRepository
import com.tul.ecommerce.data.repositories.CarritosRepository
import com.tul.ecommerce.data.repositories.EstadoCarritoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.stream.Collectors

@Service
class CarritosServiceImpl @Autowired constructor(private val carritosRepository: CarritosRepository,
private val estadoCarritoRepository: EstadoCarritoRepository,
private val carritoProductoRepository: CarritoProductoRepository) : CarritosService {

    override fun agregarCarrito(): CarritoDTO {
        val carritoDTO = CarritoDTO().initCarritoDTO()
        val codigo = this.estadoCarritoRepository.findFirstByCodigo(carritoDTO.estadoCarritoDTO?.codigo)
        val carritoEntity = CarritoMapper.toEntity(carritoDTO, codigo)
        return CarritoMapper.toDTO(this.carritosRepository.save(carritoEntity))
    }

    override fun checkoutCarrito(carritoDTO: CarritoDTO) : CarritoDTO {
        val uuid = carritoDTO.uuidCarrito
        if(carritoDTO.uuidCarrito == null || (uuid != null && !this.carritosRepository.findById(uuid).isPresent)){
            throw CrudException("No se encontró el uuid del carrito a modificar")
        }
        val codigo = this.estadoCarritoRepository.findFirstByCodigo(EstadoCarritoEnum.COMPLETADO.codigo)
        val productos = this.carritoProductoRepository.getTotalCarrito(carritoDTO.uuidCarrito)
        var total = BigDecimal.ZERO
        productos.forEach{
            var productoDTO = ProductoMapper.toDTO(it)
            total = total.plus((productoDTO.obtenerPrecio() ?: BigDecimal.ZERO)
                .multiply(this.carritoProductoRepository.getQuantity(carritoDTO.uuidCarrito, productoDTO.uuidProducto)))
        }
        val carritoEntity = CarritoMapper.toEntity(carritoDTO, codigo)
        return CarritoMapper.toDTO(this.carritosRepository.save(carritoEntity), total)
    }

    override fun agregarProducto(carritoProductosDTO: CarritoProductosDTO): CarritoProductosDTO {
        if(carritoProductosDTO.carritoDTO.uuidCarrito == null || carritoProductosDTO.productoDTO.uuidProducto == null) {
            throw CrudException("Se debe enviar uuid del producto y uuid del carrito")
        }
        var productoEnCarrito = carritoProductoRepository.findFirstByCarritoAndProducto(carritoProductosDTO.carritoDTO.idCarrito,
            carritoProductosDTO.productoDTO.sku) ?: return CarritoProductoMapper.toDTO(this.carritoProductoRepository.save(CarritoProductoMapper.toEntity(carritoProductosDTO)))
        productoEnCarrito.cantidad = productoEnCarrito.cantidad?.plus(carritoProductosDTO.cantidad ?: BigInteger.ONE)
        return CarritoProductoMapper.toDTO(this.carritoProductoRepository.save(productoEnCarrito))
    }

    override fun listAllProducts(uuid: UUID?): List<CarritoProductosDTO> {
        return this.carritoProductoRepository.findAllByCarrito(uuid).stream()
            .map { carritoProductosEntity -> CarritoProductoMapper.toDTO(carritoProductosEntity) }.collect(Collectors.toList())
    }

    override fun modificarProducto(carritoProductosDTO: CarritoProductosDTO): CarritoProductosDTO {
        if(carritoProductosDTO.carritoDTO.idCarrito == null || carritoProductosDTO.productoDTO.sku == null || carritoProductosDTO.cantidad == null) {
            throw CrudException("Se debe enviar sku del producto, id del carrito y cantidad a modificar")
        }
        var productoEnCarrito = carritoProductoRepository.findFirstByCarritoAndProducto(carritoProductosDTO.carritoDTO.idCarrito,
            carritoProductosDTO.productoDTO.sku) ?: throw CrudException("No se encontro el producto en el carrito")
        productoEnCarrito.cantidad = carritoProductosDTO.cantidad
        return CarritoProductoMapper.toDTO(this.carritoProductoRepository.save(productoEnCarrito))
    }

    override fun eliminarProducto(carritoProductosDTO: CarritoProductosDTO?) {
        if(carritoProductosDTO?.carritoDTO?.idCarrito == null || carritoProductosDTO?.productoDTO.sku == null) {
            throw CrudException("Se debe enviar sku del producto e id del carrito")
        }
        var productoEntity = this.carritoProductoRepository.findFirstByCarritoAndProducto(carritoProductosDTO.carritoDTO.idCarrito, carritoProductosDTO.productoDTO.sku) ?:
        throw CrudException("No se encontro el producto en el carrito")
        this.carritoProductoRepository.delete(productoEntity)
    }

}