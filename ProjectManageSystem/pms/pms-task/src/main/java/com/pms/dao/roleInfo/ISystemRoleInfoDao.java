package com.pms.dao.roleInfo;

import com.pms.base.dao.CrudDao;
import com.pms.entity.roleInfo.SystemRoleInfoEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface ISystemRoleInfoDao extends CrudDao<SystemRoleInfoEntity> {

    /**
     * @roleId 角色ID
     * @rolePermissions 角色权限
     * @isEnable 是否启用
     * @return boolean
     */
    @Update("update tms_system_role_info set role_permissions = #{rolePermissions},is_enable = #{isEnable} where role_id = #{roleId}")
    boolean updateByRoleId(Long roleId,String rolePermissions,Integer isEnable);

    /**
     * @roleId 角色ID
     * @return boolean
     */
    @Delete("delete from tms_system_role_info where role_id = #{roleId}")
    boolean deleteByRoleId(Long roleId);

}
