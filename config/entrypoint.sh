#!/bin/sh
set -e
echo "---------------------------------OPTS------------------------------------"
echo "JAVA_OPTS="$JAVA_OPTS
echo "SPRING_CONFIG_LOCATION="$SPRING_CONFIG_LOCATION
echo "-------------------------------------------------------------------------"
java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar