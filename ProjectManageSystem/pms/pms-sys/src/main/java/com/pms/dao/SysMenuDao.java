package com.pms.dao;


import com.pms.base.dao.TreeDao;
import com.pms.entity.SysMenu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by  on 2017/3/13.
 * <p>
 * Email 
 * <p>
 * Describe: 菜单dao
 */
public interface SysMenuDao extends TreeDao<SysMenu> {
    /**
     * 查询 用户的所有菜单
     *
     * @param userId
     * @return
     */
    List<SysMenu> selectMenusByUserId(Long userId);
}
