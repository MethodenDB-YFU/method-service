#!/bin/sh
latest=`cat deploy/app/.latest`

printf '[INFO] Starting Shop App Version %s\n' "$latest"
docker run --rm -it --name methodendb-app-$latest -p8082:8080 --env ENVIRONMENT="docker" yfudeutschland/methodendb-app:$latest