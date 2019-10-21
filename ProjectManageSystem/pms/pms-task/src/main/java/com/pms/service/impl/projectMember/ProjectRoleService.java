package com.pms.service.impl.projectMember;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.projectMember.IProjectRoleDao;
import com.pms.entity.projectMember.ProjectRoleEntity;
import com.pms.service.svcInterface.projectMember.IProjectRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProjectRoleService extends BaseService<IProjectRoleDao, ProjectRoleEntity> implements IProjectRoleService {
    
}
