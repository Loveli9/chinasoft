package com.pms.dao.baseInfo;

import com.pms.base.dao.CrudDao;
import com.pms.entity.baseInfo.ProjectBasicInfoEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
public interface IProjectBasicInfoDao extends CrudDao<ProjectBasicInfoEntity> {

    /**
     * @return boolean
     */
    @Update("update tms_project_basic_info set project_name = #{projectName}," +
            "project_description = #{projectDescription}," +
            "project_type = #{projectType}," +
            "business_type = #{businessType}," +
            "business_group = #{businessGroup}," +
            "business_line = #{businessLine}," +
            "business_department = #{businessDepartment}," +
            "delivery_department = #{deliveryDepartment}," +
            "attribution_area = #{attributionArea}," +
            "project_creation_time = #{projectCreationTime}," +
            "founder = #{founder}," +
            "project_start_time = #{projectStartTime}," +
            "project_plan_end_time = #{projectPlanEndTime}," +
            "actual_project_start_time = #{actualProjectStartTime}," +
            "actual_project_end_time = #{actualProjectEndTime}," +
            "project_status = #{projectStatus}," +
            "delivery_manager = #{deliveryManager}," +
            "spm = #{spm},pm = #{pm},qa = #{qa}," +
            "customer_interface_person = #{customerInterfacePerson} " +
            "where project_code = #{projectCode}")
    boolean updateByProjectCode(String projectCode,
                                String projectName,
                                String projectDescription,
                                Long projectType,
                                Long businessType,
                                String businessGroup,
                                String businessLine,
                                String businessDepartment,
                                String deliveryDepartment,
                                String attributionArea,
                                String projectCreationTime,
                                String founder,
                                String projectStartTime,
                                String projectPlanEndTime,
                                String actualProjectStartTime,
                                String actualProjectEndTime,
                                Long projectStatus,
                                String deliveryManager,
                                String spm,
                                String pm,
                                String qa,
                                String customerInterfacePerson);


    /**
     * @return boolean
     */
    @Delete("delete from tms_project_basic_info where project_code = #{projectCode}")
    boolean deleteByProjectCode(String projectCode);

    /**
     * @projectCode 项目编码
     * @return list
     */
    @Select("select * from tms_project_basic_info where project_code = #{projectCode}")
    List<ProjectBasicInfoEntity> queryProjectDetail(String projectCode);

    /**
     * @projectCode 项目编码
     * @return list
     */
    @Select("<script>" +
            "select project_code as projectCode,project_name as projectName " +
            "from tms_project_basic_info " +
            "<if test='queryConditionStr!=null and queryConditionStr!=\"\"'>" +
                "where project_code like CONCAT('%', #{queryConditionStr ,jdbcType=VARCHAR}, '%') " +
                "or project_name like CONCAT('%', #{queryConditionStr ,jdbcType=VARCHAR}, '%') " +
            "</if>" +
            "</script>")
    List<ProjectBasicInfoEntity> getAllProjectName(@Param("queryConditionStr") String queryConditionStr);

}
