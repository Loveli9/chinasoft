package com.pms.service;

import com.pms.base.service.IBaseService;
import com.pms.base.vo.DataTable;
import com.pms.entity.SysUser;
import com.pms.entity.SysUserOffice;


public interface ISysUserOfficeService extends IBaseService<SysUserOffice> {


    /**
     * 移除组织中的人
     * @param officeId
     * @param userIds
     */
    void removeUsers(Long officeId, Long[] userIds);

    /**
     * 向组织中添加用户
     * @param officeId
     * @param userIds
     * @param major
     */
    void addUsers(Long officeId, Long[] userIds, String major);

    /**
     * 获取组织的用户列表
     * @param dt
     * @return
     */
    DataTable<SysUser> userList(DataTable dt);
}
