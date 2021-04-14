package com.tul.ecommerce.data.service.producto

import com.tul.ecommerce.data.dto.ProductoDTO
import com.tul.ecommerce.data.entity.ProductosEntity
import com.tul.ecommerce.data.entity.TipoProductosEntity
import com.tul.ecommerce.data.exceptions.CreateCarritoException
import com.tul.ecommerce.data.mapper.ProductoMapper
import com.tul.ecommerce.data.repositories.CarritoProductoRepository
import com.tul.ecommerce.data.repositories.ProductosRepository
import com.tul.ecommerce.data.repositories.TipoProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.util.*
import java.util.stream.Collectors

@Service
class ProductoServiceImpl @Autowired constructor(private val productosRepository: ProductosRepository,
private val tipoProductoRepository: TipoProductoRepository,
private val carritoProductoRepository: CarritoProductoRepository): ProductoService {

    override fun agregarProducto(productoDTO: ProductoDTO): ProductoDTO? {
        if(productoDTO.nombre == null || productoDTO.sku == null || productoDTO.descripcion == null) {
            throw CreateCarritoException("El producto debe tener un SKU, un nombre y una descripcion")
        }
        if(this.productosRepository.findFirstBySku(productoDTO.sku) != null){
            throw CreateCarritoException("Ya se encuentra registrado un producto con este SKU")
        }
        val codigo = this.tipoProductoRepository.findFirstByCodigo(productoDTO.tipoProductoDTO?.codigo)
            ?: throw CreateCarritoException("No se encontró codigo del tipo de producto")
        val productoEntity = ProductoMapper.toEntity(productoDTO, codigo)
        return ProductoMapper.toDTO(this.productosRepository.save(productoEntity))
    }

    override fun consultarProducto(productoDTO: ProductoDTO): ProductoDTO? {
        if(productoDTO.uuid_producto == null && productoDTO.sku == null){
            throw CreateCarritoException("No se envio id del producto a buscar")
        }
        val result = this.productosRepository.findFirstBySku(productoDTO.sku)
            ?: (this.productosRepository.findFirstByUuidProducto(productoDTO.uuid_producto)
                ?: throw CreateCarritoException("No se encontro el articulo"))
        return ProductoMapper.toDTO(result)
    }

    override fun consultarTodos(): List<ProductoDTO>? {
        return this.productosRepository.findAll().stream()
            .map { productosEntity -> ProductoMapper.toDTO(productosEntity) }.collect(Collectors.toList())
    }

    override fun modificarProducto(productoDTO: ProductoDTO) : ProductoDTO {
        if(productoDTO.uuid_producto == null){
            throw CreateCarritoException("No se envio id del producto a editar")
        }
        var productoEntity = this.productosRepository.findFirstByUuidProducto(productoDTO.uuid_producto) ?:
        throw CreateCarritoException("uuid del producto no encontrada")
        if(productoDTO.tipoProductoDTO?.codigo != null){
            val codigo = this.tipoProductoRepository.findFirstByCodigo(productoDTO.tipoProductoDTO?.codigo)
                ?: throw CreateCarritoException("No se encontró codigo del tipo de producto")
            productoEntity = updateProducto(productoEntity, ProductoMapper.toEntity(productoDTO), codigo)
        }else{
            productoEntity = updateProducto(productoEntity, ProductoMapper.toEntity(productoDTO))
        }
        return ProductoMapper.toDTO(this.productosRepository.save(productoEntity))
    }

    override fun eliminarProducto(productoDTO: ProductoDTO) {
        if(productoDTO.uuid_producto == null){
            throw CreateCarritoException("No se envio id del producto a eliminar")
        }
        var productoEntity = this.productosRepository.findFirstByUuidProducto(productoDTO.uuid_producto) ?:
        throw CreateCarritoException("uuid del producto no encontrada")
        if(this.carritoProductoRepository.getCountProductoInCarrito(productoDTO.uuid_producto) > 0){
            throw CreateCarritoException("el producto se encuentra agregado a un carrito y no se puede eliminar")
        }
        this.productosRepository.delete(productoEntity)
    }


    fun updateProducto(productoToUpdate: ProductosEntity?, update: ProductosEntity, tipoProducto: TipoProductosEntity? = null): ProductosEntity {
        return ProductosEntity().apply {
            this.activo = update.activo ?: productoToUpdate?.activo
            this.precio = update.precio ?: productoToUpdate?.precio
            this.descripcion = update.descripcion ?: productoToUpdate?.descripcion
            this.nombre = update.nombre ?: productoToUpdate?.nombre
            this.tipoProductosByTipoProducto = tipoProducto ?: productoToUpdate?.tipoProductosByTipoProducto
            this.creacionProducto = productoToUpdate?.creacionProducto
            this.sku = productoToUpdate?.sku
            this.uuidProducto = productoToUpdate?.uuidProducto
        }
    }
}