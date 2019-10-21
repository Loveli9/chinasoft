package com.pms.service.impl.projectMember;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.impl.BaseService;
import com.pms.base.vo.DataTable;
import com.pms.dao.projectMember.IProjectMemberInfoDao;
import com.pms.entity.projectMember.ProjectMemberInfoEntity;
import com.pms.service.svcInterface.projectMember.IProjectMemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProjectMemberInfoService extends BaseService<IProjectMemberInfoDao, ProjectMemberInfoEntity> implements IProjectMemberInfoService {

    @Autowired
    private IProjectMemberInfoDao projectMemberInfoDao;

    @Override
    public Page<ProjectMemberInfoEntity> queryByCondition(Page page, DataTable<ProjectMemberInfoEntity> dt) {
        String projectCode = "";
        String domainAccount = "";
        if(dt.getSearchParams() != null) {
            projectCode = (dt.getSearchParams().get("search_eq_project_code") == null) ?
                    "" : dt.getSearchParams().get("search_eq_project_code").toString();
        }
        if(dt.getSearchParams() != null) {
            domainAccount = (dt.getSearchParams().get("search_eq_domain_account") == null) ?
                    "" : dt.getSearchParams().get("search_eq_domain_account").toString();
        }
        List<ProjectMemberInfoEntity> resultList =  projectMemberInfoDao.queryByCondition(page,projectCode,domainAccount);
        page.setRecords(resultList);
        return page;
    }


}
