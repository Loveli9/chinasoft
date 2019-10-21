package com.pms.service.impl.userInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.userInfo.IProjectRoleInfoDao;
import com.pms.entity.userInfo.ProjectRoleInfoEntity;
import com.pms.service.svcInterface.userInfo.IProjectRoleInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProjectRoleInfoService extends BaseService<IProjectRoleInfoDao, ProjectRoleInfoEntity> implements IProjectRoleInfoService {
    
}
