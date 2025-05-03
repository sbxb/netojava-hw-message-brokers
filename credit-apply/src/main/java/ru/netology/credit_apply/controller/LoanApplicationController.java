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
import ru.netology.credit_apply.repository.LoanApplicationRepository;
import ru.netology.credit_apply.service.KafkaSender;

@RestController
@RequestMapping("/api/application")
public class LoanApplicationController {
    private final LoanApplicationRepository repo;
    private final KafkaSender kafkaSender;

    public LoanApplicationController(LoanApplicationRepository repo, KafkaSender kafkaSender) {
        this.repo = repo;
        this.kafkaSender = kafkaSender;
    }

    @PostMapping
    public LoanApplication save(@Valid @RequestBody LoanApplicationDto dto) {
        System.out.println(dto);
        LoanApplication application = convert(dto);
        System.out.println(application);
        repo.save(application);
        kafkaSender.sendToProcessor(application);
        return application;
    }

    @GetMapping("/{id}")
    public LoanApplication getStatus(@PathVariable("id") int id) {
        var application = repo.findById(id);
        if (application.isEmpty()) {
            throw new LoanApplicationNotFound("Loan application with id " + id + " not found");
        }
        return application.get();
    }

    @ExceptionHandler(LoanApplicationNotFound.class)
    public ResponseEntity<String> absentPersonHandler(LoanApplicationNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    private LoanApplication convert(LoanApplicationDto dto) {
        return new LoanApplication(
                null,
                dto.loanAmount(),
                dto.loanTerm(),
                dto.applicantIncome(),
                dto.creditLoad(),
                "PENDING");
    }
}
