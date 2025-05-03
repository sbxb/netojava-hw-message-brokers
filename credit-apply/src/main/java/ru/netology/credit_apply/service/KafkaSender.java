package ru.netology.credit_apply.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.netology.credit_apply.model.LoanApplication;
import ru.netology.credit_apply.model.LoanApplicationDto;

@Service
public class KafkaSender {
    @Value("${my.kafka.topic}")
    private String mainTopic;

    private final KafkaTemplate<String, LoanApplication> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, LoanApplication> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToProcessor(LoanApplication application) {
        kafkaTemplate.send(mainTopic, application);
    }
}
