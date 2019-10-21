package com.pms.service.svcInterface.userInfo;

import com.pms.base.service.IBaseService;
import com.pms.entity.userInfo.SystemUserInfoEntity;
import java.util.List;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface ISystemUserInfoService extends IBaseService<SystemUserInfoEntity> {

    List<SystemUserInfoEntity> login(SystemUserInfoEntity entity);

    List<SystemUserInfoEntity> queryAllMember(String queryConditionStr);

}
