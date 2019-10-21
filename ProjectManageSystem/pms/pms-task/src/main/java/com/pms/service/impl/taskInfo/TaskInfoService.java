package com.pms.service.impl.taskInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.impl.BaseService;
import com.pms.base.vo.DataTable;
import com.pms.dao.taskInfo.ITaskInfoDao;
import com.pms.entity.taskInfo.TaskInfoEntity;
import com.pms.service.svcInterface.taskInfo.ITaskInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskInfoService extends BaseService<ITaskInfoDao, TaskInfoEntity> implements ITaskInfoService {

    @Resource
    private ITaskInfoDao taskInfoDao;

    @Override
    public List<TaskInfoEntity> getTaskDetailByMap(Map<String, Object> inMap) {
        List<TaskInfoEntity> resultMap = taskInfoDao.selectByMap(inMap);
        return resultMap;
    }

    @Override
    public List<TaskInfoEntity> getAllTaskName(String queryConditionStr) {
        return taskInfoDao.getAllTaskName(queryConditionStr);
    }

    @Override
    public Page<TaskInfoEntity> getTasksByIterativeCode(Page<TaskInfoEntity> page,String iterativeCode,String responsible){
        return page.setRecords(this.taskInfoDao.getTasksByIterativeCode(page,iterativeCode,responsible));
    }

    @Override
    public boolean deleteTaskInfoByTaskCode(String taskCode) {
        boolean result = this.taskInfoDao.deleteTaskInfoByTaskCode(taskCode);
        return result;
    }

    @Override
    public Page<TaskInfoEntity> queryByCondition(Page page, DataTable<TaskInfoEntity> dt) {
        String taskType = "";
        String responsible = "";
        String projectCode = "";
        if(dt.getSearchParams() != null) {
            taskType = (dt.getSearchParams().get("search_eq_task_type") == null) ?
                    "" : dt.getSearchParams().get("search_eq_task_type").toString();
        }
        if(dt.getSearchParams() != null) {
            responsible = (dt.getSearchParams().get("search_eq_responsible") == null) ?
                    "" : dt.getSearchParams().get("search_eq_responsible").toString();
        }
        if(dt.getSearchParams() != null) {
            projectCode = (dt.getSearchParams().get("search_eq_project_code") == null) ?
                    "" : dt.getSearchParams().get("search_eq_project_code").toString();
        }
        Long taskTypeLong = ((taskType == null || "".equals(taskType)) ? null : Long.parseLong(taskType));
        page.setRecords(this.taskInfoDao.queryByCondition( page,  taskTypeLong, responsible, projectCode));
        return page;
    }

}
