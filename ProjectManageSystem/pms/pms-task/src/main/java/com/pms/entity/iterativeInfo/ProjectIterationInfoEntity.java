package com.pms.entity.iterativeInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：项目迭代信息表
 * */
@Data
@TableName("tms_project_iteration_info")
public class ProjectIterationInfoEntity {

    @TableField("iterative_code")
    private String iterativeCode;
    @TableField("project_ownership")
    private String projectOwnership;
    @TableField("iterative_name")
    private String iterativeName;
    @TableField("iteration_goal")
    private String iterationGoal;
    @TableField("iterative_state")
    private Long iterativeState;
    @TableField("iterative_description")
    private String iterativeDescription;
    @TableField("iteration_plan_start_date")
    private String iterationPlanStartDate;
    @TableField("iteration_plan_end_date")
    private String iterationPlanEndDate;
    @TableField("iterate_actual_start_date")
    private String iterateActualStartDate;
    @TableField("iterative_actual_end_date")
    private String iterativeActualEndDate;
    @TableField("progress_deviation_value")
    private Long progressDeviationValue;
    @TableField("schedule_deviation")
    private Float scheduleDeviation;
    @TableField("founder")
    private String founder;
    @TableField("creation_time")
    private String creationTime;
    @TableField("recently_updated_staff")
    private String recentlyUpdatedStaff;
    @TableField("last_update_time")
    private String lastUpdateTime;

    @TableField(exist = false)
    private String iterativeStatus;

}
