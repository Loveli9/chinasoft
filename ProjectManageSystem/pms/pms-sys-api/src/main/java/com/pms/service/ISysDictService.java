package com.pms.service;

import com.pms.base.service.IBaseService;
import com.pms.base.vo.JsTree;
import com.pms.base.vo.PCAjaxVO;
import com.pms.entity.SysDict;

import java.util.List;

/**
 *
 * Describe: 系統数据字典
 */
public interface ISysDictService extends IBaseService<SysDict> {

    void insert(String dicKey, String dicValue, Long dicPid, String type, String desc, String sort, String invalid, String path);

    boolean update(Long id, String dicKey, String dicValue, String type, String desc, String sort, String invalid);

    List<JsTree> getDictTree();


     PCAjaxVO delete(Long id);


}
