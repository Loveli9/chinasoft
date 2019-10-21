package com.pms.controller.projectManage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.iterativeInfo.ProjectIterationInfoEntity;
import com.pms.entity.taskInfo.TaskInfoEntity;
import com.pms.service.svcInterface.iterativeInfo.IProjectIterationInfoService;
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
@RequestMapping(value = "/ViewIteration")
public class ProjectIterationInfoController extends BaseController {

    @Autowired
    private IProjectIterationInfoService projectIterationInfoService;

    @Resource
    private ITaskInfoService taskInfoService;

    @ApiOperation(value = "获取项目迭代信息", notes = "获取项目迭代信息，获取责任田信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<ProjectIterationInfoEntity> queryByCondition(@RequestBody DataTable<ProjectIterationInfoEntity> dt) {
        Long pageNumber = Long.parseLong(dt.getPageNumber()+"");
        Long pageSize = Long.parseLong(dt.getPageSize()+"");
        DataTable<ProjectIterationInfoEntity> dataTable = new DataTable<ProjectIterationInfoEntity>();
        Page page = new Page<>(pageNumber,pageSize);
        Page<ProjectIterationInfoEntity> pageOut = projectIterationInfoService.queryByCondition(page, dt);
        List<ProjectIterationInfoEntity> outList = pageOut.getRecords();
        dataTable.setRows(outList);
        dataTable.setTotal(Integer.parseInt(pageOut.getTotal()+""));
        dataTable.setPageNumber(dt.getPageNumber());
        dataTable.setPageSize(dt.getPageSize());
        return dataTable;
    }

    @ApiOperation(value = "添加项目迭代信息", notes = "添加项目迭代信息")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@RequestBody ProjectIterationInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            projectIterationInfoService.save(entity);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            e.printStackTrace();
            dto = ReturnDTOUtil.fail();
            if(e instanceof DuplicateKeyException) {
                dto.setCode(10301);
                dto.setError("操作异常，主键冲突");
                dto.setMessage("该迭代信息已存在");
            }
        }
        return dto;
    }

    @ApiOperation(value = "根据迭代编码删除项目迭代信息", notes = "根据迭代编码删除项目迭代信息")
    @GetMapping(value = "/delete/{iterativeCode}")
    @ResponseBody
    public ReturnDTO deleteByIterativeCode(@PathVariable("iterativeCode") String iterativeCode) {
        ReturnDTO dto = new ReturnDTO();
        try {
            projectIterationInfoService.deleteByIterativeCode(iterativeCode);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

    /**
     * 查看任务列表
     * */
    @ApiOperation(value = "根据迭代编码查询关联的任务列表信息", notes = "根据迭代编码查询关联的任务列表信息")
    @GetMapping(value = "/findTasksByIterativeCode/{iterativeCode}/{responsible}/{pageNumber}/{pageSize}")
    @ResponseBody
    public DataTable<TaskInfoEntity> getTasksByIterativeCode(@PathVariable String iterativeCode,
                                                             @PathVariable String responsible,
                                                             @PathVariable Integer pageNumber,
                                                             @PathVariable Integer pageSize) {
        DataTable<TaskInfoEntity> dataTable = new DataTable<>();
        Page page = new Page<>(pageNumber,pageSize);
        Page<TaskInfoEntity> pageOut = taskInfoService.getTasksByIterativeCode(page,iterativeCode,responsible);
        List<TaskInfoEntity> outList = pageOut.getRecords();
        dataTable.setRows(outList);
        dataTable.setTotal(Integer.parseInt(pageOut.getTotal()+""));
        dataTable.setPageNumber(pageNumber);
        dataTable.setPageSize(pageSize);
        return dataTable;
    }

    /**
     * 查看我的任务
     * */
    @ApiOperation(value = "查询当前迭代责任人的任务信息", notes = "查询当前迭代责任人的任务信息")
    @PostMapping("/findMyTask")
    @ResponseBody
    public DataTable<TaskInfoEntity> queryMyTaskByLoginNo(@RequestBody DataTable<TaskInfoEntity> dt) {
        DataTable<TaskInfoEntity> dataTable = taskInfoService.pageSearch(dt);
        return dataTable;
    }

}
