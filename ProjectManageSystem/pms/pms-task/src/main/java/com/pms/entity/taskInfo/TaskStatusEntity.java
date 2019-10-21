package com.pms.entity.taskInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：任务状态表
 * */
@Data
@TableName("tms_task_status")
public class TaskStatusEntity {

    @TableField("id")
    private Long id;

    @TableField("task_status")
    private String taskStatus;

}
