docker run -d \
    --name method_service_db \
    --net-alias=db \
    --net=methods \
    --rm \
    -e POSTGRES_DB=methods \
    -e POSTGRES_PASSWORD=methods \
    -e POSTGRES_USER=methods \
    -v `pwd`/src/main/resources/db/:/docker-entrypoint-initdb.d/ \
    postgres:9.6