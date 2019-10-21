package com.pms.controller.projectManage;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.baseInfo.ProjectBasicInfoEntity;
import com.pms.entity.iterativeInfo.ProjectIterationInfoEntity;
import com.pms.entity.responsibleField.ResponsibleFieldInfoEntity;
import com.pms.entity.responsibleField.ResponsibleFieldMemberInfoEntity;
import com.pms.entity.taskInfo.TaskInfoEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.service.svcInterface.baseInfo.IProjectBasicInfoService;
import com.pms.service.svcInterface.iterativeInfo.IProjectIterationInfoService;
import com.pms.service.svcInterface.responsibleField.IResponsibleFieldInfoService;
import com.pms.service.svcInterface.responsibleField.IResponsibleFieldMemberInfoService;
import com.pms.service.svcInterface.taskInfo.ITaskInfoService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ProjectInfo")
public class ProjectInfoController extends BaseController {

    @Autowired
    private IProjectBasicInfoService projectBasicInfoService;

    @Resource
    private ITaskInfoService taskInfoService;

    @Autowired
    private IResponsibleFieldInfoService responsibleFieldInfoService;

    @Autowired
    private IProjectIterationInfoService projectIterationInfoService;

    @Autowired
    private IResponsibleFieldMemberInfoService responsibleFieldMemberInfoService;

    @ApiOperation(value = "获取项目详情信息", notes = "获取项目详情信息，获取责任田信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<TaskInfoEntity> queryByPage(@RequestBody DataTable<TaskInfoEntity> dt) {
        Long pageNumber = Long.parseLong(dt.getPageNumber()+"");
        Long pageSize = Long.parseLong(dt.getPageSize()+"");
        DataTable<TaskInfoEntity> dataTable = new DataTable<TaskInfoEntity>();
        Page page = new Page<>(pageNumber,pageSize);
        Page<TaskInfoEntity> pageOut = taskInfoService.queryByCondition(page,dt);
        List<TaskInfoEntity> outList = pageOut.getRecords();
        dataTable.setRows(outList);
        dataTable.setTotal(Integer.parseInt(pageOut.getTotal()+""));
        dataTable.setPageNumber(dt.getPageNumber());
        dataTable.setPageSize(dt.getPageSize());
        return dataTable;
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
            dto = ReturnDTOUtil.fail();
            if(e instanceof DuplicateKeyException) {
                dto.setCode(10201);
                dto.setError("操作异常，主键冲突");
                dto.setMessage("项目基本信息已存在");
            }
        }
        return dto;
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

