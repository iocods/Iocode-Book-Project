FROM maven:3.8.5-openjdk AS build

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/IocodeBookProjects-1.0-SNAPSHOT.jar iocode-books.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "iocode-books.jar"]