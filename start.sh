#!/bin/bash

KAFKA_ROOT_DIR=~/kafka_2.13-3.4.0

# Function to stop Kafka and Zookeeper
stop_services() {
	echo "Stopping Kafka and Zookeeper..."
	$KAFKA_ROOT_DIR/bin/kafka-server-stop.sh
	$KAFKA_ROOT_DIR/bin/zookeeper-server-stop.sh
	exit 0 # Exit the script gracefully
}

# Trap the termination signal to stop services before exiting
trap 'stop_services' SIGTERM

# Start Zookeeper
$KAFKA_ROOT_DIR/bin/zookeeper-server-start.sh ~/kafka_2.13-3.4.0/config/zookeeper.properties &
sleep 5

# Start Kafka Broker
$KAFKA_ROOT_DIR/bin/kafka-server-start.sh ~/kafka_2.13-3.4.0/config/server.properties &

# Run the Java application
java -jar app.jar &
APP_PID=$!

sleep 20

# Start executor.jar
java -jar executor.jar &

# Wait for the background Java application to finish
wait $APP_PID

# Exit the script
exit 0

