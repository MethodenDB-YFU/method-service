# Methodendatenbank
[![Build Status](https://travis-ci.org/MethodenDB-YFU/backend.svg?branch=1-write-tests)](https://travis-ci.org/MethodenDB-YFU/backend) [![codecov](https://codecov.io/gh/MethodenDB-YFU/backend/branch/master/graph/badge.svg)](https://codecov.io/gh/MethodenDB-YFU/backend)

Methodendatenbank für das YFU Intranet

## Dependencies
* Docker
* Maven
* JDK 7

## Setup

```sh
git clone git@github.com:alexmsenger/methodendb.git
cd methodendb
./deploy/db/build.sh {VERSION}
./deploy/db/up.sh

./deploy/app/build.sh {VERSION}
./deploy/app/up.sh
```


