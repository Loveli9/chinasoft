package com.pms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.pms.base.entity.DataEntity;
import com.pms.constants.Global;
import com.pms.constants.SettingsConstants;


public abstract class CompanyLinkEntity<T extends Model> extends DataEntity<T> {


    public CompanyLinkEntity() {
        super();
        this.delFlag = SettingsConstants.DelFlag.NORMAL;

    }


    public CompanyLinkEntity(Long id) {
        super(id);
    }

    /**
     *
     * 公司实体对象
     */
    @TableField(exist=false)
    protected SysCompany sysCompany;

    /**
     * 公司iD
     */
   // @Length(min = 0, max = 64, message = "公司id长度必须介于 1 和 64 之间")
    @TableField(value = "sys_company_id")
    protected Long sysCompanyId;

    public SysCompany getSysCompany() {
        return sysCompany;
    }

    public void setSysCompany(SysCompany sysCompany) {
        this.sysCompany = sysCompany;
    }

    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }
}
