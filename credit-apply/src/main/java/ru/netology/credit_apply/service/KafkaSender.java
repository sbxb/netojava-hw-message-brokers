package ru.netology.credit_apply.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.netology.credit_common.model.LoanApplicationProcessorEvent;

@Service
public class KafkaSender {
    @Value("${my.kafka.topic}")
    private String mainTopic;

    private final KafkaTemplate<String, LoanApplicationProcessorEvent> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, LoanApplicationProcessorEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToProcessor(LoanApplicationProcessorEvent e) {
        kafkaTemplate.send(mainTopic, e);
    }
}
