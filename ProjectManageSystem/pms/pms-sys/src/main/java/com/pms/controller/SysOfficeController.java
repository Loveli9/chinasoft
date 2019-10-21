package com.pms.controller;

import com.alibaba.fastjson.JSON;
import com.pms.annotation.SLog;
import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.SysOffice;
import com.pms.entity.SysUser;
import com.pms.service.ISysOfficeService;
import com.pms.service.ISysUserOfficeService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/sys/office")
public class SysOfficeController extends BaseController {


    @Autowired
    private ISysOfficeService sysOfficeService;

    @Autowired
    private ISysUserOfficeService sysUserOfficeService;


    @SLog("获取组织用户列表数据")
    @ApiOperation(value = "获取组织用户列表数据", notes = "获取组织用户列表数据:使用约定的DataTable")
    @PostMapping(value = "/user/list")
    @ResponseBody
    public DataTable<SysUser> list(@RequestBody DataTable dt, ServletRequest request) {
        return sysUserOfficeService.userList(dt);
    }

    /**
     * 批量添加用户到 office
     *
     * @param officeId
     * @param userIds
     */
    @PostMapping("/addUsersToOffice")
    @ResponseBody
    @ApiOperation(value = "批量添加用户", notes = "批量添加")
    public ReturnDTO addUsersToOffice(@RequestParam(value = "officeId") Long officeId,
                                 @RequestParam(value = "userIds") Long[] userIds,
                                 @RequestParam(value = "major") String major) {

        sysUserOfficeService.addUsers(officeId, userIds, major);
        return ReturnDTOUtil.success();

    }


    /**
     * 移除组织的用户
     *
     * @param officeId
     * @param userIds
     */
    @PostMapping("/delUsersFromOffice")
    @ResponseBody
    public ReturnDTO delUsersFromOffice(@RequestParam(value = "officeId") Long officeId,
                                        @RequestParam(value = "userIds") Long[] userIds) {

        sysUserOfficeService.removeUsers(officeId, userIds);
        return ReturnDTOUtil.success();
    }





    /**
     * 获取机构详情
     *
     * @param id 机构ID
     */
    @GetMapping(value = "select/{id}")
    @ResponseBody
    public Map selectById(@PathVariable Long id) {
        Map map = new HashMap();
        map.put("office", sysOfficeService.getById(id));
        return map;
    }


    /**
     * 保存资源信息
     *
     * @param sysOffice
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "insert")
    @ResponseBody
    public ReturnDTO save(@Valid SysOffice sysOffice, RedirectAttributes redirectAttributes) {
        if (ObjectUtils.isEmpty(sysOffice.getId())) {
            sysOfficeService.add(sysOffice);
        } else {
            sysOfficeService.update(sysOffice);
        }
        return ReturnDTOUtil.success();
    }


}
