package com.tul.ecommerce.data.enum

import java.util.stream.Stream

enum class EstadoCarritoEnum(val codigo: String)
{
    COMPLETADO("COMPLETADO"), PENDIENTE("PENDIENTE");

    companion object {
        fun of(codigo: String?): TipoProductoEnum {
            return Stream.of(*TipoProductoEnum.values())
                .filter { p: TipoProductoEnum -> p.codigo == codigo }
                .findFirst()
                .orElseThrow { IllegalArgumentException() }
        }
    }
}