package com.pms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cr_codechecklog")
public class CodeReviewEntity {

    /**
     * int(11) 编号
     */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * varchar(20) 项目编号
     */
    @TableField("projectid")
    private String projectId;

    //项目名称
    @TableField("projectname")
    private String projectName;

    /**
     * varchar(20) 任务编号
     */
    @TableField("taskid")
    private String taskId;
    /**
     * varchar(20) 缺陷编号
     */
    @TableField("bugid")
    private String bugId;
    /**
     * varchar(50) 缺陷类型
     */
    @TableField("bugtype")
    private String bugType;
    /**
     * varchar(50) 缺陷等级
     */
    @TableField("buglevel")
    private String bugLevel;
    /**
     * text 源文件路径
     */
    @TableField("sourcefile")
    private String sourceFile;
    /**
     * varchar(50) 开始行
     */
    @TableField("beginline")
    private String beginLine;
    /**
     * varchar(50) 结束行
     */
    @TableField("endline")
    private String endLine;
    /**
     * varchar(120) 版本信息
     */
    @TableField("version")
    private String version;
    /**
     * varchar(20) 监视人
     */
    @TableField("checkuser")
    private String checkUser;
    /**
     * varchar(20) 检视时间
     */
    @TableField("checktime")
    private String checkTime;
    /**
     * varchar(20) 修改人
     */
    @TableField("edituser")
    private String editUser;
    /**
     * text 修改建议
     */
    @TableField("editsuggest")
    private String editSuggest;
    /**
     * text 备注
     */
    @TableField("remark")
    private String remark;

    @Override
    public String toString() {
        return "CodeReviewEntity{" +
                "id=" + id +
                ", projectId='" + projectId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", bugId='" + bugId + '\'' +
                ", bugType='" + bugType + '\'' +
                ", bugLevel='" + bugLevel + '\'' +
                ", sourceFile='" + sourceFile + '\'' +
                ", beginLine='" + beginLine + '\'' +
                ", endLine='" + endLine + '\'' +
                ", version='" + version + '\'' +
                ", checkUser='" + checkUser + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", editUser='" + editUser + '\'' +
                ", editSuggest='" + editSuggest + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
