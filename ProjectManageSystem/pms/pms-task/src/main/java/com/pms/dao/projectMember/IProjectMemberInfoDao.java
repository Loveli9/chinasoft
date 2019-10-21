package com.pms.dao.projectMember;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pms.base.dao.CrudDao;
import com.pms.entity.projectMember.ProjectMemberInfoEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by  on 2019/08/28.
 * <p>
 * Email 
 * <p>
 * Describe:
 */
@Component
public interface IProjectMemberInfoDao extends CrudDao<ProjectMemberInfoEntity> {

    /**
     * @projectcode 项目编码
     * @return list
     */
    @Select("<script>" +
            "select a.*,b.name as name " +
            "from tms_project_member_info a,tms_system_user_info b " +
            "where a.domain_account = b.domain_account " +
            "<if test='projectCode!=null and projectCode!=\"\"'>" +
                "and a.project_code = #{projectCode} " +
            "</if>" +
            "<if test='domainAccount!=null and domainAccount!=\"\"'>" +
                "and a.domain_account = #{domainAccount} " +
            "</if>" +
            "</script>")
    List<ProjectMemberInfoEntity> queryByCondition(Page page, String projectCode,String domainAccount);

}
