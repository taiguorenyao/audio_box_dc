package com.audio.core.dao.permission;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */

import com.audio.core.entity.Permission;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PermissionDaoImpl implements PermissionDao
{
    @Resource(name="sqlSession")
    public SqlSessionTemplate sqlSession;
    @Override
    public List<Permission> getAllPermission() {
        return sqlSession.selectList("Permission.getAllPermission");
    }
}
