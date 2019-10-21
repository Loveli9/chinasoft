package com.pms.service.impl.userInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.userInfo.ISystemUserInfoDao;
import com.pms.entity.userInfo.SystemUserInfoEntity;
import com.pms.service.svcInterface.userInfo.ISystemUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service("systemUserInfoService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SystemUserInfoService extends BaseService<ISystemUserInfoDao, SystemUserInfoEntity> implements ISystemUserInfoService {

    @Resource
    private ISystemUserInfoDao iSystemUserInfoDao;

    @Override
    public List<SystemUserInfoEntity> login(SystemUserInfoEntity entity) {
        String loginNo = entity.getLoginNo();
        String password = entity.getPassword();
        List<SystemUserInfoEntity> outList = iSystemUserInfoDao.login(loginNo, password);
        return outList;
    }

    @Override
    public List<SystemUserInfoEntity> queryAllMember(String queryConditionStr) {
        List<SystemUserInfoEntity> outList = iSystemUserInfoDao.queryAllMember(queryConditionStr);
        return outList;
    }

}
