package com.pms.base.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pms.base.vo.DataTable;


public interface IBaseService<T> extends IService<T> {

    /**
     * DateTable 分页查询
     *
     * @param dt
     * @return
     */
    DataTable<T> pageSearch(DataTable<T> dt);

}
