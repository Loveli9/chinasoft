package com.pms.service.impl.userInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.userInfo.ISystemRoleDao;
import com.pms.entity.userInfo.SystemRoleEntity;
import com.pms.service.svcInterface.userInfo.ISystemRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SystemRoleService extends BaseService<ISystemRoleDao, SystemRoleEntity> implements ISystemRoleService {
    
}
