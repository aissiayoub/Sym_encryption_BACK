package com.neofacto.encryption.symmetric.services.impl;

import com.neofacto.encryption.symmetric.dao.DecryptedValueDAO;
import com.neofacto.encryption.symmetric.dao.EncryptedValueDAO;
import com.neofacto.encryption.symmetric.exception.DecryptionException;
import com.neofacto.encryption.symmetric.mapper.EncryptionConverter;
import com.neofacto.encryption.symmetric.services.EncryptionService;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    EncryptionConverter encryptionConverter = new EncryptionConverter();

    private static final Logger log = LoggerFactory.getLogger(Object.class);

    @Override
    public EncryptedValueDAO getEncryptedValueWithBasicEncryption(DecryptedValueDAO value) {
        log.info("Basic encryption");
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(value.getPassPhrase());
        String myEncryptedText = textEncryptor.encrypt(value.getDecryptedText());
        EncryptedValueDAO encryptedValueDAO = encryptionConverter.decryptedToEncrypted(value);
        encryptedValueDAO.setEncryptedText(myEncryptedText);
        return encryptedValueDAO;
    }

    @Override
    public DecryptedValueDAO getDecryptedValueWithBasicEncryption(EncryptedValueDAO value) {
        log.info("Basic decryption");
        BasicTextEncryptor textDecryptor = new BasicTextEncryptor();
        textDecryptor.setPassword(value.getPassPhrase());
        try{
            String myEncryptedText = textDecryptor.decrypt(value.getEncryptedText());
            DecryptedValueDAO decryptedValueDAO = encryptionConverter.encryptedToDecrypted(value);
            decryptedValueDAO.setDecryptedText(myEncryptedText);
            return decryptedValueDAO;
        } catch (Exception e) {
            throw new DecryptionException("Decryption failed. The provided encrypted text might be encrypted with a different algorithm or the key might be incorrect.", e);
        }
    }

    @Override
    public EncryptedValueDAO getEncryptedValueWithStrongEncryption(DecryptedValueDAO value) {
        log.info("Strong encryption");
        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(value.getPassPhrase());
        String myEncryptedText = textEncryptor.encrypt(value.getDecryptedText());
        EncryptedValueDAO encryptedValueDAO = encryptionConverter.decryptedToEncrypted(value);
        encryptedValueDAO.setEncryptedText(myEncryptedText);
        return encryptedValueDAO;
    }

    @Override
    public DecryptedValueDAO getDecryptedValueWithStrongEncryption(EncryptedValueDAO value) {
        log.info("Strong decryption");
        StrongTextEncryptor textDecryptor = new StrongTextEncryptor();
        textDecryptor.setPassword(value.getPassPhrase());
        try{
            String myEncryptedText = textDecryptor.decrypt(value.getEncryptedText());
            DecryptedValueDAO decryptedValueDAO = encryptionConverter.encryptedToDecrypted(value);
            decryptedValueDAO.setDecryptedText(myEncryptedText);
            return decryptedValueDAO;
        } catch (EncryptionOperationNotPossibleException e) {
            throw new DecryptionException("Decryption failed. The provided encrypted text might be encrypted with a different algorithm or the key might be incorrect.", e);
        }
    }

    @Override
    public EncryptedValueDAO getEncryptedValueWithAESEncryption(DecryptedValueDAO value) {
        log.info("AES encryption");
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(value.getPassPhrase());
        String myEncryptedText = textEncryptor.encrypt(value.getDecryptedText());
        EncryptedValueDAO encryptedValueDAO = encryptionConverter.decryptedToEncrypted(value);
        encryptedValueDAO.setEncryptedText(myEncryptedText);
        return encryptedValueDAO;
    }

    @Override
    public DecryptedValueDAO getDecryptedValueWithAESEncryption(EncryptedValueDAO value) {
        log.info("AES decryption");
        AES256TextEncryptor textDecryptor = new AES256TextEncryptor();
        textDecryptor.setPassword(value.getPassPhrase());
        try{
            String myEncryptedText = textDecryptor.decrypt(value.getEncryptedText());
            DecryptedValueDAO decryptedValueDAO = encryptionConverter.encryptedToDecrypted(value);
            decryptedValueDAO.setDecryptedText(myEncryptedText);
            return decryptedValueDAO;
        } catch (EncryptionOperationNotPossibleException e) {
            throw new DecryptionException("Decryption failed. The provided encrypted text might be encrypted with a different algorithm or the key might be incorrect.", e);
        }
    }
}
