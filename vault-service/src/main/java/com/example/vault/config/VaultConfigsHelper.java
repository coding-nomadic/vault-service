package com.example.vault.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

@Configuration
@Component
/**
 * 
 * @author Reads from configuration and creates bean.
 *
 */
public class VaultConfigsHelper {

    @Value("${vault.host}")
    private String vaultHost;
    @Value("${vault.port}")
    private String vaultPort;
    @Value("${vault.scheme}")
    private String vaultScheme;
    @Value("${vault.devToken}")
    private String vaultDevToken;

    @Bean
    /**
     * 
     * @return
     */
    public VaultTemplate getVaultTemplate() {
        VaultEndpoint vaultEndpoint = new VaultEndpoint();
        vaultEndpoint.setHost(vaultHost);
        vaultEndpoint.setPort(Integer.parseInt(vaultPort));
        vaultEndpoint.setScheme(vaultScheme);
        return new VaultTemplate(vaultEndpoint, new TokenAuthentication(vaultDevToken));

    }

}
