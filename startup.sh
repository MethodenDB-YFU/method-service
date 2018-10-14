echo "Starting Java process"

set -x

java -server \
  -jar \
  -Dspring.profiles.active=$SPRING_PROFILE \
  /app/method-service-0.0.1.jar
