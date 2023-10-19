package com.neofacto.encryption.symmetric.exception;

public class DecryptionException extends RuntimeException {

    public DecryptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecryptionException(String message) {
        super(message);
    }
}