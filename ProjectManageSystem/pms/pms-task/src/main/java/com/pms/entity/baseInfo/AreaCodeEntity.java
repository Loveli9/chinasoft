package com.pms.entity.baseInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：地域代码表
 * */
@Data
@TableName("tms_area_code")
public class AreaCodeEntity {

    @TableField("code")
    private String code;

    @TableField("city")
    private String city;

}
