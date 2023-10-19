package com.neofacto.encryption.symmetric.dao;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecryptedValueDAO {
    private String decryptedText;
    private String passPhrase;
    private String algorithm;

    public DecryptedValueDAO(String algorithm, String passPhrase) {
        this.algorithm = algorithm;
        this.passPhrase = passPhrase;
    }
}
