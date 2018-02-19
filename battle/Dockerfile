FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk add --no-cache curl
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
HEALTHCHECK --interval=30s --timeout=30s --retries=5 CMD sh -c 'curl -s --connect-timeout 10 'http://localhost:8081' || exit 1'
