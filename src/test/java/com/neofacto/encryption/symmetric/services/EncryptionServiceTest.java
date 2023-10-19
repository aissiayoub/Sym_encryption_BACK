package com.neofacto.encryption.symmetric.services;

import com.neofacto.encryption.symmetric.dao.DecryptedValueDAO;
import com.neofacto.encryption.symmetric.dao.EncryptedValueDAO;
import com.neofacto.encryption.symmetric.exception.DecryptionException;
import com.neofacto.encryption.symmetric.mapper.EncryptionConverter;
import com.neofacto.encryption.symmetric.services.impl.EncryptionServiceImpl;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EncryptionServiceTest {

    private EncryptionServiceImpl encryptionService;

    @Mock
    private EncryptionConverter encryptionConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        encryptionService = new EncryptionServiceImpl();
    }

    @Test
    void testGetEncryptedValueWithBasicEncryption() {
        DecryptedValueDAO decryptedValueDAO = new DecryptedValueDAO("Basic", "testPassphrase");
        EncryptedValueDAO expectedEncryptedValue = new EncryptedValueDAO("Basic", "testPassphrase");

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(decryptedValueDAO.getPassPhrase());
        when(encryptionConverter.decryptedToEncrypted(decryptedValueDAO)).thenReturn(expectedEncryptedValue);

        EncryptedValueDAO result = encryptionService.getEncryptedValueWithBasicEncryption(decryptedValueDAO);

        assertEquals(expectedEncryptedValue.getEncryptedText(), result.getEncryptedText());
    }

    @Test
    void testGetDecryptedValueWithBasicEncryption() {
        EncryptionServiceImpl service = new EncryptionServiceImpl();

        String passPhrase = "Test1234";
        String originalText = "This is a test text";

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(passPhrase);
        String encryptedText = textEncryptor.encrypt(originalText);

        EncryptedValueDAO encryptedValue = new EncryptedValueDAO(encryptedText, passPhrase, "Basic");
        DecryptedValueDAO decryptedValue = service.getDecryptedValueWithBasicEncryption(encryptedValue);
        assertEquals(originalText, decryptedValue.getDecryptedText(), "Decrypted text should match the original text");

        // Test with incorrect passPhrase
        EncryptedValueDAO encryptedValueWithWrongPassPhrase = new EncryptedValueDAO(encryptedText, "WrongPassPhrase", "Basic");
        assertThrows(DecryptionException.class, () -> service.getDecryptedValueWithBasicEncryption(encryptedValueWithWrongPassPhrase), "Decryption should fail with the wrong passPhrase");
    }

    @Test
    void testGetEncryptedValueWithStrongEncryption() {
        DecryptedValueDAO decryptedValueDAO = new DecryptedValueDAO("Strong", "testPassphrase");
        EncryptedValueDAO expectedEncryptedValue = new EncryptedValueDAO("Strong", "testPassphrase");

        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(decryptedValueDAO.getPassPhrase());
        when(encryptionConverter.decryptedToEncrypted(decryptedValueDAO)).thenReturn(expectedEncryptedValue);

        EncryptedValueDAO result = encryptionService.getEncryptedValueWithStrongEncryption(decryptedValueDAO);

        assertEquals(expectedEncryptedValue.getEncryptedText(), result.getEncryptedText());
    }

    @Test
    void testGetDecryptedValueWithStrongEncryption() {
        EncryptionServiceImpl service = new EncryptionServiceImpl();

        String passPhrase = "Test1234";
        String originalText = "This is a test text";

        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(passPhrase);
        String encryptedText = textEncryptor.encrypt(originalText);

        EncryptedValueDAO encryptedValue = new EncryptedValueDAO(encryptedText, passPhrase, "Strong");
        DecryptedValueDAO decryptedValue = service.getDecryptedValueWithStrongEncryption(encryptedValue);
        assertEquals(originalText, decryptedValue.getDecryptedText(), "Decrypted text should match the original text");

        EncryptedValueDAO encryptedValueWithWrongPassPhrase = new EncryptedValueDAO(encryptedText, "WrongPassPhrase", "Strong");
        assertThrows(DecryptionException.class, () -> service.getDecryptedValueWithStrongEncryption(encryptedValueWithWrongPassPhrase), "Decryption should fail with the wrong passPhrase");
    }



    @Test
    void testGetEncryptedValueWithAESEncryption() {
        DecryptedValueDAO decryptedValueDAO = new DecryptedValueDAO("AES", "testPassphrase");
        EncryptedValueDAO expectedEncryptedValue = new EncryptedValueDAO("AES", "testPassphrase");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(decryptedValueDAO.getPassPhrase());
        when(encryptionConverter.decryptedToEncrypted(decryptedValueDAO)).thenReturn(expectedEncryptedValue);

        EncryptedValueDAO result = encryptionService.getEncryptedValueWithAESEncryption(decryptedValueDAO);

        assertEquals(expectedEncryptedValue.getEncryptedText(), result.getEncryptedText());
    }

    @Test
    void testGetDecryptedValueWithAESEncryption() {
        EncryptionServiceImpl service = new EncryptionServiceImpl();

        String passPhrase = "Test1234";
        String originalText = "This is a test text";

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(passPhrase);
        String encryptedText = textEncryptor.encrypt(originalText);

        EncryptedValueDAO encryptedValue = new EncryptedValueDAO(encryptedText, passPhrase, "AES");
        DecryptedValueDAO decryptedValue = service.getDecryptedValueWithAESEncryption(encryptedValue);
        assertEquals(originalText, decryptedValue.getDecryptedText(), "Decrypted text should match the original text");

        EncryptedValueDAO encryptedValueWithWrongPassPhrase = new EncryptedValueDAO(encryptedText, "WrongPassPhrase", "AES");
        assertThrows(DecryptionException.class, () -> service.getDecryptedValueWithAESEncryption(encryptedValueWithWrongPassPhrase), "Decryption should fail with the wrong passPhrase");
    }
}
