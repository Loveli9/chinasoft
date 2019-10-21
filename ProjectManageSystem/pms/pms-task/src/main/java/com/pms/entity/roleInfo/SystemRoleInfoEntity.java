package com.pms.entity.roleInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：系统角色信息表
 * */
@Data
@TableName("tms_system_role_info")
public class SystemRoleInfoEntity {

    @TableField("role_id")
    private Long roleId;
    @TableField("role_name")
    private String roleName;
    @TableField("role_permissions")
    private String rolePermissions;
    @TableField("is_enable")
    private Integer isEnable;

}
