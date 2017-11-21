#!/bin/bash
echo "$1" > deploy/db/.latest

printf '[INFO] Building DB version %s\n' "$1"
docker build --no-cache=true -t yfudeutschland/methodendb-db:$1 -f deploy/db/Dockerfile .