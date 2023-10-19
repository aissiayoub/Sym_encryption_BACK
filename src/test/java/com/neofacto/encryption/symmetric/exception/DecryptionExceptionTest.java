package com.neofacto.encryption.symmetric.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecryptionExceptionTest {

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Decryption failed";
        Throwable cause = new RuntimeException("Underlying exception");
        DecryptionException exception = new DecryptionException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Decryption failed";
        DecryptionException exception = new DecryptionException(message);

        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }
}
