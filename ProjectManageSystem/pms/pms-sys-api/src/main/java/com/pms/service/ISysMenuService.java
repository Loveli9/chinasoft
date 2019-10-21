package com.pms.service;

import com.pms.base.service.IBaseService;
import com.pms.base.vo.JsTree;
import com.pms.entity.SysMenu;

import java.util.List;

/**
 *
 * @author chen
 * @date 2017/9/19
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface ISysMenuService extends IBaseService<SysMenu> {

    /**
     * 获取用户的 私人菜单
     * @param userId
     * @return
     */
     List<SysMenu> selectMenusByUserId(Long userId);

     List<SysMenu> caseMenu(Long userId);

    /**
     * 获取菜单树
     * @return
     */
     List<JsTree> getMenuTree();

    /**
     * 保存菜单
     * @param sysMenu
     */
     void add(SysMenu sysMenu);

    /**
     * 更新菜单
     * @param sysMenu
     */
    void update(SysMenu sysMenu);

    /**
     * 把菜单设置为失效
     * @param id
     */
    void disableMenu(Long id);

    /**
     * 删除菜单
     * @param id
     */
    Boolean deleteMenu(Long id);
}
