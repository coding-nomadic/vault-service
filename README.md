## Vault-Service

This is Rest endpoints built using SpringBoot which allows to create path in hashCorp Vault. This also allows to CRUD of key values for the same path.

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

You can open any browser, navigate below URL and enter the token id as "dev-token" to login -

```
http://localhost:8200/
```
## POST endpoint - 

```
curl --location --request POST 'http://localhost:8080/vaults' \
--header 'Content-Type: application/json' \
--data-raw '{
    "vaultSecretPath": "postGreSQLPath",
    "key": "test",
    "value": "test"
}'
```
Once path secret path is created, you can navigate and see the key-values 

![Capture](https://user-images.githubusercontent.com/8009104/214837169-6ed7948e-ea87-4f71-959a-cefdf2fd52ae.JPG)

## GET by path endpoint - 
```
curl --location --request GET 'http://localhost:8080/vaults/postGreSQLPath' \
--header 'Content-Type: application/json' \
--data-raw ''
```
## Update by path endpoint - 
```
curl --location --request PUT 'http://localhost:8080/vaults/postGreSQLPath' \
--header 'Content-Type: application/json' \
--data-raw '{
    "key": "test1",
    "value": "test1"
}'
```
## Delete by path endpoint - 
```
curl --location --request DELETE 'http://localhost:8080/vaults/postGreSQLPath' \
--header 'Content-Type: application/json' \
--data-raw ''
```


