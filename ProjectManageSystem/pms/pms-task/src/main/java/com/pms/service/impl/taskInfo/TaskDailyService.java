package com.pms.service.impl.taskInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.taskInfo.ITaskDailyDao;
import com.pms.entity.taskInfo.TaskDailyEntity;
import com.pms.service.svcInterface.taskInfo.ITaskDailyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TaskDailyService extends BaseService<ITaskDailyDao, TaskDailyEntity> implements ITaskDailyService {
    
}
