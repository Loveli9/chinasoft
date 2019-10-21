package com.pms.entity.taskInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：任务优先级表
 * */
@Data
@TableName("tms_task_priority")
public class TaskPriorityEntity {

    @TableField("id")
    private Long id;

    @TableField("task_priority")
    private String taskPriority;

    @TableField("task_weight")
    private Float taskWeight;

}
