package com.getir.readingIsGood.controller.vo.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookItem {
    @Min(value = 1, message = "BookId must be minimum 1")
    private Long bookId;
    @Min(value = 1, message = "Quantity must be minimum 1")
    private Long quantity;
}
