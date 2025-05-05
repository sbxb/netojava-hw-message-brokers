package ru.netology.credit_apply.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.netology.credit_apply.repository.LoanApplicationRepository;
import ru.netology.credit_common.model.LoanApplicationProcessorEvent;

@Service
public class RabbitMQReceiver {
    private final String mainQueue = "laq";
    private final LoanApplicationRepository repo;

    public RabbitMQReceiver(LoanApplicationRepository repo) {
        this.repo = repo;
    }

    @RabbitListener(queues = mainQueue)
    public void processOrder(LoanApplicationProcessorEvent e) {
        var applicationOptional = repo.findById(e.id());
        if (applicationOptional.isPresent()) {
            var application = applicationOptional.get();
            application.setStatus(e.status());
            repo.save(application);
        } else {
            System.out.println("WARNING got status update event for missing application id " + e.id());
        }
    }
}
