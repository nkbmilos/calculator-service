package com.example.calculatorservice.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class InstallmentPlanRequest {
    @NotNull(message = "The amount is required.")
    @Positive(message = "The amount must be positive.")
    private BigDecimal amount;

    @NotNull(message = "The interest rate is required.")
    @Positive(message = "The interest rate must be positive.")
    private BigDecimal annualInterestPercentage;

    @NotNull(message = "The months number is required.")
    @Positive(message = "The months number must be positive.")
    private Integer numberOfMonths;
}
