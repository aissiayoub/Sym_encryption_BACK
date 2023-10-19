package com.neofacto.encryption.symmetric.controller;

import com.neofacto.encryption.symmetric.dao.DecryptedValueDAO;
import com.neofacto.encryption.symmetric.dao.EncryptedValueDAO;
import com.neofacto.encryption.symmetric.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EncryptionController {
    @Autowired
    EncryptionService service;

    @PostMapping("/encrypt")
    public ResponseEntity<EncryptedValueDAO> getEncryptedValue(@RequestBody DecryptedValueDAO value){
        switch (value.getAlgorithm()){
            case "AES": return ResponseEntity.ok(service.getEncryptedValueWithAESEncryption(value));
            case "Strong": return ResponseEntity.ok(service.getEncryptedValueWithStrongEncryption(value));
            default: return ResponseEntity.ok(service.getEncryptedValueWithBasicEncryption(value));
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<DecryptedValueDAO> getDecryptedValue(@RequestBody EncryptedValueDAO value){
        switch (value.getAlgorithm()){
            case "AES": return ResponseEntity.ok(service.getDecryptedValueWithAESEncryption(value));
            case "Strong": return ResponseEntity.ok(service.getDecryptedValueWithStrongEncryption(value));
            default: return ResponseEntity.ok(service.getDecryptedValueWithBasicEncryption(value));
        }
    }
}
