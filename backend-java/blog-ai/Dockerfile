FROM gradle:7.4.0-jdk17-alpine as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/build/libs/*.jar ./aimicroservice.jar
ENTRYPOINT ["java","-jar","aimicroservice.jar"]