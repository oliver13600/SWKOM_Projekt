FROM openjdk:11-jre-slim
LABEL maintainer="your-email@example.com"
VOLUME /tmp
ARG JAR_FILE=target/swagger-spring-1.22.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
