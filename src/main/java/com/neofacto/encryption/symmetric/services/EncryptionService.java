package com.neofacto.encryption.symmetric.services;

import com.neofacto.encryption.symmetric.dao.DecryptedValueDAO;
import com.neofacto.encryption.symmetric.dao.EncryptedValueDAO;

public interface EncryptionService {
    EncryptedValueDAO getEncryptedValueWithBasicEncryption(DecryptedValueDAO value);

    DecryptedValueDAO getDecryptedValueWithBasicEncryption(EncryptedValueDAO value);

    EncryptedValueDAO getEncryptedValueWithStrongEncryption(DecryptedValueDAO value);

    DecryptedValueDAO getDecryptedValueWithStrongEncryption(EncryptedValueDAO value);

    EncryptedValueDAO getEncryptedValueWithAESEncryption(DecryptedValueDAO value);

    DecryptedValueDAO getDecryptedValueWithAESEncryption(EncryptedValueDAO value);
}
