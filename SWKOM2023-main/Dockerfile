FROM amazoncorretto:17-alpine-jdk
WORKDIR /app

RUN apk --no-cache add maven

COPY . /app

RUN mvn package -DskipTests

EXPOSE 8088
ENTRYPOINT ["java","-jar","target/swagger-spring-1.22.1.jar"]