package com.pms.dao.baseInfo;

import com.pms.base.dao.CrudDao;
import com.pms.entity.baseInfo.ProjectStatusEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface IProjectStatusDao extends CrudDao<ProjectStatusEntity> {

    /**
     * @attributionArea 城市代码
     * @return list
     */
    @Select("select * from tms_project_status where status_id = #{projectStatus}")
    ProjectStatusEntity selectByProjectStatus(Long projectStatus);
}
