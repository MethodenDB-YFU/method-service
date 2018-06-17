docker run -d \
    --name method-service-db-0.0.1 \
    --rm \
    --net=methods \
    --net-alias=db \
    -p 5432:5432 \
    -e POSTGRES_DB=methods \
    -e POSTGRES_USER=methods \
    -e POSTGRES_PASSWORD=methods \
    -v `pwd`/src/main/resources/db/schema.sql:/docker-entrypoint-initdb.d/schema.sql \
    postgres:9.6