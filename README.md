# Methodendatenbank
[![Build Status](https://semaphoreci.com/api/v1/alexsenger/method-service/branches/74-remove-user-id-header/badge.svg)](https://semaphoreci.com/alexsenger/method-service)

Methodendatenbank für das YFU Intranet

## Dependencies
* Docker
* Maven
* JDK 8

## Setup

```sh
git clone git@github.com:alexmsenger/methodendb.git
cd methodendb
./deploy/db/build.sh {VERSION}
./deploy/db/up.sh

./deploy/app/build.sh {VERSION}
./deploy/app/up.sh
```

## API
Once you run the app with `mvn spring-boot:run` (or via Docker), you can access: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html) and find a Swagger UI to see the endpoints. You can also check the `swagger.yaml` of this repository. 

