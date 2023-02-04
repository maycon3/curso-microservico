FROM maven:3.8.5-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG EUREKA_SERVER=localhost
ARG PASSWORD_EUREKA=curso-eureka-tem-que-ser-uma-senha-forte
ARG LOGIN_EUREKA=cursoms-eureka-user

ENTRYPOINT java -jar app.jar