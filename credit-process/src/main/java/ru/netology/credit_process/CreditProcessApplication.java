package ru.netology.credit_process;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import ru.netology.credit_common.model.LoanApplicationProcessorEvent;
import ru.netology.credit_common.model.LoanApplicationStatus;
import ru.netology.credit_process.service.LoanApplicationProcessor;

@SpringBootApplication
public class CreditProcessApplication {
    private final String mainQueue = "laq";
    private final String mainExchange = "lax";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private LoanApplicationProcessor processor;

    public static void main(String[] args) {
        SpringApplication.run(CreditProcessApplication.class, args);
    }

    @KafkaListener(topics = "loan-applications")
    public void processMessage(LoanApplicationProcessorEvent e) {
        var status = processor.approved(e) ? LoanApplicationStatus.APPROVED : LoanApplicationStatus.REJECTED;
        LoanApplicationProcessorEvent res = new LoanApplicationProcessorEvent(
                e.id(),
                e.loanAmount(),
                e.loanTerm(),
                e.applicantIncome(),
                e.creditLoad(),
                e.monthlyRate(),
                status
        );
        System.out.println(res);

        rabbitTemplate.convertAndSend(mainExchange, "lakey", res);
    }
}
