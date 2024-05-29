FROM maven:3.8.5-openjdk AS build

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/IocodeBookProjects-0.0.1-SNAPSHOT.jar iocode-books.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "iocode-books.jar"]