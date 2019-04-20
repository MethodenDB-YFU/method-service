#!/bin/bash

docker build --cache-from yfudeutschland/method-service-build-cache -t yfudeutschland/method-service .

docker run -d \
    --name method_service \
    --net-alias=app \
    --net=methods \
    --rm \
    -e SPRING_PROFILE=docker \
    -p 8082:8080 \
    yfudeutschland/method-service