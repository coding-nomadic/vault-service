package com.example.vault.utils;

import com.example.vault.models.VaultConfiguration;
import com.example.vault.models.VaultUpdate;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.vault.support.Versioned;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * util class
 *
 */
public class VaultUtils {
    private VaultUtils() {
    }

    public static String toString(Object objectToConvert) throws IOException {
        return new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(objectToConvert);
    }

    public static <T> T convertWithClass(String jsonString, Class<T> type) throws IOException {
        return new ObjectMapper().readValue(jsonString, type);
    }

    @SuppressWarnings("unchecked")
    public static VaultConfiguration prepareResponse(Versioned<Map<String, Object>> response, String path)
                                    throws JsonMappingException, JsonProcessingException, IOException {
        VaultConfiguration vaultConfiguration = new VaultConfiguration();
        Map<String, String> maps = new ObjectMapper().readValue(VaultUtils.toString(response.getData()), HashMap.class);
        maps.forEach((k, v) -> {
            vaultConfiguration.setKey(k);
            vaultConfiguration.setValue(v);
            vaultConfiguration.setVaultSecretPath(path);
        });
        return vaultConfiguration;
    }

    public static VaultConfiguration updateResponse(VaultUpdate vaultUpdate, String path) {
        VaultConfiguration vaultConfiguration = new VaultConfiguration();
        vaultConfiguration.setKey(vaultUpdate.getKey());
        vaultConfiguration.setValue(vaultUpdate.getValue());
        vaultConfiguration.setVaultSecretPath(path);
        return vaultConfiguration;
    }
}
