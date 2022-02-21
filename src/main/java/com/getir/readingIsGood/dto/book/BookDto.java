package com.getir.readingIsGood.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookDto {

    private String bookName;
    private String bookAuthor;
    private String bookIsbnNumber;
    private BigDecimal bookPrice;
    private Long bookStock;
}
