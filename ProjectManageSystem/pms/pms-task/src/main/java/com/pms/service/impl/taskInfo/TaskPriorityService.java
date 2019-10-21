package com.pms.service.impl.taskInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.taskInfo.ITaskPriorityDao;
import com.pms.entity.taskInfo.TaskPriorityEntity;
import com.pms.service.svcInterface.taskInfo.ITaskPriorityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TaskPriorityService extends BaseService<ITaskPriorityDao, TaskPriorityEntity> implements ITaskPriorityService {
    
}
