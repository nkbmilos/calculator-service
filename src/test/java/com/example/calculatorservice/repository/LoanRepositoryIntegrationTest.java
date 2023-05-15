package com.example.calculatorservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanRepositoryIntegrationTest {

    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void save() {
        loanRepository.save(new Loan(List.of(new Installment(12, new BigDecimal(120), new BigDecimal(10), new BigDecimal(5), new BigDecimal(1000)))));

        final List<Loan> loans = loanRepository.findAll();
        assertThat(loans).hasSize(1);

        final Loan loan = loans.get(0);
        assertThat(loan.getId()).isPositive();

        final List<Installment> installments = loan.getInstallments();
        assertThat(installments).hasSize(1);
        assertThat(installments.get(0).getBalance()).isEqualTo(new BigDecimal(1000));
        assertThat(installments.get(0).getMonth()).isEqualTo(12);
        assertThat(installments.get(0).getPayment()).isEqualTo(new BigDecimal(120));
        assertThat(installments.get(0).getPrincipal()).isEqualTo(new BigDecimal(10));
        assertThat(installments.get(0).getInterest()).isEqualTo(new BigDecimal(5));
    }
}