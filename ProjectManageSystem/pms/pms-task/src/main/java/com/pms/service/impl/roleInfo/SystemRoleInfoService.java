package com.pms.service.impl.roleInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.roleInfo.ISystemRoleInfoDao;
import com.pms.entity.roleInfo.SystemRoleInfoEntity;
import com.pms.service.svcInterface.roleInfo.ISystemRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SystemRoleInfoService extends BaseService<ISystemRoleInfoDao, SystemRoleInfoEntity> implements ISystemRoleInfoService {

    @Autowired
    private ISystemRoleInfoDao systemRoleInfoDao;

    @Override
    public boolean updateByRoleId(Long roleId,String rolePermissions,Integer isEnable) {
        boolean result = systemRoleInfoDao.updateByRoleId(roleId,rolePermissions,isEnable);
        return result;
    }

    @Override
    public boolean deleteByRoleId(String roleId) {
        Long lRoleId = "".equals(roleId) ? 0L : Long.parseLong(roleId);
        boolean result = systemRoleInfoDao.deleteByRoleId(lRoleId);
        return result;
    }

}
