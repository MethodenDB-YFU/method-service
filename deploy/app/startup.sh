echo "Starting Java process"

set -x

java -server \
  -jar \
  -Dspring.profiles.active=$ENVIRONMENT \
  /app/methodendb-0.0.1-SNAPSHOT.jar
  # -Dspring.profiles.active=live \
  # -Djava.security.egd=file:/dev/./urandom \

## This point should never be reached
echo "FATAL: Java process exited"