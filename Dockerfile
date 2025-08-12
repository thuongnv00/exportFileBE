FROM openjdk:8-jdk

WORKDIR /app

RUN ./mvnw clean package -DskipTests

COPY target/EcommerceBE-0.0.1-SNAPSHOT.war app.jar

CMD ["java", "-jar", "target/*.jar"]