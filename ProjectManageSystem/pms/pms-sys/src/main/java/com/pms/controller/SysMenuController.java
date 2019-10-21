package com.pms.controller;

import com.alibaba.fastjson.JSON;
import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.entity.SysMenu;
import com.pms.enums.SysMenuType;
import com.pms.service.ISysMenuService;
import com.pms.shiro.PMSSysUser;
import com.pms.utils.ReturnDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * <p>
 * Describe: 系统菜单
 */
@Controller
@RequestMapping(value = "/sys/menu")
public class SysMenuController extends BaseController{


    @Autowired
    private ISysMenuService sysMenuService;


    /**
     * 获取菜单详情
     *
     * @param id 菜单ID
     */
    @GetMapping(value = "select/{id}")
    @ResponseBody
    public Map selectById(@PathVariable Long id) {
        Map map = new HashMap();
        map.put("menu", sysMenuService.getById(id));
        return map;
    }


    /**
     * 查询系统用户 侧边栏菜单
     */
    @GetMapping(value = "/usersidemenu")
    @ResponseBody
    public ReturnDTO selectUserSideMenu() {

        return ReturnDTOUtil.success(sysMenuService.caseMenu(PMSSysUser.id()));
    }

    /**
     * 保存资源信息
     *
     * @param sysMenu
     * @param redirectAttributes
     *
     * @return
     */
    @PostMapping(value="insert")
    @ResponseBody
    public ReturnDTO save(@Valid SysMenu sysMenu, RedirectAttributes redirectAttributes){
        if (ObjectUtils.isEmpty(sysMenu.getId())) {
            sysMenuService.add(sysMenu);
        }else {
            sysMenuService.update(sysMenu);
        }
        return  ReturnDTOUtil.success();
    }


    /**
     * 设置为不可用
     * @param id
     * @return
     */
    @PostMapping(value="disable/{id}")
    @ResponseBody
    public ReturnDTO disable(@PathVariable("id") Long id){
        sysMenuService.disableMenu(id);
        return ReturnDTOUtil.success();
    }


    /**
     * 删除菜单
     * @param id
     * @return
     */
    @DeleteMapping(value="delete/{id}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("id") Long id){
        sysMenuService.deleteMenu(id);
        return ReturnDTOUtil.success();
    }
}
