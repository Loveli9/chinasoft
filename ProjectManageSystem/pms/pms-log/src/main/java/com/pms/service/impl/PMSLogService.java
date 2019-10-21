package com.pms.service.impl;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.PMSLogDao;
import com.pms.entity.PMSLog;
import com.pms.service.IPMSLogService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PMSLogService extends BaseService<PMSLogDao, PMSLog> implements IPMSLogService {


}
