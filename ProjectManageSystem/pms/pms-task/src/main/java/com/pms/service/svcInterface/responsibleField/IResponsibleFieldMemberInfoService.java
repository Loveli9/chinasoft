package com.pms.service.svcInterface.responsibleField;

import com.pms.base.service.IBaseService;
import com.pms.entity.responsibleField.ResponsibleFieldMemberInfoEntity;

import java.util.List;

/**
 * Created by  on 2019/09/05.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface IResponsibleFieldMemberInfoService extends IBaseService<ResponsibleFieldMemberInfoEntity> {

    boolean updateTmsResponsibleFieldMemberInfo(ResponsibleFieldMemberInfoEntity responsibleFieldInfoEntity);

    List<ResponsibleFieldMemberInfoEntity> queryAllMemberByResponsibleFieldCode(String responsibleFieldCode);

    boolean insertTmsResponsibleFieldMemberInfo(ResponsibleFieldMemberInfoEntity responsibleFieldInfoEntity);

}
