package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.statistic.StatisticsResponseDto;
import com.getir.readingIsGood.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerService customerService;

    @Test
    public void getMonthlyStatistics() {

        Long customerId = 1L;

        List<StatisticsResponseDto> expectedResult = new ArrayList<>();
        List<Tuple> repoResponse = new ArrayList<>();

        when(orderRepository.findCustomerOrderStatistic(customerId)).thenReturn(repoResponse);

        List<StatisticsResponseDto> actualResult = statisticsService.getMonthlyStatistics(customerId);

        Assert.assertEquals(expectedResult.size(), actualResult.size());
    }
}
