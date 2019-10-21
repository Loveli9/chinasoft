package com.pms.service.impl.iterativeInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.iterativeInfo.IIterativeStateDao;
import com.pms.entity.iterativeInfo.IterativeStateEntity;
import com.pms.service.svcInterface.iterativeInfo.IIterativeStateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IterativeStateService extends BaseService<IIterativeStateDao, IterativeStateEntity> implements IIterativeStateService {
    
}
