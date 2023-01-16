package com.example.vault.services;

import com.example.vault.models.VaultConfiguration;
import com.example.vault.models.VaultUpdate;

import org.springframework.vault.support.Versioned;
import org.springframework.vault.support.Versioned.Metadata;

import java.io.IOException;
import java.util.Map;

public interface VaultService {

    /**
     * 
     * @param vaultConfiguration
     * @return
     */
    Metadata createVaultDetails(VaultConfiguration vaultConfiguration);

    /**
     * 
     * @param vaultConfiguration
     * @return
     */
    Versioned<Map<String, Object>> fetchVaultDetails(String vaultPath) throws IOException;

    /**
     * 
     * @param vaultPath
     * @return
     * @throws IOException
     */
    VaultConfiguration updateVaultDetails(VaultUpdate vaultUpdate, String vaultPath) throws IOException;

    /**
     * 
     * @param vaultPath
     * @return
     */
    void deleteVaultDetails(String vaultPath);

}
