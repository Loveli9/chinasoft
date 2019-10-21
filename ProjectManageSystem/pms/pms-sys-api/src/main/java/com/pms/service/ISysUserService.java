package com.pms.service;

import com.pms.base.service.IBaseService;
import com.pms.base.vo.DataTable;
import com.pms.entity.SysUser;
import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

/**
 * <p>
 * Describe:
 */
public interface ISysUserService  extends IBaseService<SysUser> {


     SysUser getById(String id);



     int addUser(SysUser sysUser);


     SysUser login(String username, String password);

    /**
     * 登录
     *
     * @param username
     * @return
     */
     SysUser getByLoginName(String username);

    /**
     * 查询某个用户的所有信息
     *
     * @param id
     * @return
     */
     SysUser selectUserAllInfoById(Long id) ;

    /**
     * 分页查询用户
     *
     * @param searchParams
     * @param dt
     * @return
     */
//     DataTable<SysUser> PageSysUser(Map<String, Object> searchParams, DataTable<SysUser> dt) ;
    /**
     * 检测登录名是否重复
     *
     * @param loginName
     * @param id
     * @return
     */
     Boolean checkLoginName(String loginName, Long id) ;

    /**
     * 创建一个用户 或者更新一个用户
     *
     * @param sysUser
     * @param ids
     */
     void insertSysUser(SysUser sysUser, Long[] ids) ;

    /**
     * 更新用户
     *
     * @param sysUser
     * @param ids
     */
     void updateSysUser(SysUser sysUser, Long[] ids) ;
}
