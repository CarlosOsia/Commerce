package com.tul.ecommerce.controller.handler

import com.tul.ecommerce.data.exceptions.CreateCarritoException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ExceptionHandlingController: ResponseEntityExceptionHandler()   {

    @ExceptionHandler(CreateCarritoException::class)
    fun handleAll(ex: Exception, request: WebRequest?): ResponseEntity<Any?>? {
        val apiError = ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR, ex.message, "error occurred"
        )
        return ResponseEntity(
            apiError, HttpHeaders(), apiError.status
        )
    }
}