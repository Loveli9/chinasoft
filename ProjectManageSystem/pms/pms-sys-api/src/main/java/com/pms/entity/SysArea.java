package com.pms.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.pms.base.entity.BaseEntity;

import java.io.Serializable;

@TableName("sys_area")
public class SysArea extends BaseEntity<SysArea> {

    /**
     * varchar(64) NULL名称
     */
    private String name;
    /**
     * int(11) NULL排序
     */
    private Integer sort;
    /**
     * varchar(64) NULLdkey
     */
    private String jkey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey;
    }



    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
