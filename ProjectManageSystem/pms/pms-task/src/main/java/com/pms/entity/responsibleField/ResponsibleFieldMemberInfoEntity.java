package com.pms.entity.responsibleField;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：责任田成员信息表
 * */
@Data
@TableName("tms_responsible_field_member_info")
public class ResponsibleFieldMemberInfoEntity {

    private Long id;
    @TableField("responsible_field_code")
    private String responsibleFieldCode;
    @TableField("employee_number")
    private String employeeNumber;
    @TableField("member_name")
    private String memberName;
    @TableField("is_responsible")
    private Integer isResponsible;
    @TableField("is_reserve")
    private Integer isReserve;

}
