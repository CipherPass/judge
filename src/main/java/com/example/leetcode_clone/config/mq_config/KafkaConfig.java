package com.example.leetcode_clone.config.mq_config;

import com.example.shared.kafka.KafkaConstant;
import com.example.shared.kafka.dto.RunCodeUpdateDTO;
import com.example.shared.kafka.dto.RunSolutionDTO;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, RunSolutionDTO> producerRunCodeFactory() {
        HashMap<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                KafkaConstant.KAFKA_LOCAL_SERVER_CONFIG);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.shared.kafka.dto");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, RunSolutionDTO> kafkaRunCodeTemplate() {
        return new KafkaTemplate<>(producerRunCodeFactory());
    }

    @Bean
    public ConsumerFactory<String, RunCodeUpdateDTO> consumerCodeupdateFactory() {
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_LOCAL_SERVER_CONFIG);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.shared.kafka.dto");
        return new DefaultKafkaConsumerFactory<>(configMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RunCodeUpdateDTO> kafkaCodeListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RunCodeUpdateDTO> factory = new ConcurrentKafkaListenerContainerFactory<String, RunCodeUpdateDTO>();
        factory.setConsumerFactory(consumerCodeupdateFactory());
        return factory;
    }
}