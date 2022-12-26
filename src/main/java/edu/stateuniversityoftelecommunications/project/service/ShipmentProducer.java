package edu.stateuniversityoftelecommunications.project.service;

import edu.diploma.warehouse.schema.audit.Carton;
import edu.diploma.warehouse.schema.audit.Product;
import edu.diploma.warehouse.schema.audit.ShipmentEnded;
import edu.stateuniversityoftelecommunications.project.domain.CartonEntity;
import edu.stateuniversityoftelecommunications.project.domain.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentProducer {
    @Value("${application.topics.first}")
    private String topic;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostConstruct
    void run() {
        Instant now = Instant.now();
        String sku = "sku";
        CartonEntity carton = CartonEntity.builder().id(1L).orderNumber("123").productSku(sku).build();


        ProductEntity product = new ProductEntity(1L, "pc", sku, 1, now, now, carton);
        carton.setProducts(Set.of(product));
        produce(carton);
    }
    public void produce(CartonEntity cartonEntity) {

        Set<ProductEntity> products = cartonEntity.getProducts();
        ProductEntity next = products.iterator().next();

        Product product = Product.newBuilder().setProductSku(
                next.getProductSku())
                .setProductName(next.getProductName())
                .setCreatedAt(next.getCreatedAt())
                .setUpdatedAt(next.getUpdatedAt())
                .setQuantity(next.getQuantity())
                .setId(next.getId()).build();
        Carton carton = Carton.newBuilder().setOrderNumber("12345")
                .setProductSku(cartonEntity.getProductSku())
                .setProducts(List.of(product))
                .build();
        ShipmentEnded shipmentEnded = ShipmentEnded.newBuilder().setOrderNumber(carton.getOrderNumber()).build();

        ProducerRecord<String, Object> cartonRecord =
                new ProducerRecord<>(topic, valueOf(carton.getOrderNumber()), carton);
        ProducerRecord<String, Object> shipmentRecord =
                new ProducerRecord<>(topic, valueOf(shipmentEnded.getOrderNumber()), shipmentEnded);
        kafkaTemplate.send(cartonRecord);
        kafkaTemplate.send(shipmentRecord);
        kafkaTemplate.flush();
    }
}
