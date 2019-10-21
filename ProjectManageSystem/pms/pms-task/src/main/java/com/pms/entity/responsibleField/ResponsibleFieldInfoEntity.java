package com.pms.entity.responsibleField;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description：责任田信息表
 * */
@Data
@TableName("tms_responsible_field_info")
public class ResponsibleFieldInfoEntity {

    @TableField("responsible_field_code")
    private String responsibleFieldCode;
    @TableField("project_code")
    private String projectCode;
    @TableField("iterative_code")
    private String iterativeCode;
    @TableField("responsible_field_name")
    private String responsibleFieldName;
    @TableField("remarks")
    private String remarks;
    @TableField("is_enabled")
    private Integer isEnabled;

    @TableField(exist = false)
    private List<ResponsibleFieldMemberInfoEntity> modifiedMemberList;

}
