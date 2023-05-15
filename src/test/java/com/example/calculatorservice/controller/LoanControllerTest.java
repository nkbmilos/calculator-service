package com.example.calculatorservice.controller;

import com.example.calculatorservice.repository.Installment;
import com.example.calculatorservice.service.LoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoanControllerTest {

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanController loanController;

    @Test
    public void test() {
        BigDecimal amount = new BigDecimal(1000);
        BigDecimal annualInstallments = new BigDecimal(1000);

        List<Installment> loan = List.of(mock(Installment.class));

        given(loanService.calculateLoan(amount, annualInstallments, 10)).willReturn(loan);

        ResponseEntity<List<Installment>> response = loanController.loans(new InstallmentPlanRequest(amount, annualInstallments, 10));

        assertThat(response.getBody()).isEqualTo(loan);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}