package edu.stateuniversityoftelecommunications.project.service;

import edu.diploma.avro.schema.product.Product;
import edu.stateuniversityoftelecommunications.project.domain.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationProducer {
    @Value("${application.topics.first}")
    private String topic;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produce(ProductEntity productEntity) {
        Product product = Product.newBuilder()
                .setId(productEntity.getId())
                .setProductName(productEntity.getProductName())
                .setProductSku(productEntity.getProductSku())
                .setQuantity(productEntity.getQuantity())
                .setCreatedAt(productEntity.getCreatedAt())
                .setUpdatedAt(productEntity.getUpdatedAt())
                .build();

        ProducerRecord<String, Object> record =
                new ProducerRecord<>(topic, valueOf(product.getProductSku()), product);
        kafkaTemplate.send(record);
        kafkaTemplate.flush();
    }
}
