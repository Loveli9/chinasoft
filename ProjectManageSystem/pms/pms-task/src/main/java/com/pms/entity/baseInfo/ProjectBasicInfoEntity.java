package com.pms.entity.baseInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：项目基本信息表
 * */
@Data
@TableName("tms_project_basic_info")
public class ProjectBasicInfoEntity {

    @TableField("project_code")
    private String projectCode;
    @TableField("project_name")
    private String projectName;
    @TableField("project_description")
    private String projectDescription;
    @TableField("project_type")
    private Long projectType;
    @TableField(exist = false)
    private String projectTypeLinked;
    @TableField("business_type")
    private Long businessType;
    @TableField(exist = false)
    private String businessTypeLinked;
    @TableField("business_group")
    private String businessGroup;
    @TableField("business_line")
    private String businessLine;
    @TableField("business_department")
    private String businessDepartment;
    @TableField("delivery_department")
    private String deliveryDepartment;
    @TableField("attribution_area")
    private String attributionArea;
    @TableField(exist = false)
    private String attributionAreaLinked;
    @TableField("project_creation_time")
    private String projectCreationTime;
    @TableField("founder")
    private String founder;
    @TableField("project_start_time")
    private String projectStartTime;
    @TableField("project_plan_end_time")
    private String projectPlanEndTime;
    @TableField("actual_project_start_time")
    private String actualProjectStartTime;
    @TableField("actual_project_end_time")
    private String actualProjectEndTime;
    @TableField("project_status")
    private Long projectStatus;
    @TableField(exist = false)
    private String projectStatusLinked;
    @TableField("delivery_manager")
    private String deliveryManager;
    @TableField("spm")
    private String spm;
    @TableField("pm")
    private String pm;
    @TableField("qa")
    private String qa;
    @TableField("customer_interface_person")
    private String customerInterfacePerson;

}
