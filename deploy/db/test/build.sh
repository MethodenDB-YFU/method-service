#!/bin/bash
echo "$1" > deploy/db/test/.latest

printf '[INFO] Building test DB version %s\n' "$1"
docker build --no-cache=true -t yfudeutschland/methodendb-db:$1 -f deploy/db/test/Dockerfile .
