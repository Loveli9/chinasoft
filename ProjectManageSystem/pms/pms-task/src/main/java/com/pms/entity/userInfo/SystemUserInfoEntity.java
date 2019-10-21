package com.pms.entity.userInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：系统用户信息表
 * */
@Data
@TableName("tms_system_user_info")
public class SystemUserInfoEntity {

    @TableField("id")
    private Long id;
    @TableField("login_no")
    private String loginNo;
    @TableField("domain_account")
    private String domainAccount;
    @TableField("password")
    private String password;
    @TableField("name")
    private String name;
    @TableField("mailbox")
    private String mailbox;
    @TableField("business_group")
    private String businessGroup;
    @TableField("business_line")
    private String businessLine;
    @TableField("business_department")
    private String businessDepartment;
    @TableField("delivery_department")
    private String deliveryDepartment;
    @TableField("account_expiration_time")
    private String accountExpirationTime;
    @TableField("password_expiration_time")
    private String passwordExpirationTime;
    @TableField("role")
    private String role;
    @TableField("is_locked")
    private Boolean isLocked;
    @TableField(exist = false)
    private String queryConditionStr;

}
