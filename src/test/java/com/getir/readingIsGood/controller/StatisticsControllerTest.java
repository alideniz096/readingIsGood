package com.getir.readingIsGood.controller;

import com.getir.readingIsGood.controller.vo.statistics.StatisticsResponse;
import com.getir.readingIsGood.dto.statistic.StatisticsResponseDto;
import com.getir.readingIsGood.service.StatisticsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {

    @InjectMocks
    private StatisticsController statisticsController;

    @Mock
    private StatisticsService statisticsService;

    @Test
    public void getStatistics() {

        Long customerId = 1L;
        List<StatisticsResponseDto> responseDtoList = new ArrayList<>();
        List<StatisticsResponse> responseList = new ArrayList<>();
        ResponseEntity<List<StatisticsResponse>> expectedResponse = ResponseEntity.ok(responseList);

        when(statisticsService.getMonthlyStatistics(customerId)).thenReturn(responseDtoList);

        ResponseEntity<List<StatisticsResponse>> response = statisticsController.getStatistics(customerId);

        Assert.assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    }
}
