FROM gradle:8.14-jdk21 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle clean bootWar --no-daemon

#---------

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/discografia-1.war app.war

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.war" ]