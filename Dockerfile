FROM maven:3.6-jdk-11-slim AS builder

# Copy source files
COPY . /app
WORKDIR /app

# Build all dependencies
RUN mvn dependency:go-offline -B

# Package for release, skip tests as there's no database
RUN mvn -Dmaven.test.skip=true package


# Final base image
FROM openjdk:11-jdk-slim

# Copy build artifacts from builder image
COPY --from=builder /app/target/method-service-*.jar /app/

# Copy startup script, expose listening port and run
COPY startup.sh /
EXPOSE 8080
CMD ["bin/bash", "startup.sh"]