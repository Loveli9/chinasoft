package com.pms.service.impl.taskInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.taskInfo.ITaskTypeDao;
import com.pms.entity.taskInfo.TaskTypeEntity;
import com.pms.service.svcInterface.taskInfo.ITaskTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TaskTypeService extends BaseService<ITaskTypeDao, TaskTypeEntity> implements ITaskTypeService {
    
}
