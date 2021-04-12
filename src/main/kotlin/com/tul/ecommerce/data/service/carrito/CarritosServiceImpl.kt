package com.tul.ecommerce.data.service.carrito

import com.tul.ecommerce.data.dto.CarritoDTO
import com.tul.ecommerce.data.enum.EstadoCarritoEnum
import com.tul.ecommerce.data.exceptions.CreateCarritoException
import com.tul.ecommerce.data.mapper.CarritoMapper
import com.tul.ecommerce.data.repositories.CarritoProductoRepository
import com.tul.ecommerce.data.repositories.CarritosRepository
import com.tul.ecommerce.data.repositories.EstadoCarritoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

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
            throw CreateCarritoException("No se encontró el uuid del carrito a modificar")
        }
        val codigo = this.estadoCarritoRepository.findFirstByCodigo(EstadoCarritoEnum.COMPLETADO.codigo)
        //val total = this.carritoProductoRepository.getTotalCarrito(uuid).get(0).
        val carritoEntity = CarritoMapper.toEntity(carritoDTO, codigo)
        return CarritoMapper.toDTO(this.carritosRepository.save(carritoEntity))
    }

}