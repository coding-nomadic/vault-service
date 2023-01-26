## Vault-Service

This is vault-service Rest endpoints build using SpringBoot which allows to create path in hashCorp Vault. This also allows to CRUD of key values for the same path.

This endpoints can be used in any Microservice Projects where we need to read the sensitive data such as database credentials which are stored in vault.

## Set up local (Dev mode) HashCorp 

Download the windows installer from this link - 

```
https://developer.hashicorp.com/vault/downloads?host=www.vaultproject.io
```

Once downloaded, go to the path extract zipped file and run the cmd with command -

```
vault server -dev -dev-root-token-id="dev-token"
```



