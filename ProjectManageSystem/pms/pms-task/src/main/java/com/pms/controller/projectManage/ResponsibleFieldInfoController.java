package com.pms.controller.projectManage;

import com.pms.base.controller.BaseController;
import com.pms.base.entity.ReturnDTO;
import com.pms.base.vo.DataTable;
import com.pms.entity.responsibleField.ResponsibleFieldInfoEntity;
import com.pms.entity.responsibleField.ResponsibleFieldMemberInfoEntity;
import com.pms.service.svcInterface.responsibleField.IResponsibleFieldInfoService;
import com.pms.service.svcInterface.responsibleField.IResponsibleFieldMemberInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.service.svcInterface.userInfo.ISystemUserInfoService;
import com.pms.utils.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ViewField")
public class ResponsibleFieldInfoController extends BaseController {

    @Autowired
    private IResponsibleFieldInfoService responsibleFieldInfoService;

    @Autowired
    private IResponsibleFieldMemberInfoService responsibleFieldMemberInfoService;

    @Resource
    private ISystemUserInfoService systemUserInfoService;

    @ApiOperation(value = "获取责任田信息", notes = "根据查询条件，获取责任田信息:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<ResponsibleFieldInfoEntity> queryByPage(@RequestBody DataTable<ResponsibleFieldInfoEntity> dt) {
        Long pageNumber = Long.parseLong(dt.getPageNumber()+"");
        Long pageSize = Long.parseLong(dt.getPageSize()+"");
        DataTable<ResponsibleFieldInfoEntity> dataTable = new DataTable<ResponsibleFieldInfoEntity>();
        Page page = new Page<>(pageNumber,pageSize);
        Page<ResponsibleFieldInfoEntity> pageOut = responsibleFieldInfoService.queryByCondition(page,dt);
        List<ResponsibleFieldInfoEntity> outList = pageOut.getRecords();
        dataTable.setRows(outList);
        dataTable.setTotal(Integer.parseInt(pageOut.getTotal()+""));
        dataTable.setPageNumber(dt.getPageNumber());
        dataTable.setPageSize(dt.getPageSize());
        return dataTable;
    }

    @ApiOperation(value = "获取成员工号和姓名信息", notes = "获取成员工号和姓名信息:使用约定的DataTable")
    @PostMapping(value = "/findUserLoginNoAndName")
    @ResponseBody
    public DataTable<ResponsibleFieldInfoEntity> queryUserLoginNoAndName(@RequestBody DataTable<ResponsibleFieldInfoEntity> dt) {
        Long pageNumber = Long.parseLong(dt.getPageNumber()+"");
        Long pageSize = Long.parseLong(dt.getPageSize()+"");
        DataTable<ResponsibleFieldInfoEntity> dataTable = new DataTable<ResponsibleFieldInfoEntity>();
        Page page = new Page<>(pageNumber,pageSize);
        Page<ResponsibleFieldInfoEntity> pageOut = responsibleFieldInfoService.queryByCondition(page,dt);
        List<ResponsibleFieldInfoEntity> outList = pageOut.getRecords();
        dataTable.setRows(outList);
        dataTable.setTotal(Integer.parseInt(pageOut.getTotal()+""));
        dataTable.setPageNumber(dt.getPageNumber());
        dataTable.setPageSize(dt.getPageSize());
        return dataTable;

    }

