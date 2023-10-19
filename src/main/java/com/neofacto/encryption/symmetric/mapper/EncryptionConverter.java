package com.neofacto.encryption.symmetric.mapper;

import com.neofacto.encryption.symmetric.dao.DecryptedValueDAO;
import com.neofacto.encryption.symmetric.dao.EncryptedValueDAO;

public class EncryptionConverter {
    public EncryptedValueDAO decryptedToEncrypted(DecryptedValueDAO decryptedValueDAO){
        return new EncryptedValueDAO(decryptedValueDAO.getAlgorithm(), decryptedValueDAO.getPassPhrase());
    }

    public DecryptedValueDAO encryptedToDecrypted(EncryptedValueDAO encryptedValueDAO){
        return new DecryptedValueDAO(encryptedValueDAO.getAlgorithm(), encryptedValueDAO.getPassPhrase());
    }
}
