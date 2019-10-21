package com.pms.service.svcInterface.iterativeInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.IBaseService;
import com.pms.base.vo.DataTable;
import com.pms.entity.iterativeInfo.ProjectIterationInfoEntity;
import java.util.List;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface IProjectIterationInfoService extends IBaseService<ProjectIterationInfoEntity> {

    boolean deleteByIterativeCode(String iterativeCode);

    Page<ProjectIterationInfoEntity> queryByCondition(Page page, DataTable<ProjectIterationInfoEntity> dt);

    List<ProjectIterationInfoEntity> findIterativeName(String projectOwnership);

}
