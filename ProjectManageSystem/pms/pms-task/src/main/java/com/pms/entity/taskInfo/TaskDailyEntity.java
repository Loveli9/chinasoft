package com.pms.entity.taskInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：任务日报表
 * */
@Data
@TableName("tms_task_daily")
public class TaskDailyEntity {

    @TableField("daily_newspaper")
    private String dailyNewspaper;

    @TableField("id")
    private Long id;

    @TableField("schedule")
    private Float schedule;

    @TableField("status")
    private Long status;

    @TableField("task_code")
    private String taskCode;

    @TableField("task_daily")
    private String taskDaily;

    @TableField("update_date")
    private String updateDate;

    @TableField("update_time")
    private String updateTime;

    @TableField("used_time")
    private Float usedTime;

}
