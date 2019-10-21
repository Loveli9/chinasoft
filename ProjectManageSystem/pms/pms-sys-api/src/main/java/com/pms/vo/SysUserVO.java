package com.pms.vo;

import com.pms.entity.SysUser;

import java.util.List;

public class SysUserVO extends SysUser {


    private List<SysRoleVO> sysRoleVOs;

    public List<SysRoleVO> getSysRoleVOs() {
        return sysRoleVOs;
    }

    public void setSysRoleVOs(List<SysRoleVO> sysRoleVOs) {
        this.sysRoleVOs = sysRoleVOs;
    }
}
