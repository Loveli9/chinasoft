package com.pms.entity.taskInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：任务信息表
 * */
@Data
@TableName("tms_task_info")
public class TaskInfoEntity {

    @TableField("task_code")
    private String taskCode;

    @TableField("task_name")
    private String taskName;

    @TableField("project_code")
    private String projectCode;

    @TableField("iterative_code")
    private String iterativeCode;

    @TableField("parent_task_code")
    private String parentTaskCode;

    @TableField("responsible_field_code")
    private String responsibleFieldCode;

    @TableField("task_level")
    private Long taskLevel;

    @TableField(exist = false)
    private String taskLevelName;

    @TableField("task_type")
    private Long taskType;

    @TableField(exist = false)
    private String taskTypeName;

    @TableField("task_priority")
    private String taskPriority;

    @TableField("creation_time")
    private String creationTime;

    @TableField("task_start_time")
    private String taskStartTime;

    @TableField("task_end_time")
    private String taskEndTime;

    @TableField("planned_time")
    private Float plannedTime;

    @TableField("founder")
    private String founder;

    @TableField("responsible")
    private String responsible;

    @TableField(exist=false)
    private String responsiblePerson;

    @TableField("status")
    private Long status;

    @TableField("schedule")
    private Float schedule;

    @TableField("remarks")
    private String remarks;

}
