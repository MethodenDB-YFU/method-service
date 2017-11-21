#!/bin/sh
latest=`cat deploy/db/.latest`

printf "[INFO] Starting up DB version %s\n" "$latest"
docker run -it -p5432:5432 --rm --name methodendb-db-$latest --net=ac-net --net-alias=shop-db yfudeutschland/methodendb-db:$latest