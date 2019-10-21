package com.pms.service;

import com.pms.base.service.IBaseService;
import com.pms.base.vo.JsTree;
import com.pms.entity.SysOffice;

import java.util.List;

/**
 * Describe: 组织
 */
public interface ISysOfficeService  extends IBaseService<SysOffice> {
    /**
     * 获取组织架构树
     * @return
     */
    List<JsTree> getOfficeTree();

    /**
     * 保存office （添加）
     * @param sysOffice
     */
    void add(SysOffice sysOffice);

    /**
     * 更新组织
     * @param sysOffice
     */
    void update(SysOffice sysOffice);
}
