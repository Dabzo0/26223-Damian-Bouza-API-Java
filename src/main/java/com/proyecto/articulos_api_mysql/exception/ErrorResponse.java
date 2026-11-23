package com.proyecto.articulos_api_mysql.exception;

import java.time.LocalDateTime;

public record ErrorResponse (
    int status,
    String error,
    String message,
    LocalDateTime timestamp
){}
