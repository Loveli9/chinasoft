package com.pms.dao.taskInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.dao.CrudDao;
import com.pms.entity.taskInfo.TaskInfoEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
@Component("taskInfoDao")
public interface ITaskInfoDao extends CrudDao<TaskInfoEntity> {

    /**
     * @param page 翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @iterativeCode 迭代编码
     * @responsible 责任人工号
     * @return
     */
    @Select("SELECT a.*,b.NAME as responsiblePerson FROM tms_task_info a,tms_system_user_info b WHERE a.responsible = b.login_no and b.login_no = #{responsible} and a.iterative_code = #{iterativeCode}")
    List<TaskInfoEntity> getTasksByIterativeCode(Page page,String iterativeCode,String responsible);


    /**
     * @queryConditionStr 查询条件
     * @return list
     */
    @Select("<script>" +
            "select task_code,task_name " +
            "from tms_task_info " +
            "<if test='queryConditionStr!=null and queryConditionStr!=\"\"'>" +
                "where task_code like CONCAT('%', #{queryConditionStr ,jdbcType=VARCHAR}, '%') " +
                "or task_name like CONCAT('%', #{queryConditionStr ,jdbcType=VARCHAR}, '%')" +
            "</if>" +
            "</script>")
    List<TaskInfoEntity> getAllTaskName(@Param("queryConditionStr") String queryConditionStr);

    /**
     * @taskCode 任务编码
     * @return boolean
     */
    @Delete("delete from tms_task_info where task_code = #{taskCode}")
    boolean deleteTaskInfoByTaskCode(String taskCode);

    /**
     * @param page 翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @taskType 任务类型
     * @responsible 责任人工号
     * @projectCode 项目编码
     * @return
     */
    @Select("<script>" +
            "select a.*,b.task_type_name as taskTypeName " +
            "from tms_task_info a,tms_task_type b " +
            "where a.task_type = b.task_type_encode " +
            "<if test='taskType!=null and taskType!=\"\"'>" +
                "and a.task_type = #{taskType} " +
            "</if>" +
            "<if test='responsible!=null and responsible!=\"\"'>" +
                "and a.responsible = #{responsible} " +
            "</if>" +
            "<if test='projectCode!=null and projectCode!=\"\"'>" +
                "and a.project_code = #{projectCode} " +
            "</if>" +
            "</script>")
    List<TaskInfoEntity> queryByCondition(Page page,Long taskType,String responsible, String projectCode);

}
