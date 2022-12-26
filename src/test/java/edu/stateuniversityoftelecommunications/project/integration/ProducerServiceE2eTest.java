package edu.stateuniversityoftelecommunications.project.integration;

import edu.diploma.avro.schema.product.Product;
import edu.stateuniversityoftelecommunications.project.anotation.IntegrationTest;
import edu.stateuniversityoftelecommunications.project.domain.ProductEntity;
import edu.stateuniversityoftelecommunications.project.service.ShipmentProducer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import lombok.SneakyThrows;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static edu.stateuniversityoftelecommunications.project.Util.TestConstant.MESSAGE_NAME;
import static edu.stateuniversityoftelecommunications.project.Util.TestConstant.QUANTITY_THEN;
import static edu.stateuniversityoftelecommunications.project.Util.TestUtil.newProductEntity;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@IntegrationTest
//public class ProducerServiceE2eTest {
//
//    @Autowired
//    ShipmentProducer shipmentProducer;
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    EmbeddedKafkaBroker embeddedKafkaBroker;
//    @Autowired
//    ConsumerFactory<Integer, String> consumerFactory;
//    @Value("${application.topics.first}")
//    private String topic;
//
//    @Test
//    @SneakyThrows
//    void shouldSentMessageToTopic() {
//        //arrange:
//        Instant timeNow = Instant.now();
//
//        ProductEntity product = newProductEntity();
//
//        //act:
//        shipmentProducer.produce(product);
//
//        //assert:
//        ConsumerRecords<String, SpecificRecordBase> consumerRecords = pullRecords();
//        Optional<Product> expectedResult =
//                StreamSupport.stream(consumerRecords.spliterator(), false)
//                        .map(ConsumerRecord::value)
//                        .filter(r -> r instanceof Product)
//                        .map(r -> (Product) r)
//                        .findFirst();
//
//        assertTrue(expectedResult.isPresent());
//        assertEquals(MESSAGE_NAME, valueOf(expectedResult.get().getProductName()));
//        assertEquals(QUANTITY_THEN, expectedResult.get().getQuantity());
//        assertEquals(timeNow.getEpochSecond(), expectedResult.get().getCreatedAt().getEpochSecond());
//    }
//
//    private ConsumerRecords<String, SpecificRecordBase> pullRecords() {
//        try (Consumer<String, SpecificRecordBase> consumer = configureConsumer()) {
//            return KafkaTestUtils.getRecords(consumer);
//        }
//    }
//
//    private Consumer<String, SpecificRecordBase> configureConsumer() {
//        Map<String, Object> consumerProps =
//                KafkaTestUtils.consumerProps(
//                        "ProducerServiceE2eTest", "true", embeddedKafkaBroker);
//        consumerProps.putAll(consumerFactory.getConfigurationProperties());
//        consumerProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
//        Consumer<String, SpecificRecordBase> consumer =
//                new DefaultKafkaConsumerFactory<String, SpecificRecordBase>(consumerProps).createConsumer();
//        consumer.subscribe(Collections.singleton(topic));
//        return consumer;
//    }
//
//}
