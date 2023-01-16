package com.example.vault.exceptions;

/**
 * 
 * Custom Exception for Vault Service.
 *
 */
public class VaultException extends RuntimeException {
    private static final long serialVersionUID = 3953822390268748845L;

    private final String code;

    public VaultException(String message, String code) {
        super(message);
        this.code = code;
    }

    public VaultException(String message, String code, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
