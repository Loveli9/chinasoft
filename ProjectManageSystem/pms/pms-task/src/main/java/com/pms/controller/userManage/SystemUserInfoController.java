package com.pms.controller.userManage;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.userInfo.SystemUserInfoEntity;
import com.pms.service.svcInterface.userInfo.ISystemUserInfoService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/UserManage")
public class SystemUserInfoController extends BaseController {

    @Resource
    private ISystemUserInfoService systemUserInfoService;

    @ApiOperation(value = "用户登录", notes = "用户登录:使用约定的DataTable")
    @PostMapping(value = "/login")
    @ResponseBody
    public ReturnDTO login(@RequestBody SystemUserInfoEntity entity) {
        List<SystemUserInfoEntity> outList =  systemUserInfoService.login(entity);
        int counts = (outList != null ? outList.size() : 0);
        if(counts == 1){
            return ReturnDTOUtil.success();
        } else {
            return ReturnDTOUtil.fail();
        }
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SystemUserInfoEntity> queryByPage(@RequestBody DataTable<SystemUserInfoEntity> dt) {
        DataTable<SystemUserInfoEntity> dataTable = systemUserInfoService.pageSearch(dt);
        return dataTable;
    }

}
