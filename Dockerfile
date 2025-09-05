# Sử dụng JDK làm base image
FROM openjdk:21-jdk-slim

# Tạo thư mục app trong container
WORKDIR /app

# Copy file jar vào container
COPY target/*.jar app.jar

# Expose port 8080 (Spring Boot mặc định)
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
