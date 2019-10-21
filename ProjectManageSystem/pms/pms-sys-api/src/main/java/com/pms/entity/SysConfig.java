package com.pms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

@TableName("sys_config")
public class SysConfig {

    /**
     * 删除标记（Y：正常；N：删除；A：审核；）
     */
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * int(11) NULL排序
     */
    private Integer sort;

    private String jkey;
    private String jvalue;

    @Length(min = 0, max = 64, message = "key长度必须介于 1 和 64 之间")
    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey;
    }

    @Length(min = 0, max = 1000, message = "value长度必须介于 1 和 1000 之间")
    public String getJvalue() {
        return jvalue;
    }

    public void setJvalue(String jvalue) {
        this.jvalue = jvalue;
    }

    @Length(min = 0, max = 500, message = "备注信息长度必须介于 1 和 500 之间")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @JsonIgnore
    @Length(min = 1, max = 1)
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


    @Length(min = 0, max = 11, message = "排序长度必须介于 1 和 11 之间")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


}
