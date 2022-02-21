package com.getir.readingIsGood.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponseDto {

    private String month;
    private Integer totalOrderCount;
    private BigDecimal totalBookCount;
    private BigDecimal totalPurchasedAmount;
}
