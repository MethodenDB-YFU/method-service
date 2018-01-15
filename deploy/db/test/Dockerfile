FROM postgres:latest
MAINTAINER Alex Senger <alexander.senger@yfu-deutschland.de>

ENV POSTGRES_DB methodendb-test
ENV POSTGRES_USER methodendb-test
ENV POSTGRES_PASSWORD methodendb-test
# ENV POSTGRES_DB $POSTGRES_DB
# ENV POSTGRES_USER $POSTGRES_USER
# ENV POSTGRES_PASSWORD methodendb-test


EXPOSE 5432

COPY db/* /docker-entrypoint-initdb.d/
RUN ["rm", "/docker-entrypoint-initdb.d/20_seed.sql"]

