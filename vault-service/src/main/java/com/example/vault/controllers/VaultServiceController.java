package com.example.vault.controllers;

import com.example.vault.constants.VaultConstants;
import com.example.vault.models.VaultConfiguration;
import com.example.vault.models.VaultUpdate;
import com.example.vault.services.VaultService;
import com.example.vault.utils.VaultUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(path = VaultConstants.BASE_PATH)
@RestController
@Slf4j
/**
 * 
 * controller class for vault APIs
 *
 */
public class VaultServiceController {

    @Autowired
    private VaultService vaultService;

    /**
     * 
     * @param vaultConfiguration
     * @return
     * @throws IOException
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VaultConfiguration> createVaultDetails(
                                    @RequestBody @Valid VaultConfiguration vaultConfiguration) throws IOException {
        log.info("Entered Vault Details Request Body {}", VaultUtils.toString(vaultConfiguration));
        vaultService.createVaultDetails(vaultConfiguration);
        return new ResponseEntity<>(vaultConfiguration, HttpStatus.CREATED);
    }

    /**
     * 
     * @param pathValue
     * @return
     * @throws IOException
     */
    @GetMapping(path = VaultConstants.PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VaultConfiguration> fetchVaultDetails(@PathVariable String pathValue) throws IOException {
        log.info("Fetching Vault Details for Path : {}", pathValue);
        final VaultConfiguration response = VaultUtils.prepareResponse(vaultService.fetchVaultDetails(pathValue),
                                        pathValue);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 
     * @param pathValue
     * @return
     * @throws IOException
     */
    @PutMapping(path = VaultConstants.PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VaultConfiguration> UpdateVaultDetails(@RequestBody VaultUpdate vaultUpdate,
                                    @PathVariable String pathValue) throws IOException {
        log.info("Updating Vault Details for Path : {}", pathValue);
        final VaultConfiguration vaultConfiguration = vaultService.updateVaultDetails(vaultUpdate, pathValue);
        return new ResponseEntity<>(vaultConfiguration, HttpStatus.OK);
    }

    /**
     * 
     * @param pathValue
     * @return
     */
    @DeleteMapping(path = VaultConstants.PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VaultConfiguration> deleteVaultDetails(@PathVariable String pathValue) {
        log.info("Deleting Vault Details for Path : {}", pathValue);
        vaultService.deleteVaultDetails(pathValue);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
