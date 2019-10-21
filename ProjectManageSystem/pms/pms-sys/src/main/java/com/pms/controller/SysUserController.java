package com.pms.controller;

import com.alibaba.fastjson.JSON;
import com.pms.annotation.SLog;
import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.SysUser;
import com.pms.enums.HttpCodeEnum;
import com.pms.exception.PMSException;
import com.pms.service.ISysRoleService;
import com.pms.service.ISysUserService;
import com.pms.shiro.PMSSysUser;
import com.pms.utils.FileUtils;
import com.pms.utils.PasswordUtils;
import com.pms.utils.ReturnDTOUtil;
import com.pms.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 2017/8/1.
 * <p>
 * Email 
 * <p>
 * Describe:  系统用户
 */
@Controller
@RequestMapping(value = "/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;


    /**
     * 导出 exccel
     * * @param dt
     *
     * @throws Exception
     */
    @GetMapping(value = "/exportUserList")
    @ResponseBody
    @ApiOperation(value = "导出用户", notes = "批量导出用户")
    public void exportCollectCustomerList(HttpServletResponse response) throws Exception {


        List<SysUser> users = sysUserService.list();

        //导出数据
        String excelTitle = "用户列表";
        String[] headerTitle = new String[]{"id", "登陆名", "姓名", "邮箱", "工号", "电话", "手机", "描述", "状态"};
        List<String[]> arrayList = new ArrayList<>();
        arrayList.add(headerTitle); //列头
        if (null != users && users.size() > 0) {
            for (SysUser sysUser : users) {
                arrayList.add(
                        new String[]{
                                sysUser.getId().toString(),
                                sysUser.getLoginName(),
                                sysUser.getName(),
                                sysUser.getEmail(),
                                sysUser.getNo(),
                                sysUser.getPhone(),
                                sysUser.getMobile(),
                                sysUser.getRemark(),
                                "Y".equals(sysUser.getLoginFlag()) ? "正常" : "禁用"
                        });
            }
        }
        ExceptInfo(response, excelTitle, arrayList);
    }


    @ApiOperation(value = "删除头像", notes = "删除头像")
    @PostMapping(value = "/delete/photo")
    public ReturnDTO deletePhoto(@RequestParam("name") String name) {

        String defaluePhoto = "/img/log9.png";
        if (defaluePhoto.equals(name)) {
            logger.info("默认头像不可删除！");
            return ReturnDTOUtil.custom(HttpCodeEnum.DELETE_DEFAULT_PHOTO_ERR);
        }
        Long userId = PMSSysUser.id();
        SysUser sysUser = sysUserService.getById(userId);
        if (ObjectUtils.isEmpty(sysUser)) {
            return ReturnDTOUtil.notFound();
        }
        sysUser.setPhoto(defaluePhoto);
        sysUserService.updateById(sysUser);
        //删除文件
        FileUtils.deleteFile(name);
        return ReturnDTOUtil.success();
    }


    /**
     * 对用户分页显示
     *
     * @param dt
     * @param request
     * @return
     */
    @SLog("获取用户列表数据")
    @ApiOperation(value = "获取用户列表数据", notes = "获取用户列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SysUser> list(@RequestBody DataTable dt, ServletRequest request) {
        return sysUserService.pageSearch(dt);
    }






    @ApiOperation(value = "创建用户操作", notes = "创建用户操作")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@Valid SysUser sysUser, @RequestParam(value = "ids", defaultValue = "") Long[] roleIds) {
        sysUser.setId(null);
        sysUserService.insertSysUser(sysUser, roleIds);

        return ReturnDTOUtil.success();
    }


    @ApiOperation(value = "更新用户", notes = "更新用户")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@Valid SysUser sysUser, @RequestParam(value = "ids", defaultValue = "") Long[] roleIds) {

        SysUser sysUserDb = sysUserService.getById(sysUser.getId());
        if (ObjectUtils.isEmpty(sysUserDb)) {
            throw new PMSException(HttpCodeEnum.NOT_FOUND);
        }

        sysUser.setId(sysUserDb.getId());
        sysUser.setCreateDate(sysUserDb.getCreateDate());
        sysUser.setCreateId(sysUserDb.getCreateId());
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            sysUser.setPassword(PasswordUtils.entryptPassword(sysUser.getPassword()));
        } else {
            sysUser.setPassword(sysUserDb.getPassword());
        }
        sysUserService.updateSysUser(sysUser, roleIds);
        return ReturnDTOUtil.success();
    }

    /**
     * 检查用户名是否存在
     *
     * @param loginName
     * @return
     */
    @GetMapping(value = "check/{id}")
    @ResponseBody
    public Boolean check(@PathVariable("id") Long id, @RequestParam("loginName") String loginName) {
        return sysUserService.checkLoginName(loginName, id);
    }


    /**
     * 查询系统用户 侧边栏菜单
     */
    @GetMapping(value = "/userinfoall")
    @ResponseBody
    public ReturnDTO selectUserSideMenu() {

        return ReturnDTOUtil.success(sysUserService.selectUserAllInfoById(PMSSysUser.id()));
    }

    @SLog("批量删除用户")
    @ApiOperation(value = "批量删除用户", notes = "批量删除用户")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = sysUserService.removeByIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();

    }


}
