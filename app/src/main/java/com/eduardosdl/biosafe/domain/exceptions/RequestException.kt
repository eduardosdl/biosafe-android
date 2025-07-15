package com.eduardosdl.biosafe.domain.exceptions

class RequestException(val type: RequestExceptionType, message: String? = null) : Exception(message)