package com.pms.controller.projectManage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.projectMember.ProjectMemberInfoEntity;
import com.pms.entity.userInfo.SystemUserInfoEntity;
import com.pms.service.svcInterface.projectMember.IProjectMemberInfoService;
import com.pms.service.svcInterface.userInfo.ISystemUserInfoService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/Member")
public class ProjectMemberInfoController extends BaseController {

    @Resource
    private IProjectMemberInfoService projectMemberInfoService;

    @Resource
    private ISystemUserInfoService systemUserInfoService;

    //新增条件查询
    @ApiOperation(value = "获取项目成员信息", notes = "获取项目成员信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<ProjectMemberInfoEntity> queryByPage(@RequestBody DataTable<ProjectMemberInfoEntity> dt) {
        Long pageNumber = Long.parseLong(dt.getPageNumber()+"");
        Long pageSize = Long.parseLong(dt.getPageSize()+"");
        DataTable<ProjectMemberInfoEntity> dataTable = new DataTable<ProjectMemberInfoEntity>();
        Page page = new Page<>(pageNumber,pageSize);
        Page<ProjectMemberInfoEntity> pageOut = projectMemberInfoService.queryByCondition(page,dt);
        List<ProjectMemberInfoEntity> outList = pageOut.getRecords();
        dataTable.setRows(outList);
        dataTable.setTotal(Integer.parseInt(pageOut.getTotal()+""));
        dataTable.setPageNumber(dt.getPageNumber());
        dataTable.setPageSize(dt.getPageSize());
        return dataTable;
    }

    @ApiOperation(value = "查询所有成员姓名", notes = "查询所有成员姓名")
    @PostMapping(value = "/queryAllMember")
    @ResponseBody
    public List<SystemUserInfoEntity> queryAllMember(@RequestBody SystemUserInfoEntity systemUserInfoEntity) {
        List<SystemUserInfoEntity> resultList = systemUserInfoService.queryAllMember(
                systemUserInfoEntity.getQueryConditionStr());
        return resultList;
    }

    @ApiOperation(value = "添加项目成员", notes = "添加项目成员")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@RequestBody ProjectMemberInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            projectMemberInfoService.save(entity);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            e.printStackTrace();
            dto = ReturnDTOUtil.fail();
            if(e instanceof DuplicateKeyException) {
                dto.setCode(10401);
                dto.setError("操作异常，主键冲突");
                dto.setMessage("该项目成员已存在");
            }
        }
        return dto;
    }

    @ApiOperation(value = "删除项目成员", notes = "删除项目成员")
    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("id") Integer id) {
        ReturnDTO dto = new ReturnDTO();
        try {
            projectMemberInfoService.removeById(id);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

}
