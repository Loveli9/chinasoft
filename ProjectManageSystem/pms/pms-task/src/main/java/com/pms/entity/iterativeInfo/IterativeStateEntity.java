package com.pms.entity.iterativeInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description：迭代状态表
 * */
@Data
@TableName("tms_iterative_state")
public class IterativeStateEntity {

    @TableField("id")
    private Long id;

    @TableField("iterative_state")
    private String iterativeState;

}
