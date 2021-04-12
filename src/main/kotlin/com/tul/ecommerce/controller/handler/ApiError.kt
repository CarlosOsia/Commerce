package com.tul.ecommerce.controller.handler

import org.springframework.http.HttpStatus

class ApiError(val status: HttpStatus, val message: String?, val error: String)