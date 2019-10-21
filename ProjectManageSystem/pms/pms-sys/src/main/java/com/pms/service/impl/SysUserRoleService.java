package com.pms.service.impl;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.SysUserRoleDao;
import com.pms.entity.SysUserRole;
import com.pms.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * <p>
 * Describe: 系统用户角色中间表
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysUserRoleService extends BaseService<SysUserRoleDao, SysUserRole> implements ISysUserRoleService {
}
