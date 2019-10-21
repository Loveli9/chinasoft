package com.pms.base.dao;




import com.pms.base.entity.TreeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by  on 2017/4/10.
 * <p>
 * Email 
 * <p>
 * Describe:树型结构的DAO支持类实现
 */
@Component
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
     List<T> findByParentIdsLike(T entity);

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
     int updateParentIds(T entity);

}