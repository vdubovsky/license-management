# license-management demo poc project

This is a simple spring boot project - license management demo in java applications.
This license management approach is mostly applicable to on-premise single-tenant applications.
There is no way to fully protect your application from reverse-engineering.

The backbones of the project are two opensource projects:

- License3j Free License management for Java https://github.com/verhas/License3j
- License3jRepl free cli tool for creation RSA key pairs, licenses, signing,
  etc. https://github.com/verhas/License3jRepl

License3j is integrated into project to validate license signature and retrieve license attributes:

- clientId (UUID - client identifier)
- expireAt (String in format: 2023-01-13T15:00:00Z pattern:(yyyy-MM-dd'T'HH:mm:ss'Z') - date of expiration DateTime)

License3jRepl is a separate cli tool and is used for:

- generate keypairs(RSA)
- set clientId and expireAt
- create and sign license file

# Actors

1. Public key: public key which is used for signature verification. Public key is hardcoded inside the application.
   It could be stored separately but in this case it would be easy to substitute it by user.
2. Private key: private key is unavailable for clients, stores separately and secretly.
3. License file: license.bin which contains expireAt and clientId, signed with private key.

# Getting started

There are two branches with different approaches:

- aop-implementation - methods with annotation @LicensedAccess will be proxied and license verification will be
  implemented
- throttler - at the application start-up if license is expired or wrong client id application shutdown will be
  scheduled with 60 minutes delay

## Preparation

- `git clone https://github.com/verhas/License3jRepl`

- `mvn package`

- `java -jar ./target/License3jrepl-3.1.6-SNAPSHOT-jar-with-dependencies.jar`

- `newLicense`

- `generateKeys algorithm=RSA format=BINARY private=private.key public=public.key`

- `feature clientId:UUID=6567bd4a-c87d-4278-a1e7-db3cb75ace45`

- `feature expireAt:STRING=2023-01-13T17:00:00Z`

- `sign`

- `saveLicense format=BINARY license.bin`

- copy newly created `license.bin` file to root folder of license-management project

- `dumpPublicKey`

- copy public key from console to LicenseKeeper.class

- run application

## license-management endpoint

There are two endpoints:

1. GET `http://localhost:8080/api/v1/license`
to get license information in following format:

```
   {
        "expireAt":"2023-01-13T17:00:00",
        "clientId":"6567bd4a-c87d-4278-a1e7-db3cb75ace45"
   }
```

2. GET `http://localhost:8080/api/v1/input`

   to run application workflow, response example:

```
    {
        "requestId":"16be0291-732b-4ecb-9d0d-0a3a66a63b89",
        "requestPayload":null,
        "responsePayload":"PROCESSED"
    }

```