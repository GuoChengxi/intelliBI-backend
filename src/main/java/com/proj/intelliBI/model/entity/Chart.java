package com.proj.intelliBI.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Chart Table
 * @TableName chart
 */
@TableName(value ="chart")
@Data
public class Chart implements Serializable {
    /**
     * Chart id
     */
    // if id type is auto increment, there may be security problem
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * Chart Name
     */
    private String name;

    /**
     * Analysis Goal
     */
    private String goal;


    private String chartData;


    private String chartType;

    /**
     * Chart Data Generated
     */
    private String genChart;

    /**
     * Analytical Results Generated
     */
    private String genResult;

    /**
     * Task Status
     */
    private Integer status;

    /**
     * Message during Execution
     */
    private String execMessage;

    /**
     * id of User that Adds Chart Request
     */
    private Long userId;


    private Date createTime;


    private Date updateTime;

    /**
     * Whether the data is deleted
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}