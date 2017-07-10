package com.audio.core.dao.role;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */

import com.audio.core.entity.Role;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao
{

    @Resource(name="sqlSession")
    public SqlSessionTemplate sqlSession;
    /**
     * 获取所有角色
     *
     * @return
     */
    public List<Role> getAllRole()
    {
        return sqlSession.selectList("Role.getAllRole");
    }
}
