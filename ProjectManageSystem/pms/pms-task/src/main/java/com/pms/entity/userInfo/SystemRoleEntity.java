package com.pms.entity.userInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：系统角色表
 * */
@Data
@TableName("tms_system_role")
public class SystemRoleEntity {

    @TableField("role_id")
    private Long roleId;
    @TableField("role_name")
    private String roleName;
    @TableField("resource_authority")
    private String resourceAuthority;
    @TableField("id_enable")
    private Boolean idEnable;

}
