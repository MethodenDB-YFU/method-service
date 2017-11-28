#!/bin/bash
echo "$1" > deploy/app/.latest

sh ./deploy/app/build.sh $1

printf '[INFO] Deploying App Version %s\n' "$1"
docker push yfudeutschland/methodendb-app:$1

sh ./deploy/app/up.sh