    @ApiOperation(value = "添加责任田", notes = "添加责任田")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@RequestBody ResponsibleFieldInfoEntity entity) {
        ReturnDTO dto = new ReturnDTO();
        try {
            responsibleFieldInfoService.save(entity);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            e.printStackTrace();
            dto = ReturnDTOUtil.fail();
            if(e instanceof DuplicateKeyException) {
                dto.setCode(10501);
                dto.setError("操作异常，主键冲突");
                dto.setMessage("该责任田已存在");
            }
        }
        return dto;
    }

    @ApiOperation(value = "更新责任田信息", notes = "更新责任田信息")
    @PostMapping(value = "/update")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO update(@RequestBody ResponsibleFieldInfoEntity entity) {

        boolean success1 = responsibleFieldInfoService.updateTmsResponsibleFieldInfo(entity);
        if(success1 == false) {
            return ReturnDTOUtil.fail();
        }
        //修改后该责任田所有的田主+成员
        List<ResponsibleFieldMemberInfoEntity> modifiedMemberList = entity.getModifiedMemberList();
        //修改前该责任田所有的田主+成员
        List<ResponsibleFieldMemberInfoEntity> existMemberList = responsibleFieldMemberInfoService.
                queryAllMemberByResponsibleFieldCode(entity.getResponsibleFieldCode());
        //当前责任田成员工号列表
        List<String> existEmployeeNumberList = new ArrayList<>();
        List<String> modifiedEmployeeNumberList = new ArrayList<>();
        existMemberList.stream().forEach(x -> {
            existEmployeeNumberList.add(x.getEmployeeNumber());
        });
        modifiedMemberList.stream().forEach(x -> {
            modifiedEmployeeNumberList.add(x.getEmployeeNumber());
        });
        Map<Long,String> existMemberMap = new HashMap<>();
        existMemberList.stream().forEach(x -> {
            existMemberMap.put(x.getId(),x.getEmployeeNumber());
        });
        //existMemberMap
        for(ResponsibleFieldMemberInfoEntity modifiedmember : modifiedMemberList) {
            if(existMemberList.size() == 0) {
                //新增责任田成员
                boolean success5 = responsibleFieldMemberInfoService.insertTmsResponsibleFieldMemberInfo(modifiedmember);
                if(success5 == false) {
                    return ReturnDTOUtil.fail();
                }
            }
            for(ResponsibleFieldMemberInfoEntity existMember : existMemberList) {
                //如果修改后的成员工号在已有成员列表之内的工号，则可能修改责任田田主
                if(existMember.getEmployeeNumber().trim().equals(
                        modifiedmember.getEmployeeNumber().trim())) {
                    //成员责任田田主标识发生变化，更改该责任田成员的田主成员标识
                    if(!existMember.getIsResponsible().equals(modifiedmember.getIsResponsible())) {
                        boolean success2 = responsibleFieldMemberInfoService.updateTmsResponsibleFieldMemberInfo(modifiedmember);
                        if(success2 == false) {
                            return ReturnDTOUtil.fail();
                        }
                    }
                }
                //如果修改后该责任田成员工号不在已有责任田成员工号列表之内，说明一定是新增该责任田成员
                if(!existEmployeeNumberList.contains(modifiedmember.getEmployeeNumber())) {
                    //添加责任田成员
                    boolean success3 = responsibleFieldMemberInfoService.insertTmsResponsibleFieldMemberInfo(modifiedmember);
                    if(success3 == false) {
                        return ReturnDTOUtil.fail();
                    }
                    break;
                }
                //如果已有责任田成员工号不在修改后该责任田成员工号列表之内，说明一定是删除该责任田成员
                if(!modifiedEmployeeNumberList.contains(existMember.getEmployeeNumber())) {
                    //没有添加责任田成员标识，则是删除该责任田成员
                    boolean success4 = responsibleFieldMemberInfoService.removeById(existMember.getId());
                    if(success4 == false) {
                        return ReturnDTOUtil.fail();
                    }
                    break;
                }
            }
        }
        return ReturnDTOUtil.success();
    }

    @ApiOperation(value = "根据责任田编码删除责任田信息", notes = "根据责任田编码删除责任田信息")
    @GetMapping(value = "/delete/{responsibleFieldCode}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("responsibleFieldCode") String responsibleFieldCode) {
        ReturnDTO dto = new ReturnDTO();
        try {
            responsibleFieldInfoService.deleteByResponsibleFieldCode(responsibleFieldCode);
            dto = ReturnDTOUtil.success();
        } catch(Exception e) {
            dto = ReturnDTOUtil.fail();
        }
        return dto;
    }

}
