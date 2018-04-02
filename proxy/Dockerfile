FROM openjdk:8-jdk-alpine

VOLUME /tmp
RUN apk add --no-cache curl

ARG JAR_FILE
ENV JAVA_OPTS "-Xmx256m -Xms128m"
ENV SPRING_CONFIG_LOCATION ""

ADD ${JAR_FILE} app.jar
ADD ./entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
HEALTHCHECK --interval=30s --timeout=30s --retries=20 CMD sh -c 'curl -s --connect-timeout 10 'http://localhost:9000' || exit 1'
