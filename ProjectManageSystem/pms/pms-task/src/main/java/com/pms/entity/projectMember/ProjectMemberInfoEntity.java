package com.pms.entity.projectMember;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：项目成员信息表
 * */
@Data
@TableName("tms_project_member_info")
public class ProjectMemberInfoEntity {

    @TableField("id")
    private Long id;
    @TableField("project_code")
    private String projectCode;
    @TableField("iterative_code")
    private String iterativeCode;
    @TableField("domain_account")
    private String domainAccount;
    @TableField(exist = false)
    private String name;
    @TableField("input_ratio")
    private Long inputRatio;
    @TableField("role_id")
    private Long roleId;
    @TableField("enter_project_time")
    private String enterProjectTime;
    @TableField("exit_project_time")
    private String exitProjectTime;

}
