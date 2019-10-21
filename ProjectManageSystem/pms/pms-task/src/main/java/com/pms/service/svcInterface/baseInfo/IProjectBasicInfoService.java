package com.pms.service.svcInterface.baseInfo;

import com.pms.base.service.IBaseService;
import com.pms.entity.baseInfo.ProjectBasicInfoEntity;
import java.util.List;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface IProjectBasicInfoService extends IBaseService<ProjectBasicInfoEntity> {

    boolean updateByProjectCode(ProjectBasicInfoEntity projectBasicInfoEntity);

    boolean deleteByProjectCode(String projectCode);

    ProjectBasicInfoEntity queryProjectDetail(String projectCode);

    List<ProjectBasicInfoEntity> getAllProjectName(String queryConditionStr);

}
