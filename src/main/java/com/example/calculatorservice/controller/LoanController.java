package com.example.calculatorservice.controller;

import com.example.calculatorservice.repository.Installment;
import com.example.calculatorservice.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/loans")
@Validated
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<List<Installment>> loans(@RequestBody @Valid InstallmentPlanRequest request) {
        List<Installment> installments = loanService.calculateLoan(request.getAmount(), request.getAnnualInterestPercentage(), request.getNumberOfMonths());
        return ResponseEntity.ok(installments);
    }
}