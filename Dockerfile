#
# Build stage
#
FROM maven:3.8.7-openjdk-18-slim AS build
ARG POM_FILE=/home/app/pom.xml
COPY pom.xml ${POM_FILE}
RUN mvn -f ${POM_FILE} clean
RUN mvn -f ${POM_FILE} dependency:resolve
COPY src /home/app/src
RUN mvn -f ${POM_FILE} package

#
# Package stage
#
FROM openjdk:18-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG jar_FILE=/home/app/target/*.jar
COPY --from=build ${jar_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
