package com.pms.controller;

import com.pms.base.controller.BaseController;
import com.pms.service.ISysMenuService;
import com.pms.shiro.PMSSysUser;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SiteMeshController extends BaseController{

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 查询系统用户 侧边栏菜单
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "layouts")
    public String getIndex(Model model, HttpServletRequest request) {


        return "index";
    }

    @PostMapping(value = "layouts")
    public String postIndex(Model model, HttpServletRequest request) {
        return getIndex( model,  request);
    }
}
