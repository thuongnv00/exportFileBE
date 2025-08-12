FROM openjdk:8-jdk
WORKDIR /app

# Copy toàn bộ code vào container
COPY . .

# Build
RUN ./mvnw clean package -DskipTests

# Chạy ứng dụng
CMD ["java", "-jar", "target/EcommerceBE-0.0.1-SNAPSHOT.war"]