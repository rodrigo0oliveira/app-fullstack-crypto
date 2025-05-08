FROM gradle:7.4.0-jdk17-alpine as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/build/libs/*.jar ./commentsmicroservice.jar
ENTRYPOINT ["java","-jar","commentsmicroservice.jar"]