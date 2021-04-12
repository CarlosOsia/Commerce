package com.tul.ecommerce.data.enum

import java.util.stream.Stream

enum class TipoProductoEnum(val codigo: String)
{
    DESCUENTO("DESCUENTO"), SIMPLE("SIMPLE");

    companion object {
        fun of(codigo: String?): TipoProductoEnum {
            return Stream.of(*values())
                .filter { p: TipoProductoEnum -> p.codigo == codigo }
                .findFirst()
                .orElseThrow { IllegalArgumentException() }
        }
    }
}