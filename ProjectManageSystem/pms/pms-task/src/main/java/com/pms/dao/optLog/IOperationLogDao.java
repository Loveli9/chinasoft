package com.pms.dao.optLog;

import com.pms.base.dao.CrudDao;
import com.pms.entity.optLog.OperationLogEntity;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface IOperationLogDao extends CrudDao<OperationLogEntity> {
    
}
