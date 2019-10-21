package com.pms.service.impl.baseInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.baseInfo.IProjectStatusDao;
import com.pms.entity.baseInfo.ProjectStatusEntity;
import com.pms.service.svcInterface.baseInfo.IProjectStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProjectStatusService extends BaseService<IProjectStatusDao, ProjectStatusEntity> implements IProjectStatusService {
    
}
