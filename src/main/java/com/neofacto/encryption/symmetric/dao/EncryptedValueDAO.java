package com.neofacto.encryption.symmetric.dao;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncryptedValueDAO {
    private String encryptedText;
    private String passPhrase;
    private String algorithm;

    public EncryptedValueDAO(String algorithm, String passPhrase) {
        this.algorithm = algorithm;
        this.passPhrase = passPhrase;
    }
}