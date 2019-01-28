FROM maven:3.6-jdk-11-slim AS builder

# Copy source files
COPY . /app
WORKDIR /app

# Build all dependencies
RUN mvn dependency:go-offline -B

# build release
RUN mvn -Dmaven.test.skip=true package


# Final base image
FROM openjdk:11-jdk-slim

# Copy build artifacts
COPY --from=builder /app/target/method-service-*.jar /app/

# Copy start script
COPY startup.sh /

# Expose listening port
EXPOSE 8080

# Run
CMD ["bin/bash", "startup.sh"]