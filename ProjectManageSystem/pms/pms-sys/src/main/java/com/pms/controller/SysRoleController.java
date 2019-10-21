package com.pms.controller;

import com.alibaba.fastjson.JSON;
import com.pms.annotation.SLog;
import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.SysRole;
import com.pms.service.ISysMenuService;
import com.pms.service.ISysRoleService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by  on 2017/9/19.
 * <p>
 * Email 
 * <p>
 * Describe: 系统角色控制器
 */
@Controller
@RequestMapping(value = "/sys/role")
public class SysRoleController extends BaseController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysMenuService sysMenuService;



    @SLog("获角色列表")
    @ApiOperation(value = "获角色列表", notes = "获角色列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SysRole> list(@RequestBody DataTable dt, ServletRequest request) {
        return sysRoleService.pageSearch(dt);
    }



    @ApiOperation(value = "创建角色操作", notes = "创建角色操作")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@Valid SysRole sysRole, @RequestParam(value = "ids", defaultValue = "") Long[] menuIds) {
        sysRole.setId(null);
        sysRoleService.insertSysRole(sysRole, menuIds);
        return ReturnDTOUtil.success();
    }

    /**
     * 检测角色编码是否存在
     *
     * @param id
     * @param code
     * @return
     */
    @ApiOperation(value = "检测角色", notes = "检测角色编码是否存在")
    @GetMapping(value = "check/{id}")
    @ResponseBody
    public Boolean check(@PathVariable("id") Long id, @RequestParam("code") String code) {
        return sysRoleService.checkRoleCode(code, id);
    }




    @ApiOperation(value = "修改角色操作", notes = "修改角色操作")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@Valid SysRole sysRole, @RequestParam(value = "ids", defaultValue = "") Long[] menuIds) {
        sysRoleService.insertSysRole(sysRole, menuIds);
        return ReturnDTOUtil.success();
    }


}
