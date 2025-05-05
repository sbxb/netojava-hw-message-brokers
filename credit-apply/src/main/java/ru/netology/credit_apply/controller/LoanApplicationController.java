package ru.netology.credit_apply.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.credit_apply.exception.LoanApplicationNotFound;
import ru.netology.credit_apply.model.LoanApplication;
import ru.netology.credit_apply.model.LoanApplicationDto;
import ru.netology.credit_apply.model.LoanApplicationIdDto;
import ru.netology.credit_apply.model.LoanApplicationStatusDto;
import ru.netology.credit_apply.model.ModelConvertor;
import ru.netology.credit_apply.repository.LoanApplicationRepository;
import ru.netology.credit_apply.service.KafkaSender;

@RestController
@RequestMapping("/api/application")
public class LoanApplicationController {
    private static final double MONTHLY_RATE = 0.0125; // 15 percent per annum
    private final LoanApplicationRepository repo;
    private final KafkaSender kafkaSender;

    public LoanApplicationController(LoanApplicationRepository repo, KafkaSender kafkaSender) {
        this.repo = repo;
        this.kafkaSender = kafkaSender;
    }

    @PostMapping
    public LoanApplicationIdDto save(@Valid @RequestBody LoanApplicationDto dto) {
        var application = ModelConvertor.dtoToLoanApplication(dto);
        repo.save(application);
        System.out.println(application);
        kafkaSender.sendToProcessor(ModelConvertor.loanApplicationToEvent(application,
                MONTHLY_RATE));
        return new LoanApplicationIdDto(application.getId());
    }

    @GetMapping("/{id}")
    public LoanApplication getById(@PathVariable("id") int id) {
        var application = repo.findById(id);
        if (application.isEmpty()) {
            throw new LoanApplicationNotFound("Loan application with id " + id + " not found");
        }
        return application.get();
    }

    @GetMapping("/{id}/status")
    public LoanApplicationStatusDto getStatus(@PathVariable("id") int id) {
        var application = repo.findById(id);
        if (application.isEmpty()) {
            throw new LoanApplicationNotFound("Loan application with id " + id + " not found");
        }
        return ModelConvertor.loanApplicationToStatusDto(application.get());
    }

    @ExceptionHandler(LoanApplicationNotFound.class)
    public ResponseEntity<String> absentPersonHandler(LoanApplicationNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
