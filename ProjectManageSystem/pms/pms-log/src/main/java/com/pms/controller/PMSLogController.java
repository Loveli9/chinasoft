package com.pms.controller;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.PMSLog;
import com.pms.service.IPMSLogService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/monitor/log")
public class PMSLogController extends BaseController {

    @Autowired
    private IPMSLogService pmsLogService;


    @ApiOperation(value = "进入日志列表", notes = "进入日志列表")
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("url", request.getContextPath()+"/monitor/log/");
        return "log/list";
    }

    @ApiOperation(value = "获取日志列表数据", notes = "获取日志列表数据:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<PMSLog> list(@RequestBody DataTable dt, ServletRequest request) {
        return pmsLogService.pageSearch(dt);
    }

    @ApiOperation(value = "批量删除日志记录", notes = "批量删除日志记录传入日志ids")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = pmsLogService.removeByIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();

    }


}
