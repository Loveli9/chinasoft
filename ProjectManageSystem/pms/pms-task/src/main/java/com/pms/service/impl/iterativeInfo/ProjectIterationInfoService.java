package com.pms.service.impl.iterativeInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.impl.BaseService;
import com.pms.base.vo.DataTable;
import com.pms.dao.iterativeInfo.IProjectIterationInfoDao;
import com.pms.entity.iterativeInfo.ProjectIterationInfoEntity;
import com.pms.service.svcInterface.iterativeInfo.IProjectIterationInfoService;
import com.pms.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectIterationInfoService extends BaseService<IProjectIterationInfoDao, ProjectIterationInfoEntity> implements IProjectIterationInfoService {

    @Autowired
    private IProjectIterationInfoDao projectIterationInfoDao;

    @Override
    public boolean deleteByIterativeCode(String iterativeCode) {
        boolean result = projectIterationInfoDao.deleteByIterativeCode(iterativeCode);
        return result;
    }

    @Override
    public Page<ProjectIterationInfoEntity> queryByCondition(Page page, DataTable<ProjectIterationInfoEntity> dt) {
        String projectOwnership = "";
        String iterativeState = "";
        if(dt.getSearchParams() != null) {
            projectOwnership = (dt.getSearchParams().get("search_eq_project_ownership") == null) ?
                    "" : dt.getSearchParams().get("search_eq_project_ownership").toString();
        }
        if(dt.getSearchParams() != null) {
            iterativeState = (dt.getSearchParams().get("search_eq_iterative_state") == null) ?
                    "" : dt.getSearchParams().get("search_eq_iterative_state").toString();
        }
        if(StringUtils.isNotEmpty(projectOwnership) && StringUtils.isNotEmpty(iterativeState)) {
            return page.setRecords(this.projectIterationInfoDao.queryByCondition(page,projectOwnership,Integer.parseInt(iterativeState)));
        } else if (StringUtils.isEmpty(projectOwnership) && StringUtils.isNotEmpty(iterativeState)) {
            return page.setRecords(this.projectIterationInfoDao.queryByCondition1(page,"",Integer.parseInt(iterativeState)));
        } else if (StringUtils.isNotEmpty(projectOwnership) && StringUtils.isEmpty(iterativeState)) {
            return page.setRecords(this.projectIterationInfoDao.queryByCondition2(page,projectOwnership,0));
        } else {
            return page.setRecords(this.projectIterationInfoDao.queryByCondition3(page,"",0));
        }
    }

    @Override
    public List<ProjectIterationInfoEntity> findIterativeName(String projectOwnership) {
        return projectIterationInfoDao.findIterativeName(projectOwnership);
    }


}
