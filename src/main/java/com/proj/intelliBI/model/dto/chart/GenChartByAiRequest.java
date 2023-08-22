package com.proj.intelliBI.model.dto.chart;

import lombok.Data;

import java.io.Serializable;


/**
 * Data File Upload Request
 */
@Data
public class GenChartByAiRequest implements Serializable {

    /**
     * Chart Name
     */
    private String name;


    private String goal;


    private String chartType;

    private static final long serialVersionUID = 1L;
}