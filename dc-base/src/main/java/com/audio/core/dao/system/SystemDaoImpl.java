package com.audio.core.dao.system;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */

import com.audio.core.entity.Config;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SystemDaoImpl implements SystemDao
{
    @Resource(name="sqlSession")
    public SqlSessionTemplate sqlSession;

    @Override
    public List<Config> getSaBdConfig()
    {
        return sqlSession.selectList("System.getSaBdConfig");
    }
}
