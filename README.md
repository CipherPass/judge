
# How to run this project

```bash
KAFKA_ROOT_DIR=`~/path/to/kafka_2.x-x.x.x`
PROJECT_ROOT=`~/path/to/project/root/`
```

## Messaging Queue common usage
### Start Zookeeper
```bash
"$KAFKA_ROOT_DIR/bin/zookeeper-server-start.sh" "$KAFKA_ROOT_DIR/config/zookeeper.properties"
```
### Start Kafka Broker
```bash
"$KAFKA_ROOT_DIR/bin/kafka-server-start.sh" "$KAFKA_ROOT_DIR/config/server.properties"
```
### Delete Topic
```bash
"$KAFKA_ROOT_DIR/bin/kafka-topics.sh" -bootstrap-server localhost:9092 --delete --topic ProblemSubmittion
```
### Start consumer
```bash
"$KAFKA_ROOT_DIR/bin/kafka-console-consumer.sh" -bootstrap-server localhost:9092 --topic ProblemSubmittion --from-beginning
```

## Start Postgres
```bash
brew services start postgresql@14
```

## Run Backend
```bash
cd "$PROJECT_ROOT/judge"
./gradlew bootrun
```

## Run CodeExecutor
```bash
cd "$PROJECT_ROOT/code_executor"
./gradlew bootrun
```

## Start Frontend
```bash
cd "$PROJECT_ROOT/webapp"
npm start
```
