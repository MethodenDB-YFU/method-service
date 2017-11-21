FROM postgres:latest
MAINTAINER Alex Senger <alexander.senger@yfu-deutschland.de>

ENV POSTGRES_DB methodendb
ENV POSTGRES_USER spring
ENV POSTGRES_PASSWORD spring

EXPOSE 5432

COPY db/* /docker-entrypoint-initdb.d/
