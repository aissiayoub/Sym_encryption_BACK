package com.neofacto.encryption.symmetric.controller;

import com.neofacto.encryption.symmetric.dao.DecryptedValueDAO;
import com.neofacto.encryption.symmetric.dao.EncryptedValueDAO;
import com.neofacto.encryption.symmetric.services.EncryptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EncryptionControllerTest {

    private EncryptionController encryptionController;

    @Mock
    private EncryptionService encryptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        encryptionController = new EncryptionController();
        encryptionController.service = encryptionService;
    }

    @Test
    void getEncryptedValueAES() {
        DecryptedValueDAO decryptedValueDAO = new DecryptedValueDAO("testText", "testPassphrase", "AES");
        EncryptedValueDAO expectedEncryptedValue = new EncryptedValueDAO("encryptedText", "testPassphrase", "AES");

        when(encryptionService.getEncryptedValueWithAESEncryption(decryptedValueDAO)).thenReturn(expectedEncryptedValue);

        ResponseEntity<EncryptedValueDAO> result = encryptionController.getEncryptedValue(decryptedValueDAO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedEncryptedValue, result.getBody());
    }

    @Test
    void getEncryptedValueBasic() {
        DecryptedValueDAO decryptedValueDAO = new DecryptedValueDAO("testText", "testPassphrase", "Basic");
        EncryptedValueDAO expectedEncryptedValue = new EncryptedValueDAO("encryptedText", "testPassphrase", "Basic");

        when(encryptionService.getEncryptedValueWithBasicEncryption(decryptedValueDAO)).thenReturn(expectedEncryptedValue);

        ResponseEntity<EncryptedValueDAO> result = encryptionController.getEncryptedValue(decryptedValueDAO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedEncryptedValue, result.getBody());
    }

    @Test
    void getEncryptedValueStrong() {
        DecryptedValueDAO decryptedValueDAO = new DecryptedValueDAO("testText", "testPassphrase", "Strong");
        EncryptedValueDAO expectedEncryptedValue = new EncryptedValueDAO("encryptedText", "testPassphrase", "Strong");

        when(encryptionService.getEncryptedValueWithStrongEncryption(decryptedValueDAO)).thenReturn(expectedEncryptedValue);

        ResponseEntity<EncryptedValueDAO> result = encryptionController.getEncryptedValue(decryptedValueDAO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedEncryptedValue, result.getBody());
    }

    @Test
    void getDecryptedValueAES() {
        EncryptedValueDAO encryptedValueDAO = new EncryptedValueDAO("encryptedText", "testPassphrase", "AES");
        DecryptedValueDAO expectedDecryptedValue = new DecryptedValueDAO("testText", "testPassphrase", "AES");

        when(encryptionService.getDecryptedValueWithAESEncryption(encryptedValueDAO)).thenReturn(expectedDecryptedValue);

        ResponseEntity<DecryptedValueDAO> result = encryptionController.getDecryptedValue(encryptedValueDAO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedDecryptedValue, result.getBody());
    }

    @Test
    void getDecryptedValueBasic() {
        EncryptedValueDAO encryptedValueDAO = new EncryptedValueDAO("encryptedText", "testPassphrase", "Basic");
        DecryptedValueDAO expectedDecryptedValue = new DecryptedValueDAO("testText", "testPassphrase", "Basic");

        when(encryptionService.getDecryptedValueWithBasicEncryption(encryptedValueDAO)).thenReturn(expectedDecryptedValue);

        ResponseEntity<DecryptedValueDAO> result = encryptionController.getDecryptedValue(encryptedValueDAO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedDecryptedValue, result.getBody());
    }

    @Test
    void getDecryptedValueStrong() {
        EncryptedValueDAO encryptedValueDAO = new EncryptedValueDAO("encryptedText", "testPassphrase", "Strong");
        DecryptedValueDAO expectedDecryptedValue = new DecryptedValueDAO("testText", "testPassphrase", "Strong");

        when(encryptionService.getDecryptedValueWithStrongEncryption(encryptedValueDAO)).thenReturn(expectedDecryptedValue);

        ResponseEntity<DecryptedValueDAO> result = encryptionController.getDecryptedValue(encryptedValueDAO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedDecryptedValue, result.getBody());
    }
}
