package com.proj.intelliBI.model.dto.chart;

import com.proj.intelliBI.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class ChartQueryRequest extends PageRequest implements Serializable {


    private Long id;

    /**
     * Chart Name
     */
    private String name;


    private String goal;


    private String chartType;


    private Long userId;

    private static final long serialVersionUID = 1L;
}