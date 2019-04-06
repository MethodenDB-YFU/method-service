FROM maven:3.6-jdk-11-slim AS builder

# Get Dependencies
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Build
COPY src /build/src
RUN mvn -Dmaven.test.skip=true clean package

# Runtime
FROM openjdk:11-jre-slim

COPY --from=builder /build/target/method-service-0.0.1.jar /app/
COPY startup.sh /

EXPOSE 8080

ENTRYPOINT ["/bin/bash", "startup.sh"]