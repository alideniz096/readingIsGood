package com.getir.readingIsGood.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private String bookName;
    private String bookAuthor;
    private String bookIsbnNumber;
    private BigDecimal bookPrice;
    private Long bookStock;
}
