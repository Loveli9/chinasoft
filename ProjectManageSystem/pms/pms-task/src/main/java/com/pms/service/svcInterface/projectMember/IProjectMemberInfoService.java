package com.pms.service.svcInterface.projectMember;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.service.IBaseService;
import com.pms.base.vo.DataTable;
import com.pms.entity.projectMember.ProjectMemberInfoEntity;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
public interface IProjectMemberInfoService extends IBaseService<ProjectMemberInfoEntity> {

    Page<ProjectMemberInfoEntity> queryByCondition(Page page, DataTable<ProjectMemberInfoEntity> dt);

}
