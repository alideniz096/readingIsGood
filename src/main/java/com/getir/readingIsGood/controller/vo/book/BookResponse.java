package com.getir.readingIsGood.controller.vo.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookIsbnNumber;
    private BigDecimal bookPrice;
    private Long bookStock;
    private Date creationDate;
}
