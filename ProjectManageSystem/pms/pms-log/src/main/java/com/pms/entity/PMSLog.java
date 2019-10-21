package com.pms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pms.base.entity.DataEntity;

import java.io.Serializable;


@TableName("sys_log")
public class PMSLog extends DataEntity<PMSLog> {

    /**
     * varchar(64) NULL日志来源类型
     */
    private String type;
    /**
     * varchar(500) NULL模块名称
     */
    private String tag;
    /**
     * varchar(1000) NULL全类名或者操作url
     */
    private String src;
    /**
     * varchar(15) NULLip
     */
    private String ip;
    /**
     * varchar(1000) NULL信息
     */
    private String msg;

    @TableField(value = "login_name")
    private String loginName;

    private String name;

    @TableField(value = "use_time")
    private Long useTime;

    private String params;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }
}
