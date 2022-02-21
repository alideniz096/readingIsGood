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
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "D_BOOK")
public class Book {
    @Id
    @SequenceGenerator(name = "bookSeq", sequenceName = "bookSequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSeq")
    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;

    @Column(name = "AUTHOR", nullable = false)
    private String bookAuthor;

    @Column(name = "ISBN_NUMBER", nullable = false)
    private String bookIsbnNumber;

    @Positive(message = "Price must be greater than zero")
    @Column(name = "PRICE", nullable = false)
    private BigDecimal bookPrice;

    @Min(value = 0, message = "Quantity must be equal or greater than zero")
    @Column(name = "STOCK", nullable = false)
    private Long bookStock;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    private Date creationDate;

}
