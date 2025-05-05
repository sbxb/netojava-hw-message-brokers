package ru.netology.credit_apply.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.netology.credit_common.model.LoanApplicationProcessorEvent;

@Service
public class RabbitMQReceiver {
    private final String mainQueue = "laq";

    @RabbitListener(queues = mainQueue)
    public void processOrder(LoanApplicationProcessorEvent e) {
        System.out.println("!!! " + e);
    }
}
