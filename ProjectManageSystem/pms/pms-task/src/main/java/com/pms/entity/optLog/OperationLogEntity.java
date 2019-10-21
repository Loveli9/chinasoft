package com.pms.entity.optLog;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：操作日志表
 * */
@Data
@TableName("tms_operation_log")
public class OperationLogEntity {

    @TableField("id")
    private Long id;
    @TableField("table_name")
    private String tableName;
    @TableField("field_name")
    private String fieldName;
    @TableField("operation")
    private String operation;
    @TableField("original_value")
    private String originalValue;
    @TableField("modified_value")
    private String modifiedValue;
    @TableField("operator")
    private String operator;
    @TableField("operating_time")
    private String operatingTime;
    @TableField("is_succeed")
    private Boolean isSucceed;

}
