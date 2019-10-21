package com.pms.entity.projectMember;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：项目角色表
 * */
@Data
@TableName("tms_project_role")
public class ProjectRoleEntity {

    @TableField("role_id")
    private Long roleId;
    @TableField("role_name")
    private String roleName;

}
