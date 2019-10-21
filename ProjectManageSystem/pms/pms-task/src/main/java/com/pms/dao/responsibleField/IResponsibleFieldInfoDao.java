package com.pms.dao.responsibleField;

import com.pms.base.dao.CrudDao;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.entity.responsibleField.ResponsibleFieldInfoEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component("responsibleFieldInfoDao")
public interface IResponsibleFieldInfoDao extends CrudDao<ResponsibleFieldInfoEntity> {

    /**
     * @responsibleFieldCode 责任田编码
     * @isEnabled 是否启用
     * @remarks 责任田描述
     * @return boolean
     */
    @Update("update tms_responsible_field_info set is_enabled = #{isEnabled},remarks = #{remarks} where responsible_field_code = #{responsibleFieldCode}")
    boolean updateTmsResponsibleFieldInfo(String responsibleFieldCode, Integer isEnabled, String remarks);


    /**
     * @responsibleFieldCode 责任田编码
     * @return boolean
     */
    @Delete("delete from tms_responsible_field_info where responsible_field_code = #{responsibleFieldCode}")
    boolean deleteByResponsibleFieldCode(String responsibleFieldCode);

    /**
     * @return list
     */
    @Select("<script>" +
            "select * from tms_responsible_field_info " +
            "<if test='projectCode!=null and projectCode!=\"\"'>" +
            " where project_code = #{projectCode} " +
            "</if>" +
            "</script>")
    List<ResponsibleFieldInfoEntity> getViewField(Page page,String projectCode);

    /**
     * @return list
     */
    @Select("<script>" +
            "select * from tms_responsible_field_info " +
            "<if test='projectCode!=null and projectCode!=\"\"'>" +
            " where project_code = #{projectCode} " +
            "</if>" +
            "</script>")
    List<ResponsibleFieldInfoEntity> queryByCondition(Page page,String projectCode);

}
