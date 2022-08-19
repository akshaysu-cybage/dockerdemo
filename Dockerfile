FROM eclipse-temurin:8-jdk-jammy as base

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

FROM base as test
RUN ["./mvnw", "test"]

FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:8 as development
EXPOSE 8083
COPY --from=build /app/target/dockerdemo-*.jar /dockerdemo.jar
CMD ["java", "-jar", "/dockerdemo.jar"]