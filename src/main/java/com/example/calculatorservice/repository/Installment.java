package com.example.calculatorservice.repository;

import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Installment {

    private int month;

    private BigDecimal payment;

    private BigDecimal principal;

    private BigDecimal interest;

    private BigDecimal balance;
}