package com.pms.entity.baseInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：项目状态表
 * */
@Data
@TableName("tms_project_status")
public class ProjectStatusEntity {

    @TableField("status_id")
    private Long statusId;

    @TableField("project_status")
    private String projectStatus;

}
