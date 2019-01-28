FROM maven:3.6-jdk-11-slim AS builder

LABEL maintainer="Alexander Senger <alexander.senher@yfu-deutschland.de>"
LABEL version="0.0.1"

# Copy pom first to allow for caching
COPY ./pom.xml /app/pom.xml
WORKDIR /app

# Build all dependencies
RUN mvn dependency:go-offline -B

# Copy rest of files
COPY . /app

# Package for release, skip tests as there's no database
RUN mvn -Dmaven.test.skip=true package



# Final base image
FROM openjdk:11-jre-slim

# Copy build artifacts from builder image
COPY --from=builder /app/target/method-service-*.jar /app/

# Copy startup script, expose listening port and run
COPY startup.sh /
EXPOSE 8080
CMD ["bin/bash", "startup.sh"]