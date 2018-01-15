#!/bin/sh
latest=`cat deploy/db/main/.latest`

printf "[INFO] Starting up DB version %s\n" "$latest"
# docker run -it -p5432:5432 --rm --name methodendb-db-$latest yfudeutschland/methodendb-db:$latest
docker run -dit -p5432:5432 --net=methodendb --net-alias=db --rm --name methodendb-db-$latest yfudeutschland/methodendb-db:$latest