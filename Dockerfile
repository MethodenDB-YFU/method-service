FROM maven:3.5-jdk-8-alpine
MAINTAINER Alex Senger <alexander.senger@yfu-deutschland.de>

COPY target/method-service-0.0.1.jar /app/
COPY startup.sh /

EXPOSE 8080

CMD ["bin/bash", "startup.sh"]