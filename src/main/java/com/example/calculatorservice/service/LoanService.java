package com.example.calculatorservice.service;

import com.example.calculatorservice.repository.Installment;
import com.example.calculatorservice.repository.LoanRepository;
import com.example.calculatorservice.repository.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    @Autowired
    private final LoanRepository loanRepository;

    private static final BigDecimal PERCENTAGE = new BigDecimal(100);
    private static final BigDecimal NO_OF_MONTHS = new BigDecimal(12);

    public List<Installment> calculateLoan(BigDecimal amount, BigDecimal annualInterestPercentage, Integer numberOfMonths) {
        BigDecimal monthlyInterestRate = annualInterestPercentage.divide(PERCENTAGE, 10, RoundingMode.HALF_UP)
                .divide(NO_OF_MONTHS, 10, RoundingMode.HALF_UP);

        BigDecimal payment = amount.multiply(monthlyInterestRate)
                .divide(BigDecimal.ONE
                        .subtract(BigDecimal.ONE
                                .add(monthlyInterestRate)
                                .pow(-numberOfMonths, MathContext.DECIMAL128)), 10, RoundingMode.HALF_UP);

        List<Installment> installments = new ArrayList<>();
        BigDecimal remainingBalance = amount;

        for (int i = 1; i <= numberOfMonths; i++) {
            BigDecimal interest = remainingBalance.multiply(monthlyInterestRate);
            BigDecimal principal = payment.subtract(interest);
            remainingBalance = remainingBalance.subtract(principal);

            installments.add(new Installment(i, roundToScale(payment),
                    roundToScale(principal), roundToScale(interest),
                    roundToScale(remainingBalance)));
        }

        loanRepository.save(new Loan(installments));

        return installments;
    }

    private static BigDecimal roundToScale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}

