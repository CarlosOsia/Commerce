package com.tul.ecommerce.data.exceptions

class CrudException (private val msg : String) : RuntimeException(msg)