package com.pms.service.svcInterface.responsibleField;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.IBaseService;
import com.pms.base.vo.DataTable;
import com.pms.entity.responsibleField.ResponsibleFieldInfoEntity;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface IResponsibleFieldInfoService extends IBaseService<ResponsibleFieldInfoEntity> {

    List<ResponsibleFieldInfoEntity> queryByMap(Map<String, Object> inMap);

    boolean updateTmsResponsibleFieldInfo(ResponsibleFieldInfoEntity responsibleFieldCode);

    boolean deleteByResponsibleFieldCode(String responsibleFieldCode);

    Page<ResponsibleFieldInfoEntity> getViewField(Page page, DataTable<ResponsibleFieldInfoEntity> dt);

    Page<ResponsibleFieldInfoEntity> queryByCondition(Page page,DataTable<ResponsibleFieldInfoEntity> dt);

}
