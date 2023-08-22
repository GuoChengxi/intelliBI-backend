package com.proj.intelliBI.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.proj.intelliBI.service.ChartService;
import com.proj.intelliBI.model.entity.Chart;
import com.proj.intelliBI.mapper.ChartMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author chengxiguo
* @description 针对表【chart(图表信息表)】的数据库操作Service实现
* @createDate 2023-08-10 00:51:30
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService {

    @Resource
    ChartMapper chartMapper;

    @Override
    /**
     * Save every uploaded data file to a new table
     * @return
     */
    public void saveChartData(MultipartFile multipartFile, long chartId) {
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("表格处理错误", e);
        }
        if (CollUtil.isEmpty(list)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE chart_" + chartId + " (");
        sb.append("id bigint auto_increment comment 'id' primary key,");
        // 读取表头
        LinkedHashMap<Integer, String> headerMap = (LinkedHashMap) list.get(0);
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        sb.append("`" + headerList.get(0) + "` varchar(50) NOT NULL,");
        sb.append("`" + headerList.get(1) + "` bigint NOT NULL,");
        sb.append("`" + headerList.get(2) + "` bigint NOT NULL");
        sb.append(")");
//        List<Map<String, Object>> resultData = chartMapper.queryChartData("select * from chart");
//        System.out.println(resultData);
        chartMapper.createChart(sb.toString());

        // 读取数据
        for (int j = 0; j < 1; j++) {
            for (int i = 1; i < list.size(); i++) {
                sb = new StringBuilder();
                sb.append("INSERT INTO chart_" + chartId + " (");
                for (String header : headerList) {
                    sb.append(header + ",");
                }
                sb.deleteCharAt(sb.length() - 1); // remove the last comma
                sb.append(") VALUES (");
                LinkedHashMap<Integer, String> dataMap = (LinkedHashMap) list.get(i);
                List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
                dataList.set(0, "\"" + dataList.get(0) + "\"");
                sb.append(StringUtils.join(dataList, ","));
                sb.deleteCharAt(sb.length() - 1); // strip the last comma
                sb.append(");\n");
                chartMapper.insertChartData(sb.toString());
            }
        }

    }
}




