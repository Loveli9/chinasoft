package com.pms.service;

import com.pms.base.vo.DataTable;
import com.pms.constants.ActivitiConstants;
import com.pms.dto.TaskDTO;
import com.pms.shiro.PMSSysUser;
import com.pms.utils.StringUtils;
import com.pms.utils.ExtUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
public class TaskService {

    @Autowired
    org.activiti.engine.TaskService actTaskService;

    @Autowired
    RepositoryService repositoryService;

    /**
     * 待办任务
     * @param title
     * @param category
     * @param page
     * @param pageSize
     * @return
     */
    public DataTable<Map> getTodoTasks(DataTable dt) {
        String userId = PMSSysUser.id() + "";
        TaskQuery taskQuery = actTaskService.createTaskQuery().taskCandidateOrAssigned(userId)
                .active().includeProcessVariables().orderByTaskCreateTime().desc();
        String category = (String) dt.getSearchParams().get("category");
        if (StringUtils.isNotBlank(category)) {
            taskQuery.taskCategory(category);
        }
        String title = (String) dt.getSearchParams().get("title");
        if (StringUtils.isNotBlank(title)){
            taskQuery.processVariableValueLikeIgnoreCase("title", "%" + title + "%");
        }
        Map<String, List<TaskDTO>> collect = taskQuery.listPage((dt.getPageNumber() - 1) * dt.getPageSize(), dt.getPageSize())
                .stream()
                .map(task -> {
                    TaskDTO dto = getTaskDTO(task, task.getAssignee() == null ?
                            ActivitiConstants.TaskStatus.TASK_STATUS_CLAIM : ActivitiConstants.TaskStatus.TASK_STATUS_TODO);
                    dto.setTime(task.getCreateTime().getTime());
                    return dto;
                }).collect(Collectors.groupingBy(TaskDTO::getCategory));
        dt.setRows(Collections.singletonList(collect));
        return dt;
    }

    private TaskDTO getTaskDTO(TaskInfo task, String status) {
        ProcessDefinition processDefinition = getProcessDefinition(task.getProcessDefinitionId());
        String deploymentId = processDefinition.getDeploymentId();
        Deployment deployment = getDeployment(deploymentId);
        return ExtUtils.toTaskDTO(task, status, processDefinition, deployment);
    }

    private ProcessDefinition getProcessDefinition(String processDefinitionId) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    }

    private Deployment getDeployment(String deploymentId) {
        return repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
    }
}
