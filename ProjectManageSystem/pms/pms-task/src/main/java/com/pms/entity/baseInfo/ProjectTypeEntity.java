package com.pms.entity.baseInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：项目类型表
 * */
@Data
@TableName("tms_project_type")
public class ProjectTypeEntity {

    @TableField("id")
    private Long id;

    @TableField("project_type")
    private String projectType;

}
