package com.pms.service.impl.responsibleField;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.impl.BaseService;
import com.pms.base.vo.DataTable;
import com.pms.dao.responsibleField.IResponsibleFieldInfoDao;
import com.pms.dao.responsibleField.IResponsibleFieldMemberInfoDao;
import com.pms.entity.responsibleField.ResponsibleFieldInfoEntity;
import com.pms.entity.responsibleField.ResponsibleFieldMemberInfoEntity;
import com.pms.service.svcInterface.responsibleField.IResponsibleFieldInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResponsibleFieldInfoService extends BaseService<IResponsibleFieldInfoDao, ResponsibleFieldInfoEntity> implements IResponsibleFieldInfoService {

    @Resource
    private IResponsibleFieldInfoDao responsibleFieldInfoDao;

    @Autowired
    private IResponsibleFieldMemberInfoDao responsibleFieldMemberInfoDao;

    @Override
    public List<ResponsibleFieldInfoEntity> queryByMap(Map<String, Object> inMap) {
        List<ResponsibleFieldInfoEntity> outList = responsibleFieldInfoDao.selectByMap(inMap);
        return outList;
    }

    @Override
    public boolean updateTmsResponsibleFieldInfo(ResponsibleFieldInfoEntity responsibleFieldInfoEntity) {
        String responsibleFieldCode = responsibleFieldInfoEntity.getResponsibleFieldCode();
        Integer isEnabled = responsibleFieldInfoEntity.getIsEnabled();
        String remarks = responsibleFieldInfoEntity.getRemarks();
        boolean result = responsibleFieldInfoDao.updateTmsResponsibleFieldInfo(
                responsibleFieldCode,isEnabled,remarks);
        return result;
    }

    @Override
    public boolean deleteByResponsibleFieldCode(String responsibleFieldCode) {
        boolean result = responsibleFieldInfoDao.deleteByResponsibleFieldCode(responsibleFieldCode);
        return result;
    }

    @Override
    public Page<ResponsibleFieldInfoEntity> getViewField(Page page, DataTable<ResponsibleFieldInfoEntity> dt) {
        String projectCode = "";
        if(dt.getSearchParams() != null) {
            projectCode = (dt.getSearchParams().get("search_eq_project_code") == null) ?
                    "" : dt.getSearchParams().get("search_eq_project_code").toString();
        }
        List<ResponsibleFieldInfoEntity> outList = responsibleFieldInfoDao.getViewField(page,projectCode);
        for(ResponsibleFieldInfoEntity entity : outList) {
            List<ResponsibleFieldMemberInfoEntity> resultList = responsibleFieldMemberInfoDao.queryAllMemberByResponsibleFieldCode(entity.getResponsibleFieldCode());
            entity.setModifiedMemberList(resultList);
        }
        return page.setRecords(outList);
    }

    @Override
    public Page<ResponsibleFieldInfoEntity> queryByCondition(Page page,DataTable<ResponsibleFieldInfoEntity> dt) {
        String projectCode = "";
        if(dt.getSearchParams() != null) {
            projectCode = (dt.getSearchParams().get("search_eq_project_code") == null) ?
                    "" : dt.getSearchParams().get("search_eq_project_code").toString();
        }
        List<ResponsibleFieldInfoEntity> outList = responsibleFieldInfoDao.queryByCondition(page , projectCode);
        for(ResponsibleFieldInfoEntity entity : outList) {
            List<ResponsibleFieldMemberInfoEntity> resultList = responsibleFieldMemberInfoDao.queryAllMemberByResponsibleFieldCode(entity.getResponsibleFieldCode());
            entity.setModifiedMemberList(resultList);
        }
        page.setRecords(outList);
        return page;
    }

}
