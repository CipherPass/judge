#!/bin/bash

# Function to stop Kafka and Zookeeper
stop_services() {
  echo "Stopping Kafka and Zookeeper..."
  ~/kafka_2.13-3.0.0/bin/kafka-server-stop.sh
  ~/kafka_2.13-3.0.0/bin/zookeeper-server-stop.sh
  exit 0  # Exit the script gracefully
}

# Trap the termination signal to stop services before exiting
trap 'stop_services' SIGTERM

# Start Zookeeper
~/kafka_2.13-3.0.0/bin/zookeeper-server-start.sh ~/kafka_2.13-3.0.0/config/zookeeper.properties &
sleep 3

# Start Kafka Broker
# ~/kafka_2.13-3.0.0/bin/kafka-server-start.sh ~/kafka_2.13-3.0.0/config/server.properties &

# Run the Java application
java -jar app.jar
JAVA_EXIT_CODE=$?

# Exit the script with the same exit code as the Java application
exit $JAVA_EXIT_CODE
