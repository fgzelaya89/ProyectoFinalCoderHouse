package com.coderhouse.exceptions;

public class ProductoSinStockException extends RuntimeException {
    public ProductoSinStockException(String mensaje) {
        super(mensaje);
    }
}
