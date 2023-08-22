package com.proj.intelliBI.service;

import com.proj.intelliBI.model.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author chengxiguo
* @description 针对表【chart(图表信息表)】的数据库操作Service
* @createDate 2023-08-10 00:51:30
*/
public interface ChartService extends IService<Chart> {

    void saveChartData(MultipartFile multipartFile, long chartId);
}
