package edu.stateuniversityoftelecommunications.project.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfiguration {
    
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(
            ProducerFactory<Integer, String> producerFactory) {
        return new KafkaTemplate<>(
                new DefaultKafkaProducerFactory<>(producerFactory.getConfigurationProperties()));
    }
}
