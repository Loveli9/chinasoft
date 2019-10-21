package com.pms.dao.userInfo;

import com.pms.base.dao.CrudDao;
import com.pms.entity.baseInfo.ProjectBasicInfoEntity;
import com.pms.entity.userInfo.SystemUserInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface ISystemUserInfoDao extends CrudDao<SystemUserInfoEntity> {

    /**
     * @domainAccount 中软工号
     * @password 密码
     * @accountExpirationTime 账号失效时间
     * @passwordExpirationTime 密码失效时间
     * @return boolean
     */
    @Select("select * from tms_system_user_info where login_no = #{loginNo}" +
            " and `password` = #{password}" +
            " and current_date <= date_format(account_expiration_time, \'%Y-%m-%d\') " +
            " and current_date <= date_format(password_expiration_time, \'%Y-%m-%d\')")
    List<SystemUserInfoEntity> login(String loginNo, String password);

    /**
     * @queryConditionStr 模糊查询条件
     * @return list
     */
    @Select("<script>" +
            "select login_no,domain_account,name " +
            "from tms_system_user_info " +
            "<if test='queryConditionStr!=null and queryConditionStr!=\"\"'>" +
                "where login_no like CONCAT('%', #{queryConditionStr ,jdbcType=VARCHAR}, '%') " +
                "or domain_account like CONCAT('%', #{queryConditionStr ,jdbcType=VARCHAR}, '%')" +
                "or name like CONCAT('%', #{queryConditionStr ,jdbcType=VARCHAR}, '%')" +
            "</if>" +
            "</script>")
    List<SystemUserInfoEntity> queryAllMember(@Param("queryConditionStr") String queryConditionStr);

}
