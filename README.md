#### This project introduces a simple service on java using Apache Kafka as a message broker, it was created for the student diploma Anton Popov from the State University of Telecommunications of Ukraine

To start this project locally you must have downloaded Kafka and Confluent libraries on your local pc

- Start the ZooKeeper service
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

- Start the Kafka broker service
```
bin/kafka-server-start.sh config/server.properties
```

- Create a topic to store your events
```
bin/kafka-topics.sh --create --topic dip --bootstrap-server localhost:9092
```

- Also, you need to install the confluent library to start the local schema registry
```
export CONFLUENT_HOME=/home/aopopov/confluent-7.2.1
confluent local services schema-registry start
```
bin/kafka-console-consumer.sh --topic dip --from-beginning --bootstrap-server localhost:9092
bin/kafka-avro-console-consumer --topic dip-avro --bootstrap-server localhost:9092  --from-beginning
