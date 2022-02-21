package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.statistic.StatisticsResponseDto;
import com.getir.readingIsGood.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public StatisticsService(OrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    public List<StatisticsResponseDto> getMonthlyStatistics(Long customerId) {

        customerService.getCustomer(customerId);

        List<Tuple> statistics = orderRepository.findCustomerOrderStatistic(customerId);

        ArrayList<StatisticsResponseDto> statisticList = new ArrayList<>();
        for (Tuple statistic : statistics) {
            String month = (String) statistic.get(0);
            BigDecimal totalAmount = (BigDecimal) statistic.get(1);
            BigInteger totalOrderCount = (BigInteger) statistic.get(2);
            BigDecimal totalBookCount = (BigDecimal) statistic.get(3);
            StatisticsResponseDto tempStatistic = StatisticsResponseDto.builder()
                    .month(month)
                    .totalPurchasedAmount(totalAmount)
                    .totalBookCount(totalBookCount)
                    .totalOrderCount(totalOrderCount.intValue())
                    .build();

            statisticList.add(tempStatistic);
        }

        return statisticList;
    }
}
