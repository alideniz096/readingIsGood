package com.getir.readingIsGood.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "D_CUSTOMER")
public class Customer {
    @Id
    @SequenceGenerator(name = "customerSeq", sequenceName = "customerSequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;

    @Column(name = "CUSTOMER_SURNAME", nullable = false)
    private String customerSurname;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String customerPhoneNumber;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String customerEmail;

    @Column(name = "ADDRESS", nullable = false)
    private String customerAddress;

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private Date creationDate;
}