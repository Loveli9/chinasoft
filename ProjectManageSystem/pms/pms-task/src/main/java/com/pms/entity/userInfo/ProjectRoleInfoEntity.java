package com.pms.entity.userInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：项目角色任职信息表
 * */
@Data
@TableName("tms_project_role_info")
public class ProjectRoleInfoEntity {

    @TableField("id")
    private Long id;
    @TableField("number")
    private String number;
    @TableField("project")
    private String project;
    @TableField("role_id")
    private Long roleId;
    @TableField("start_time_for_role")
    private String startTimeForRole;
    @TableField("end_time_for_role")
    private String endTimeForRole;
    @TableField("evaluation")
    private String evaluation;
    @TableField("evaluation_time")
    private String evaluationTime;
    @TableField("evaluator")
    private String evaluator;

}
