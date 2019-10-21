package com.pms.dao;


import com.pms.base.dao.CrudDao;
import com.pms.entity.SysRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by  on 2017/3/2.
 * <p>
 * Email 
 * <p>
 * Describe: 角色 dao接口
 */
@Component
public interface SysRoleDao extends CrudDao<SysRole> {


    /**
     * 获取某个用户的角色和菜单
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(Long userId);

}
