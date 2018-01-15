#!/bin/sh
latest=`cat deploy/app/.latest`

printf '[INFO] Starting App Version %s\n' "$latest"
docker run --rm -it --name methodendb-app-$latest -p8080:8080 --net=methodendb --net-alias=app --env ENVIRONMENT="docker" yfudeutschland/methodendb-app:$latest