package com.example.vault.services;

import com.example.vault.config.VaultConfigsHelper;
import com.example.vault.exceptions.VaultException;
import com.example.vault.models.VaultConfiguration;
import com.example.vault.models.VaultUpdate;
import com.example.vault.utils.VaultUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.support.Versioned;
import org.springframework.vault.support.Versioned.Metadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class VaultServiceImpl implements VaultService {

    @Value("${vault.secret}")
    private String secret;
    @Autowired
    private VaultConfigsHelper vaultConfigsHelper;

    @Override
    public Metadata createVaultDetails(VaultConfiguration vaultConfiguration) {
        final Map<String, String> vaultData = new HashMap<>();
        vaultData.put(vaultConfiguration.getKey(), vaultConfiguration.getValue());
        return vaultConfigsHelper.getVaultTemplate().opsForVersionedKeyValue(secret)
                                        .put(vaultConfiguration.getVaultSecretPath(), vaultData);

    }

    @Override
    public Versioned<Map<String, Object>> fetchVaultDetails(String vaultPath) throws IOException {

        final Versioned<Map<String, Object>> readResponse = vaultConfigsHelper.getVaultTemplate()
                                        .opsForVersionedKeyValue(secret).get(vaultPath);
        return Optional.ofNullable(readResponse).orElseThrow(
                                        () -> new VaultException("Vault path doesnt have any details", "102"));
    }

    @Override
    public void deleteVaultDetails(String vaultPath) {
        vaultConfigsHelper.getVaultTemplate().opsForVersionedKeyValue(secret).delete(vaultPath);

    }

    @Override
    public VaultConfiguration updateVaultDetails(VaultUpdate vaultUpdate, String vaultPath) throws IOException {
        fetchVaultDetails(vaultPath);
        final Map<String, String> vaultData = new HashMap<>();
        vaultData.put(vaultUpdate.getKey(), vaultUpdate.getValue());
        vaultConfigsHelper.getVaultTemplate().opsForVersionedKeyValue(secret).put(vaultPath, vaultData);
        return VaultUtils.updateResponse(vaultUpdate, vaultPath);
    }
}
