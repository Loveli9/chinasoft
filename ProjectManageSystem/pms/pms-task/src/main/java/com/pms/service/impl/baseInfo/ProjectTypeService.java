package com.pms.service.impl.baseInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.baseInfo.IProjectTypeDao;
import com.pms.entity.baseInfo.ProjectTypeEntity;
import com.pms.service.svcInterface.baseInfo.IProjectTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProjectTypeService extends BaseService<IProjectTypeDao, ProjectTypeEntity> implements IProjectTypeService {
    
}
