package com.pms.service.impl.optLog;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.optLog.IOperationLogDao;
import com.pms.entity.optLog.OperationLogEntity;
import com.pms.service.svcInterface.optLog.IOperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OperationLogService extends BaseService<IOperationLogDao, OperationLogEntity> implements IOperationLogService {
    
}
