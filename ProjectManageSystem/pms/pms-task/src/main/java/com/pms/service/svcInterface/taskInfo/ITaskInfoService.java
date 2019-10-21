package com.pms.service.svcInterface.taskInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.IBaseService;
import com.pms.base.vo.DataTable;
import com.pms.entity.taskInfo.TaskInfoEntity;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface ITaskInfoService extends IBaseService<TaskInfoEntity> {

    List<TaskInfoEntity> getTaskDetailByMap(Map<String,Object> inMap);

    List<TaskInfoEntity> getAllTaskName(String queryConditionStr);

    Page<TaskInfoEntity> getTasksByIterativeCode(Page<TaskInfoEntity> page,String iterativeCode,String responsible);

    boolean deleteTaskInfoByTaskCode(String taskCode);

    Page<TaskInfoEntity> queryByCondition(Page page, DataTable<TaskInfoEntity> dt);

}
