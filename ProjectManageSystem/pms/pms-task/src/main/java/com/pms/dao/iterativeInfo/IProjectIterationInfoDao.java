package com.pms.dao.iterativeInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.dao.CrudDao;
import com.pms.entity.iterativeInfo.ProjectIterationInfoEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface IProjectIterationInfoDao extends CrudDao<ProjectIterationInfoEntity> {

    /**
     * @return boolean
     * @iterativeCode 迭代编码
     */
    @Delete("delete from tms_project_iteration_info where iterative_code = #{iterativeCode}")
    boolean deleteByIterativeCode(String iterativeCode);

    /**
     * @boolean
     * @pageSize
     * @projectOwnership
     * @iterativeState
     * @return list
     */
    @Select("SELECT a.*,b.iterative_state as iterativeStatus FROM tms_project_iteration_info a,tms_iterative_state b WHERE a.iterative_state = b.id and a.project_ownership = #{projectOwnership} and a.iterative_state = #{iterativeState}")
    List<ProjectIterationInfoEntity> queryByCondition(Page page, String projectOwnership, Integer iterativeState);

    @Select("SELECT a.*,b.iterative_state as iterativeStatus FROM tms_project_iteration_info a,tms_iterative_state b WHERE a.iterative_state = b.id and a.iterative_state = #{iterativeState}")
    List<ProjectIterationInfoEntity> queryByCondition1(Page page, String projectOwnership, Integer iterativeState);

    @Select("SELECT a.*,b.iterative_state as iterativeStatus FROM tms_project_iteration_info a,tms_iterative_state b WHERE a.iterative_state = b.id and a.project_ownership = #{projectOwnership}")
    List<ProjectIterationInfoEntity> queryByCondition2(Page page, String projectOwnership, Integer iterativeState);

    @Select("SELECT a.*,b.iterative_state as iterativeStatus FROM tms_project_iteration_info a,tms_iterative_state b WHERE a.iterative_state = b.id ")
    List<ProjectIterationInfoEntity> queryByCondition3(Page page, String projectOwnership, Integer iterativeState);

    /**
     * @projectOwnership 责任田编码
     * @return list
     */
    @Select("select iterative_code,iterative_name from tms_project_iteration_info where project_ownership = #{projectOwnership}")
    List<ProjectIterationInfoEntity> findIterativeName(String projectOwnership);

}