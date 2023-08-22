package com.proj.intelliBI.utils;

import com.proj.intelliBI.mapper.ChartMapper;
import com.proj.intelliBI.model.entity.Chart;
import com.proj.intelliBI.service.ChartService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class ShardingTest {

    @Resource
    private ChartMapper chartMapper;

    long chartId = 1693516603029188610L;

    // 214 ms
    @Test
    public void testWithSharding() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String querySql = String.format("select * from chart_%s where id = 5", chartId);
        List<Map<String, Object>> resultData = chartMapper.queryChartData(querySql);

        System.out.println(resultData);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    // 329ms
    @Test
    public void testNoSharding() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Chart chart = new Chart();

        for (int i = 0; i < 1; i++) {
            chart = chartMapper.selectById(chartId);
        }
        String[] rows = ExcelUtils.oneStringToListOfRows(chart.getChartData());
        String result = rows[5];
        System.out.println(result);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
