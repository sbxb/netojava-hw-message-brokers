package ru.netology.credit_apply.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Value("${my.kafka.topic}")
    private String mainTopic;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(mainTopic).build();
    }
}
