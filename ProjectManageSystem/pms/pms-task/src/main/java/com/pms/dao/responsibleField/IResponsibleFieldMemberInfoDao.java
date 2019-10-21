package com.pms.dao.responsibleField;

import com.pms.base.dao.CrudDao;
import com.pms.entity.responsibleField.ResponsibleFieldMemberInfoEntity;
import org.apache.ibatis.annotations.Insert;
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
@Component
public interface IResponsibleFieldMemberInfoDao extends CrudDao<ResponsibleFieldMemberInfoEntity> {


    /**
     * @responsibleFieldCode 责任田编码
     * @employeeNumber 员工工号
     * @memberName 员工姓名
     * @isResponsible 是否是责任人
     * @return boolean
     */
    @Update("update tms_responsible_field_member_info set is_responsible = #{isResponsible} where responsible_field_code = #{responsibleFieldCode} and employee_number = #{employeeNumber} and member_name = #{memberName}")
    boolean updateTmsResponsibleFieldMemberInfo(String responsibleFieldCode,String employeeNumber,String memberName,Integer isResponsible);

    /**
     * @responsibleFieldCode 责任田编码
     * @return list
     */
    @Select("select * from tms_responsible_field_member_info where responsible_field_code = #{responsibleFieldCode}")
    List<ResponsibleFieldMemberInfoEntity> queryAllMemberByResponsibleFieldCode(String responsibleFieldCode);


    /**
     * @responsibleFieldCode 责任田编码
     * @employeeNumber 员工工号
     * @memberName 员工姓名
     * @isResponsible 是否是责任人
     * @return boolean
     */
    @Insert("insert into tms_responsible_field_member_info (responsible_field_code,employee_number,member_name,is_responsible,is_reserve) value (#{responsibleFieldCode},#{employeeNumber},#{memberName},#{isResponsible},#{isReserve})")
    boolean insertTmsResponsibleFieldMemberInfo(String responsibleFieldCode,String employeeNumber,String memberName,Integer isResponsible,Integer isReserve);

}
