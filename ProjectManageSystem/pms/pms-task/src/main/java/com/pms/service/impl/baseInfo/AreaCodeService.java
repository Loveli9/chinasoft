package com.pms.service.impl.baseInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.baseInfo.IAreaCodeDao;
import com.pms.entity.baseInfo.AreaCodeEntity;
import com.pms.service.svcInterface.baseInfo.IAreaCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AreaCodeService extends BaseService<IAreaCodeDao, AreaCodeEntity> implements IAreaCodeService {
    
}
