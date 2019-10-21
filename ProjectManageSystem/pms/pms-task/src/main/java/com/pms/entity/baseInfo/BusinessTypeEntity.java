package com.pms.entity.baseInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：商务类型表
 * */
@Data
@TableName("tms_business_type")
public class BusinessTypeEntity {

    @TableField("id")
    private Long id;

    @TableField("business_type")
    private String businessType;

}
