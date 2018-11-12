FROM maven:3.5-jdk-8-alpine AS builder

COPY . /app
WORKDIR /app
RUN mvn -Dmaven.test.skip=true clean package

FROM maven:3.5-jdk-8-alpine

COPY --from=builder /app/target/method-service-0.0.1.jar /app/
COPY startup.sh /

EXPOSE 8080

CMD ["bin/bash", "startup.sh"]