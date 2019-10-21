package com.pms.controller;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.CodeReviewEntity;
import com.pms.enums.HttpCodeEnum;
import com.pms.service.ICodeReviewService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/codereview")
public class CodeReviewController extends BaseController {

    @Autowired
    private ICodeReviewService codeReviewService;

    @ApiOperation(value = "获取一条检视信息", notes = "获取检视对")
    @GetMapping(value = "/find/{id}")
    @ResponseBody
    public ReturnDTO queryById(@PathVariable("id") String id) {
        ReturnDTO dto= new ReturnDTO();

        try {
            CodeReviewEntity cre = codeReviewService.getById(Long.parseLong(id));
             dto.setMessage(cre);
             dto.setCode(HttpCodeEnum.OK.getCode());
        } catch (Exception e) {
            dto.setCode(HttpCodeEnum.FAIL.getCode());
            e.printStackTrace();
        }
        return dto;
    }

    @ApiOperation(value = "获取代码检视列表数据", notes = "获取代码检视列表数据:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<CodeReviewEntity> queryByPage(@RequestBody DataTable<CodeReviewEntity> dt) {
        DataTable<CodeReviewEntity> dataTable = codeReviewService.pageSearch(dt);
        return dataTable;
    }

    @ApiOperation(value = "创建代码检视", notes = "创建代码检视")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@RequestBody CodeReviewEntity entity) {
        boolean success=codeReviewService.save(entity);
        if(success){
            return ReturnDTOUtil.success();
        }else {
            return ReturnDTOUtil.fail();
        }
    }

    @ApiOperation(value = "更新代码检视", notes = "更新代码检视")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@RequestBody CodeReviewEntity entity) {
        boolean success=codeReviewService.updateById(entity);
        if(success){
            return ReturnDTOUtil.success();
        }else {
            return ReturnDTOUtil.fail();
        }
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("id") Integer id) {
        boolean success=codeReviewService.removeById(id);
        if(success){
            return ReturnDTOUtil.success();
        }else {
            return ReturnDTOUtil.fail();
        }
    }

}
