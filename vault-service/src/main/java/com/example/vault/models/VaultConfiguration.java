package com.example.vault.models;

import lombok.Data;

@Data
/**
 * 
 * @author Model for vault
 *
 */
public class VaultConfiguration {

    private String vaultSecretPath;
    private String key;
    private String value;
    
}
