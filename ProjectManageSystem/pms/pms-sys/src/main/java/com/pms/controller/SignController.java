package com.pms.controller;

import com.pms.base.controller.BaseController;
import com.pms.service.ISysUserService;
import com.pms.shiro.PMSSysUser;
import com.pms.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by  on 2017/7/14.
 * <p>
 * Email 
 * <p>
 * Describe: 系统注册-登录-登出
 */
@Controller
public class SignController extends BaseController{

    @Autowired
    private ISysUserService sysUserService;


    /**
     *  空地址请求
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index(Model model, HttpServletRequest request) {
      return "home/index";
    }


    /**
     * index 界面
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "home/index";
    }




    /**
     * 检查是否登陆
     * @return
     */
    @GetMapping("/user/getLoginedUser")
    @ResponseBody
    public ShiroUser getLoginedUser(){
        return PMSSysUser.ShiroUser();
    }

     /**
     * 登录出错界面--实际的登录 交给了 shiro
     * @param request
     * @param userName
     * @param model
     * @return
     */
    @PostMapping(value = "/user/login")
    @ResponseBody
    public String fail(HttpServletRequest request,@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);

        String error=(String)request.getAttribute(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String errorMsg="";
        if(error!=null){
            if(error.contains("DisabledAccountException")){
                errorMsg="用户已被屏蔽,请登录其他用户.";
            }else if(error.contains("UnknownAccountException")){
                errorMsg="用户不存在,请检查后重试!";
            }else{
                errorMsg="登录失败，请重试.";
            }
        }
        return errorMsg;
    }



}
