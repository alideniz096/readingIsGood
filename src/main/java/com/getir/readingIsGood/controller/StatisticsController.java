package com.getir.readingIsGood.controller;

import com.getir.readingIsGood.controller.vo.statistics.StatisticsResponse;
import com.getir.readingIsGood.dto.statistic.StatisticsResponseDto;
import com.getir.readingIsGood.mappers.StatisticsMapper;
import com.getir.readingIsGood.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@RestController
@RequestMapping(value = "/rig")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsMapper statisticsMapper = getMapper(StatisticsMapper.class);


    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/getStatistics")
    public ResponseEntity<List<StatisticsResponse>> getStatistics(@RequestParam Long customerId) {

        List<StatisticsResponseDto> statisticsDtos = statisticsService.getMonthlyStatistics(customerId);
        List<StatisticsResponse> statistics = new ArrayList<>();
        for (StatisticsResponseDto statisticDto : statisticsDtos) {
            statistics.add(statisticsMapper.mapStatisticsResponseDtoToStatisticsResponse(statisticDto));
        }
        return ResponseEntity.ok(statistics);
    }
}