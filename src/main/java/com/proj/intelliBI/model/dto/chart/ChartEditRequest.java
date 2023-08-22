package com.proj.intelliBI.model.dto.chart;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class ChartEditRequest implements Serializable {


    private Long id;


    private String name;

    /**
     * Analysis Goal
     */
    private String goal;


    private String chartData;


    private String chartType;


    private static final long serialVersionUID = 1L;
}