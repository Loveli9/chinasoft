package com.pms.dao.taskInfo;

import com.pms.base.dao.CrudDao;
import com.pms.entity.taskInfo.TaskStatusEntity;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface ITaskStatusDao extends CrudDao<TaskStatusEntity> {
    
}
