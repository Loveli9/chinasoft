package com.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pms.base.service.impl.BaseService;
import com.pms.base.vo.DataTable;
import com.pms.dao.SysUserOfficeDao;
import com.pms.entity.SysUser;
import com.pms.entity.SysUserOffice;
import com.pms.service.ISysUserOfficeService;
import com.pms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysUserOfficeService extends BaseService<SysUserOfficeDao, SysUserOffice> implements ISysUserOfficeService {

    @Autowired
    private ISysUserOfficeService sysUserOfficeService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 获取组织的用户列表
     *
     * @param dt
     * @return
     */
    @Override
    public DataTable<SysUser> userList(DataTable dt) {
        List<SysUser> sysUsers = new ArrayList<>();
        DataTable<SysUser> sysUserDataTable= new DataTable<>();
        DataTable<SysUserOffice> userOffices = sysUserOfficeService.pageSearch(dt);
        if (!CollectionUtils.isEmpty(userOffices.getRows())) {
            List<Long> userIds = userOffices.getRows().stream().parallel()
                    .map(SysUserOffice::getSysUserId)
                    .collect(Collectors.toList());
            sysUsers = sysUserService.list(new QueryWrapper<SysUser>().lambda().in(SysUser::getId, userIds));
        }
        sysUserDataTable.setRows(sysUsers);
        sysUserDataTable.setTotal(userOffices.getTotal());
        sysUserDataTable.setPageNumber(userOffices.getPageNumber());
        sysUserDataTable.setPageSize(userOffices.getPageSize());
        return sysUserDataTable;
    }


    /**
     * 移除组织中的人
     *
     * @param officeId
     * @param userIds
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeUsers(Long officeId, Long[] userIds) {
        remove(new QueryWrapper<SysUserOffice>().lambda().eq(SysUserOffice::getSysOfficeId, officeId).in(SysUserOffice::getSysUserId, userIds));
    }

    /**
     * 向组织中添加用户
     *
     * @param officeId
     * @param userIds
     * @param major
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUsers(Long officeId, Long[] userIds, String major) {
        List<SysUserOffice> sysUserOffices = Arrays.stream(userIds).parallel().map(userId -> {
                    SysUserOffice sys = new SysUserOffice();
                    sys.setMajor(major);
                    sys.setSysUserId(userId);
                    sys.setSysOfficeId(officeId);
                    return sys;
                }
        ).collect(Collectors.toList());
        saveBatch(sysUserOffices);
    }
}
