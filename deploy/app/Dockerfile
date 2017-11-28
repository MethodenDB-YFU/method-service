FROM maven:3.5-jdk-8-alpine
MAINTAINER Alex Senger <alexander.senger@yfu-deutschland.de>

COPY target/methodendb-0.0.1-SNAPSHOT.jar /app/
COPY deploy/app/startup.sh /

EXPOSE 8080

CMD ["bin/bash", "startup.sh"]