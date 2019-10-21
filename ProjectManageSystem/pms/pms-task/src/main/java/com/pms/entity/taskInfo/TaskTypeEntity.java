package com.pms.entity.taskInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：任务类型表
 * */
@Data
@TableName("tms_task_type")
public class TaskTypeEntity {

    @TableField("task_type_encode")
    private Long taskTypeEncode;

    @TableField("task_type_name")
    private String taskTypeName;

}
