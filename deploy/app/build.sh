#!/bin/bash
echo "$1" > deploy/app/.latest
mvn package -DskipTests

printf '[INFO] Building App Version %s\n' "$1"
docker build --no-cache=true -t yfudeutschland/methodendb-app:$1 -f deploy/app/Dockerfile .