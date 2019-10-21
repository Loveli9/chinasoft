package com.pms.service.impl.baseInfo;

import com.pms.base.service.impl.BaseService;
import com.pms.dao.baseInfo.*;
import com.pms.entity.baseInfo.*;
import com.pms.service.svcInterface.baseInfo.IProjectBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Primary
@Transactional(rollbackFor = Exception.class)
public class ProjectBasicInfoService extends BaseService<IProjectBasicInfoDao, ProjectBasicInfoEntity> implements IProjectBasicInfoService {

    @Autowired
    private IProjectBasicInfoDao projectBasicInfoDao;
    @Autowired
    private IAreaCodeDao areaCodeDao;
    @Autowired
    private IBusinessTypeDao businessTypeDao;
    @Autowired
    private IProjectStatusDao projectStatusDao;
    @Autowired
    private IProjectTypeDao projectTypeDao;

    @Override
    public boolean updateByProjectCode(ProjectBasicInfoEntity entity) {
        boolean result = projectBasicInfoDao.updateByProjectCode(entity.getProjectCode(),
                entity.getProjectName(),
                entity.getProjectDescription(),
                entity.getProjectType(),
                entity.getBusinessType(),
                entity.getBusinessGroup(),
                entity.getBusinessLine(),
                entity.getBusinessDepartment(),
                entity.getDeliveryDepartment(),
                entity.getAttributionArea(),
                entity.getProjectCreationTime(),
                entity.getFounder(),
                entity.getProjectStartTime(),
                entity.getProjectPlanEndTime(),
                entity.getActualProjectStartTime(),
                entity.getActualProjectEndTime(),
                entity.getProjectStatus(),
                entity.getDeliveryManager(),
                entity.getSpm(),
                entity.getPm(),
                entity.getQa(),
                entity.getCustomerInterfacePerson());
        return result;
    }

    @Override
    public boolean deleteByProjectCode(String projectCode) {
        boolean result = projectBasicInfoDao.deleteByProjectCode(projectCode);
        return result;
    }

    @Override
    public ProjectBasicInfoEntity queryProjectDetail(String projectCode) {
        ProjectBasicInfoEntity projectBasicInfoEntity = null;
        List<ProjectBasicInfoEntity> resultList =  projectBasicInfoDao.queryProjectDetail(projectCode);
        if(resultList != null || resultList.size() != 0) {
            projectBasicInfoEntity = resultList.get(0);
            String attributionArea = projectBasicInfoEntity.getAttributionArea();
            Long businessType = projectBasicInfoEntity.getBusinessType();
            Long projectStatus = projectBasicInfoEntity.getProjectStatus();
            Long projectType = projectBasicInfoEntity.getProjectType();
            //关联城市
            AreaCodeEntity areaCodeEntity = areaCodeDao.selectByAreaCode(attributionArea);
            if(areaCodeEntity != null) {
                projectBasicInfoEntity.setAttributionAreaLinked(areaCodeEntity.getCity());
            } else {
                projectBasicInfoEntity.setAttributionAreaLinked("");
            }
            //关联商务类型
            BusinessTypeEntity businessTypeEntity = businessTypeDao.selectById(businessType);
            if(businessTypeEntity != null) {
                projectBasicInfoEntity.setBusinessTypeLinked(businessTypeEntity.getBusinessType());
            } else {
                projectBasicInfoEntity.setBusinessTypeLinked("");
            }
            //关联项目状态
            ProjectStatusEntity projectStatusEntity = projectStatusDao.selectByProjectStatus(projectStatus);
            if(projectStatusEntity != null) {
                projectBasicInfoEntity.setProjectStatusLinked(projectStatusEntity.getProjectStatus());
            } else {
                projectBasicInfoEntity.setProjectStatusLinked("");
            }
            //关联项目类型
            ProjectTypeEntity projectTypeEntity = projectTypeDao.selectById(projectType);
            if(projectTypeEntity != null) {
                projectBasicInfoEntity.setProjectTypeLinked(projectTypeEntity.getProjectType());
            } else {
                projectBasicInfoEntity.setProjectTypeLinked("");
            }
        } else {
            projectBasicInfoEntity = new ProjectBasicInfoEntity();
        }
        return projectBasicInfoEntity;
    }

    @Override
    public List<ProjectBasicInfoEntity> getAllProjectName(String queryConditionStr) {
        List<ProjectBasicInfoEntity> resultList = projectBasicInfoDao.getAllProjectName(queryConditionStr);
        return resultList;
    }

}
