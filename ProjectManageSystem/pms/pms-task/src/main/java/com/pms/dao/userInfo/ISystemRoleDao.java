package com.pms.dao.userInfo;

import com.pms.base.dao.CrudDao;
import com.pms.entity.taskInfo.TaskTypeEntity;
import com.pms.entity.userInfo.SystemRoleEntity;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface ISystemRoleDao extends CrudDao<SystemRoleEntity> {
    
}
