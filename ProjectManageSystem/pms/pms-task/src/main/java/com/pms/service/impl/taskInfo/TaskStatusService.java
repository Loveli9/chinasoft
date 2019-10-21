package com.pms.service.impl.taskInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.taskInfo.ITaskStatusDao;
import com.pms.entity.taskInfo.TaskStatusEntity;
import com.pms.service.svcInterface.taskInfo.ITaskStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TaskStatusService extends BaseService<ITaskStatusDao, TaskStatusEntity> implements ITaskStatusService {
    
}
