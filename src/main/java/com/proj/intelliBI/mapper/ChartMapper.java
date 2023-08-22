package com.proj.intelliBI.mapper;

import com.proj.intelliBI.model.entity.Chart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @Entity com.proj.intelliBI.model.entity.Chart
*/
@Mapper
public interface ChartMapper extends BaseMapper<Chart> {

    @MapKey("id")
    List<Map<String, Object>> queryChartData(String querySql);

    void createChart(@Param("createChartSql") String createChartSql);

    void insertChartData(String insertChartDataSql);
}




