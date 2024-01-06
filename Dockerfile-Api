# Use a Debian-based image with Java pre-installed
FROM openjdk:17-jdk-slim

# Update package list and install Maven, Tesseract OCR, and German language pack for Tesseract
RUN apt-get update && \
    apt-get install -y maven tesseract-ocr tesseract-ocr-deu && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml /app/pom.xml

# Copy the source code
COPY src /app/src/

# Build the project
RUN mvn clean package

# Expose the port the app runs on
EXPOSE 8080

# Command to run the app
CMD ["java", "-jar", "target/paperless-1.0.jar"]