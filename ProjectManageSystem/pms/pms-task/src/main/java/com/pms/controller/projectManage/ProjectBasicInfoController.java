package com.pms.controller.projectManage;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.baseInfo.ProjectBasicInfoEntity;
import com.pms.entity.responsibleField.ResponsibleFieldInfoEntity;
import com.pms.entity.taskInfo.TaskInfoEntity;
import com.pms.service.svcInterface.baseInfo.IProjectBasicInfoService;
import com.pms.service.svcInterface.responsibleField.IResponsibleFieldInfoService;
import com.pms.service.svcInterface.taskInfo.ITaskInfoService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/ViewProject")
public class ProjectBasicInfoController extends BaseController {

    @Autowired
    private IProjectBasicInfoService projectBasicInfoService;

    @Resource
    private ITaskInfoService taskInfoService;

    @Autowired
    private IResponsibleFieldInfoService responsibleFieldInfoService;

    @ApiOperation(value = "获取项目基本信息", notes = "获取项目基本信息，获取责任田信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<ProjectBasicInfoEntity> queryByPage(@RequestBody DataTable<ProjectBasicInfoEntity> dt) {
        DataTable<ProjectBasicInfoEntity> dataTable = projectBasicInfoService.pageSearch(dt);
        return dataTable;
    }

    @ApiOperation(value = "查询所有的项目名称", notes = "查询所有的项目名称")
    @GetMapping(value = "/findAllProjectName")
    @ResponseBody
    public List<ProjectBasicInfoEntity> getAllProjectName(@RequestParam("queryConditionStr") String queryConditionStr) {
        List<ProjectBasicInfoEntity> resultList = projectBasicInfoService.getAllProjectName(queryConditionStr);
        return resultList;
    }

    @ApiOperation(value = "根据项目编码获取项目基本信息", notes = "根据项目编码获取项目基本信息，获取责任田信息:使用约定的DataTable")
    @GetMapping(value = "/qryDetail/{projectCode}")
    @ResponseBody
    public ProjectBasicInfoEntity queryProjectDetail(@PathVariable("projectCode") String projectCode) {
        ProjectBasicInfoEntity projectBasicInfoEntity = null;
        try {
            projectBasicInfoEntity = projectBasicInfoService.queryProjectDetail(projectCode);
            return projectBasicInfoEntity;
        } catch(Exception e) {
            projectBasicInfoEntity = new ProjectBasicInfoEntity();
        }
        return projectBasicInfoEntity;
    }

    @ApiOperation(value = "添加项目基本信息", notes = "添加项目基本信息")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@RequestBody ProjectBasicInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            projectBasicInfoService.save(entity);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            e.printStackTrace();
            dto = ReturnDTOUtil.fail();
            if(e instanceof DuplicateKeyException) {
                dto.setCode(10101);
                dto.setError("操作异常，主键冲突");
                dto.setMessage("项目编码已存在");
            }
        }
        return dto;
    }

    @ApiOperation(value = "修改项目基本信息", notes = "修改项目基本信息")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@RequestBody ProjectBasicInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            projectBasicInfoService.updateByProjectCode(entity);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

    @ApiOperation(value = "根据项目编码删除项目基本信息", notes = "根据项目编码删除项目基本信息")
    @GetMapping(value = "/delete/{projectCode}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("projectCode") String projectCode) {
        ReturnDTO dto = new ReturnDTO();
        try {
            projectBasicInfoService.deleteByProjectCode(projectCode);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

    @ApiOperation(value = "根据项目编码查询关联的任务列表信息", notes = "根据项目编码查询关联的任务列表信息")
    @PostMapping(value = "/findTasksByProjectCode")
    @ResponseBody
    public DataTable<TaskInfoEntity> getTasksByProjectCode(@RequestBody DataTable<TaskInfoEntity> dt) {
        DataTable<TaskInfoEntity> dataTable = taskInfoService.pageSearch(dt);
        return dataTable;
    }

    @ApiOperation(value = "根据项目编码查询关联的责任田信息", notes = "根据项目编码查询关联的责任田信息")
    @PostMapping(value = "/findViewFieldByProjectCode")
    @ResponseBody
    public DataTable<ResponsibleFieldInfoEntity> getViewFieldByProjectCode(@RequestBody DataTable<ResponsibleFieldInfoEntity> dt) {
        DataTable<ResponsibleFieldInfoEntity> dataTable = responsibleFieldInfoService.pageSearch(dt);
        return dataTable;
    }

}
