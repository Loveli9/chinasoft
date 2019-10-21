package com.pms.service.impl.responsibleField;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.responsibleField.IResponsibleFieldMemberInfoDao;
import com.pms.entity.responsibleField.ResponsibleFieldMemberInfoEntity;
import com.pms.service.svcInterface.responsibleField.IResponsibleFieldMemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResponsibleFieldMemberInfoService extends BaseService<IResponsibleFieldMemberInfoDao, ResponsibleFieldMemberInfoEntity> implements IResponsibleFieldMemberInfoService {

    @Autowired
    private IResponsibleFieldMemberInfoDao responsibleFieldMemberInfoDao;

    @Override
    public boolean updateTmsResponsibleFieldMemberInfo(ResponsibleFieldMemberInfoEntity responsibleFieldMemberInfo) {
        String responsibleFieldCode = responsibleFieldMemberInfo.getResponsibleFieldCode();
        String employeeNumber = responsibleFieldMemberInfo.getEmployeeNumber();
        String memberName = responsibleFieldMemberInfo.getMemberName();
        Integer isResponsible = responsibleFieldMemberInfo.getIsResponsible();
        boolean result = responsibleFieldMemberInfoDao.updateTmsResponsibleFieldMemberInfo(
                responsibleFieldCode,employeeNumber,memberName,isResponsible);
        return result;
    }

    @Override
    public List<ResponsibleFieldMemberInfoEntity> queryAllMemberByResponsibleFieldCode(String responsibleFieldCode) {
        List<ResponsibleFieldMemberInfoEntity> resultList = responsibleFieldMemberInfoDao.queryAllMemberByResponsibleFieldCode(responsibleFieldCode);
        return resultList;
    }

    @Override
    public boolean insertTmsResponsibleFieldMemberInfo(ResponsibleFieldMemberInfoEntity responsibleFieldMemberInfo) {
        String responsibleFieldCode = responsibleFieldMemberInfo.getResponsibleFieldCode();
        String employeeNumber = responsibleFieldMemberInfo.getEmployeeNumber();
        String memberName = responsibleFieldMemberInfo.getMemberName();
        Integer isResponsible = responsibleFieldMemberInfo.getIsResponsible();
        Integer isReserve = responsibleFieldMemberInfo.getIsReserve();
        boolean result = responsibleFieldMemberInfoDao.insertTmsResponsibleFieldMemberInfo(
                responsibleFieldCode,employeeNumber,memberName,isResponsible,isReserve);
        return result;
    }



}
