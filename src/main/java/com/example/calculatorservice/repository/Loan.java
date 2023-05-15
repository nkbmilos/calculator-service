package com.example.calculatorservice.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "loan")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 4000)
    @Convert(converter = JpaConverterJson.class)
    private List<Installment> installments;

    public Loan(List<Installment> installments) {
        this.installments = installments;
    }
}
