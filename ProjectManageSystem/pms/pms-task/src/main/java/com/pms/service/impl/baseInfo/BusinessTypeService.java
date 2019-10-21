package com.pms.service.impl.baseInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.baseInfo.IBusinessTypeDao;
import com.pms.entity.baseInfo.BusinessTypeEntity;
import com.pms.service.svcInterface.baseInfo.IBusinessTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BusinessTypeService extends BaseService<IBusinessTypeDao, BusinessTypeEntity> implements IBusinessTypeService {
    
}
