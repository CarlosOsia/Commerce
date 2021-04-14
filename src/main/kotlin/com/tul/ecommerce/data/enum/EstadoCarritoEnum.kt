package com.tul.ecommerce.data.enum

import java.util.stream.Stream

enum class EstadoCarritoEnum(val codigo: String)
{
    COMPLETADO("COMPLETADO"), PENDIENTE("PENDIENTE");

    companion object {
        fun of(codigo: String?): EstadoCarritoEnum {
            return Stream.of(*values())
                .filter { p: EstadoCarritoEnum -> p.codigo == codigo }
                .findFirst()
                .orElseThrow { IllegalArgumentException() }
        }
    }
}