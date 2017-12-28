#!/bin/sh
latest=`cat deploy/db/test/.latest`

printf "[INFO] Starting up test DB version %s\n" "$latest"
# docker run -it -p5432:5432 --rm --name methodendb-db-$latest yfudeutschland/methodendb-db:$latest
docker run -it -p5432:5432 --rm --name methodendb-db-test-$latest yfudeutschland/methodendb-db:$latest
