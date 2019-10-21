package com.pms.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Created by  on 2017/4/10.
 * <p>
 * Email 
 * <p>
 * Describe:   DAO支持类实现
 */


public interface BaseDao<T> extends BaseMapper<T> {

     void insert(String tableName);
}
