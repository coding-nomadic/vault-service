## Vault-Service

This is vault-service Rest endpoints built using SpringBoot which allows to create path in hashCorp Vault. This also allows to CRUD of key values for the same path.

This endpoints can be used in any Microservice Projects where we need to read the sensitive data such as database credentials which are stored in vault.

Run the JAR locally using this maven command to start SpringBoot project - 
```
mvn spring-boot:run
```

## Set up local (Dev mode) HashCorp 

Download the windows installer from this link - 

```
https://developer.hashicorp.com/vault/downloads?host=www.vaultproject.io
```

Once downloaded, go to the path, extract zipped file and run the cmd with command and it will start the local vault -

```
vault server -dev -dev-root-token-id="dev-token"
```
![Capture](https://user-images.githubusercontent.com/8009104/214834062-dfff2c42-f3de-42e3-9e92-2c07a29599bb.JPG)

## Open the local Vault User Interface 

You can open any browser and navigate 

```
http://localhost:8200/
```
The same host and the port number is confiugred in the 




