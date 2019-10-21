package com.pms.service.impl;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.SysRoleMenuDao;
import com.pms.entity.SysRoleMenu;
import com.pms.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysRoleMenuService  extends BaseService<SysRoleMenuDao, SysRoleMenu> implements ISysRoleMenuService {
}
