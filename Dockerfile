# Use a base image compatible with all platforms
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/contact-book-api-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
