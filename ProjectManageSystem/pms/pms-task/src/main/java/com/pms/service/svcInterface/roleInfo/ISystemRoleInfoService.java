package com.pms.service.svcInterface.roleInfo;

import com.pms.base.service.IBaseService;
import com.pms.entity.roleInfo.SystemRoleInfoEntity;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface ISystemRoleInfoService extends IBaseService<SystemRoleInfoEntity> {

    boolean updateByRoleId(Long roleId,String rolePermissions,Integer isEnable);

    boolean deleteByRoleId(String roleId);


}
