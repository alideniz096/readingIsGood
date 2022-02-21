package com.getir.readingIsGood.mappers;

import com.getir.readingIsGood.controller.vo.statistics.StatisticsResponse;
import com.getir.readingIsGood.dto.statistic.StatisticsResponseDto;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface StatisticsMapper {

    StatisticsResponse mapStatisticsResponseDtoToStatisticsResponse(StatisticsResponseDto statisticsResponseDto);
}
