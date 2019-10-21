package com.pms.controller.userManage;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.roleInfo.SystemRoleInfoEntity;
import com.pms.service.svcInterface.roleInfo.ISystemRoleInfoService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/UserManagement")
public class SystemRoleInfoController extends BaseController {

    @Resource
    private ISystemRoleInfoService systemRoleInfoService;

    @ApiOperation(value = "获取角色用户信息", notes = "获取角色用户信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SystemRoleInfoEntity> queryByPage(@RequestBody DataTable<SystemRoleInfoEntity> dt) {
        DataTable<SystemRoleInfoEntity> dataTable = systemRoleInfoService.pageSearch(dt);
        return dataTable;
    }

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@RequestBody SystemRoleInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            systemRoleInfoService.save(entity);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            e.printStackTrace();
            dto = ReturnDTOUtil.fail();
            if(e instanceof DuplicateKeyException) {
                dto.setCode(30101);
                dto.setError("操作异常，主键冲突");
                dto.setMessage("该用户信息已存在");
            }
        }
        return dto;
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@RequestBody SystemRoleInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            systemRoleInfoService.updateByRoleId(entity.getRoleId(),
                entity.getRolePermissions(),
                entity.getIsEnable());
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
                dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    @GetMapping(value = "/delete/{roleId}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("roleId") String roleId) {
        ReturnDTO dto = new ReturnDTO();
        try {
            systemRoleInfoService.deleteByRoleId(roleId);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

}
