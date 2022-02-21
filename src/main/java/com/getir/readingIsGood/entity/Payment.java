package com.getir.readingIsGood.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "D_PAYMENT")
public class Payment {

    @Id
    @SequenceGenerator(name = "paymentSeq", sequenceName = "paymentSequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentSeq")
    private Long id;
    private BigDecimal price;
    private String bankResponse;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        IN_PROGRESS, ERROR, SUCCESS
    }
}