package com.pms.controller.taskManage;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.taskInfo.TaskInfoEntity;
import com.pms.service.svcInterface.taskInfo.ITaskInfoService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ViewTask")
public class TaskInfoController extends BaseController {

    @Resource
    private ITaskInfoService taskInfoService;

    @ApiOperation(value = "获取任务信息", notes = "获取任务信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<TaskInfoEntity> queryByPage(@RequestBody DataTable<TaskInfoEntity> dt) {
        DataTable<TaskInfoEntity> dataTable = taskInfoService.pageSearch(dt);
        return dataTable;
    }

    @ApiOperation(value = "查询所有任务名称", notes = "查询所有任务名称")
    @GetMapping(value = "/findAllTaskName")
    @ResponseBody
    public List<TaskInfoEntity> getAllTaskName(@RequestParam("queryConditionStr") String queryConditionStr) {
        List<TaskInfoEntity> resultList = taskInfoService.getAllTaskName(queryConditionStr);
        return resultList;
    }

    @ApiOperation(value = "通过任务编码获取任务详细信息", notes = "通过任务编码获取任务详细信息")
    @GetMapping("/findDetail/{taskCode}")
    @ResponseBody
    public TaskInfoEntity queryTaskDetailByTaskCode(@PathVariable("taskCode") String taskCode) {
        Map<String,Object> inMap = new HashMap<>();
        inMap.put("task_code",taskCode);
        List<TaskInfoEntity> outList = taskInfoService.getTaskDetailByMap(inMap);
        if(outList != null && outList.size() > 0) {
            return outList.get(0);
        }
        return new TaskInfoEntity();
    }

    @ApiOperation(value = "添加任务信息", notes = "添加任务信息")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@RequestBody TaskInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            taskInfoService.save(entity);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            e.printStackTrace();
            dto = ReturnDTOUtil.fail();
            if(e instanceof DuplicateKeyException) {
                dto.setCode(20101);
                dto.setError("操作异常，主键冲突");
                dto.setMessage("该任务信息已存在");
            }
        }
        return dto;
    }

    @ApiOperation(value = "删除任务信息", notes = "删除任务信息")
    @GetMapping(value = "/delete/{taskCode}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("taskCode") String taskCode) {
        ReturnDTO dto = new ReturnDTO();
        try {
            taskInfoService.deleteTaskInfoByTaskCode(taskCode);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

}
