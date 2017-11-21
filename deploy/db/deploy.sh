#!/bin/bash
echo "$1" > deploy/db/.latest

sh ./deploy/db/build.sh $1

printf '[INFO] Deploying DB version %s\n' "$1"
docker push yfudeutschland/methodendb-db:$1

sh ./deploy/db/up.sh