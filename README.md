# Methodendatenbank
[![Build Status](https://travis-ci.org/alexmsenger/methodendb.svg?branch=master)](https://travis-ci.org/alexmsenger/methodendb)

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


