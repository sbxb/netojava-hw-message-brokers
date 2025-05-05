package ru.netology.credit_apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.credit_apply.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Integer> {

}
