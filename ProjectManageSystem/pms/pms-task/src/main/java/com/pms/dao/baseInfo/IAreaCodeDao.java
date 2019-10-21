package com.pms.dao.baseInfo;

import com.pms.base.dao.CrudDao;
import com.pms.entity.baseInfo.AreaCodeEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface IAreaCodeDao extends CrudDao<AreaCodeEntity> {


    /**
     * @attributionArea 城市代码
     * @return list
     */
    @Select("select * from tms_area_code where code = #{attributionArea}")
    AreaCodeEntity selectByAreaCode(String attributionArea);

}
