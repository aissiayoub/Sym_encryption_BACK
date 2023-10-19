package com.neofacto.encryption.symmetric.exception;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Before
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleDecryptionException() {
        String errorMessage = "Decryption failed";
        DecryptionException decryptionException = new DecryptionException(errorMessage);

        ResponseEntity<String> responseEntity = globalExceptionHandler.handleDecryptionException(decryptionException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    public void testHandleOtherException() {
        String errorMessage = "Error";
        Exception exception = new Exception(errorMessage);

        ResponseEntity<String> responseEntity = globalExceptionHandler.handleDecryptionException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }
}