    @ApiOperation(value = "通过任务编码获取任务详细信息", notes = "通过任务编码获取任务详细信息")
    @GetMapping("/findDetail/{taskCode}")
    @ResponseBody
    public TaskInfoEntity queryTaskDetailByTaskCode(@PathVariable("taskCode") String taskCode) {
        Map<String,Object> inParam = new HashMap<>();
        inParam.put("task_code",taskCode);
        List<TaskInfoEntity> resultList = taskInfoService.getTaskDetailByMap(inParam);
        if(resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return new TaskInfoEntity();
    }

    @ApiOperation(value = "查询所有的项目名称", notes = "查询所有的项目名称")
    @GetMapping(value = "/findAllProjectName")
    @ResponseBody
    public List<ProjectBasicInfoEntity> getAllProjectName(@RequestParam("queryConditionStr") String queryConditionStr) {
        List<ProjectBasicInfoEntity> resultList = projectBasicInfoService.getAllProjectName(queryConditionStr);
        return resultList;
    }

    @ApiOperation(value = "根据项目编码查询迭代名称", notes = "根据项目编码查询迭代名称")
    @GetMapping(value = "/findIterativeName/{projectOwnership}")
    @ResponseBody
    public DataTable<ProjectIterationInfoEntity> findIterativeName(@PathVariable("projectOwnership") String projectOwnership) {
        DataTable<ProjectIterationInfoEntity> dataTable = new DataTable<ProjectIterationInfoEntity>();
        List<ProjectIterationInfoEntity> resultList = projectIterationInfoService.findIterativeName(projectOwnership);
        dataTable.setRows(resultList);
        return dataTable;
    }

    @ApiOperation(value = "查询关联的责任田信息", notes = "查询关联的责任田信息")
    @PostMapping(value = "/findViewFieldByProjectCode")
    @ResponseBody
    public DataTable<ResponsibleFieldInfoEntity> getViewFieldByProjectCode(@RequestBody DataTable<ResponsibleFieldInfoEntity> dt) {
        DataTable<ResponsibleFieldInfoEntity> dataTable = responsibleFieldInfoService.pageSearch(dt);
        return dataTable;
    }

    @ApiOperation(value = "查询当前用户关联的责任田信息", notes = "查询当前用户关联的责任田信息")
    @PostMapping(value = "/findViewFieldInfo")
    @ResponseBody
    public DataTable<ResponsibleFieldInfoEntity> getviewFieldInfo(@RequestBody DataTable<ResponsibleFieldInfoEntity> dt) {
        Long pageNumber = Long.parseLong(dt.getPageNumber()+"");
        Long pageSize = Long.parseLong(dt.getPageSize()+"");
        DataTable<ResponsibleFieldInfoEntity> dataTable = new DataTable<ResponsibleFieldInfoEntity>();
        Page page = new Page<>(pageNumber,pageSize);
        Page<ResponsibleFieldInfoEntity> pageOut = responsibleFieldInfoService.getViewField(page,dt);
        List<ResponsibleFieldInfoEntity> outList = pageOut.getRecords();
        dataTable.setRows(outList);
        dataTable.setTotal(Integer.parseInt(pageOut.getTotal()+""));
        dataTable.setPageNumber(dt.getPageNumber());
        dataTable.setPageSize(dt.getPageSize());
        return dataTable;
    }

    @ApiOperation(value = "根据责任田编码更新责任田信息", notes = "根据责任田编码更新责任田信息")
    @PostMapping(value = "/updateViewFieldInfo")
    @ResponseBody
    public ReturnDTO updateViewFieldInfo(@RequestBody ResponsibleFieldInfoEntity entity) {

        boolean success1 = responsibleFieldInfoService.updateTmsResponsibleFieldInfo(entity);
        if(success1 == false) {
            return ReturnDTOUtil.fail();
        }
        //修改后该责任田所有的田主+成员
        List<ResponsibleFieldMemberInfoEntity> modifiedMemberList = entity.getModifiedMemberList();
        //修改前该责任田所有的田主+成员
        List<ResponsibleFieldMemberInfoEntity> existMemberList = responsibleFieldMemberInfoService.
                queryAllMemberByResponsibleFieldCode(entity.getResponsibleFieldCode());
        //当前责任田成员工号列表
        List<String> existEmployeeNumberList = new ArrayList<>();
        List<String> modifiedEmployeeNumberList = new ArrayList<>();
        existMemberList.stream().forEach(x -> {
            existEmployeeNumberList.add(x.getEmployeeNumber());
        });
        modifiedMemberList.stream().forEach(x -> {
            modifiedEmployeeNumberList.add(x.getEmployeeNumber());
        });
        Map<Long,String> existMemberMap = new HashMap<>();
        existMemberList.stream().forEach(x -> {
            existMemberMap.put(x.getId(),x.getEmployeeNumber());
        });
        //existMemberMap
        for(ResponsibleFieldMemberInfoEntity modifiedmember : modifiedMemberList) {
            if(existMemberList.size() == 0) {
                //新增责任田成员
                boolean success5 = responsibleFieldMemberInfoService.insertTmsResponsibleFieldMemberInfo(modifiedmember);
                if(success5 == false) {
                    return ReturnDTOUtil.fail();
                }
            }
            for(ResponsibleFieldMemberInfoEntity existMember : existMemberList) {
                //如果修改后的成员工号在已有成员列表之内的工号，则可能修改责任田田主
                if(existMember.getEmployeeNumber().trim().equals(
                        modifiedmember.getEmployeeNumber().trim())) {
                    //成员责任田田主标识发生变化，更改该责任田成员的田主成员标识
                    if(!existMember.getIsResponsible().equals(modifiedmember.getIsResponsible())) {
                        boolean success2 = responsibleFieldMemberInfoService.updateTmsResponsibleFieldMemberInfo(modifiedmember);
                        if(success2 == false) {
                            return ReturnDTOUtil.fail();
                        }
                    }
                }
                //如果修改后该责任田成员工号不在已有责任田成员工号列表之内，说明一定是新增该责任田成员
                if(!existEmployeeNumberList.contains(modifiedmember.getEmployeeNumber())) {
                    //添加责任田成员
                    boolean success3 = responsibleFieldMemberInfoService.insertTmsResponsibleFieldMemberInfo(modifiedmember);
                    if(success3 == false) {
                        return ReturnDTOUtil.fail();
                    }
                    break;
                }
                //如果已有责任田成员工号不在修改后该责任田成员工号列表之内，说明一定是删除该责任田成员
                if(!modifiedEmployeeNumberList.contains(existMember.getEmployeeNumber())) {
                    //没有添加责任田成员标识，则是删除该责任田成员
                    boolean success4 = responsibleFieldMemberInfoService.removeById(existMember.getId());
                    if(success4 == false) {
                        return ReturnDTOUtil.fail();
                    }
                    break;
                }
            }
        }
        return ReturnDTOUtil.success();

    }

}
