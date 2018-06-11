# Methodendatenbank
[![Build Status](https://travis-ci.org/MethodenDB-YFU/backend.svg?branch=1-write-tests)](https://travis-ci.org/MethodenDB-YFU/backend) [![codecov](https://codecov.io/gh/MethodenDB-YFU/backend/branch/master/graph/badge.svg)](https://codecov.io/gh/MethodenDB-YFU/backend)

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

