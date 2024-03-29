package com.pms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;


@TableName("sys_user_sys_office")
public class SysUserOffice implements Serializable{


    private static final long serialVersionUID = -7037483917783879927L;
    /**
     * bigint(18) NOT NULL用户id
     */
    @TableField(value = "sys_user_id")
    private Long sysUserId;
    /**
     * bigint(18) NOT NULL系统组织架构id
     */
    @TableField(value = "sys_office_id")
    private Long sysOfficeId;

    /**
     * char(1) NULL主负责人1，副负责人2，普通员工3
     */
    private String major;


    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
