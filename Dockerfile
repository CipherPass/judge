# Use a base image with OpenJDK 17, Kafka, and PostgreSQL
FROM openjdk:17-slim

# Install Apache Kafka
RUN apt-get update && apt-get install -y curl
RUN curl -O https://archive.apache.org/dist/kafka/3.4.0/kafka_2.13-3.4.0.tgz
RUN tar xzf kafka_2.13-3.4.0.tgz
RUN mv kafka_2.13-3.4.0 ~

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/leetcode_clone-0.0.1-SNAPSHOT.jar app.jar
COPY build/libs/CodeExecutor-0.0.1-SNAPSHOT.jar executor.jar

# Expose the port that your Java application will run on (if applicable)
EXPOSE ${PORT:-8888}

# Add startup script to start services
COPY start.sh /app/start.sh
RUN chmod +x /app/start.sh

# Set the entry point to run the startup script
ENTRYPOINT ["/app/start.sh"]



