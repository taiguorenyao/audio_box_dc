package com.audio.core.dao.data;

import com.audio.core.entity.AcessLog;
import com.audio.core.entity.BaseRedis;
import com.audio.core.entity.PersonAviEvt;
import com.audio.util.StringUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by taiguorenyao on 2017/1/10.
 * e-mail: ooxx@Live.cn
 */
@Repository
public class DataDaoImpl implements DataDao
{

    @Resource(name="sqlSession")
    public SqlSessionTemplate sqlSession;

    public void saveDataLog(AcessLog acessLog)
    {
        sqlSession.insert("Data.saveAccessLog",acessLog);
    }


    public List<? extends BaseRedis> loadAllDate(int type,Object... s)
    {
        if (1 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("times",
                    (String)s[0]);
            return sqlSession.selectList("Data.getGroupUidData",map);
        }

        return null;
    }


    public int updateObjectData (int type,Object... s)
    {
        if (1 == type)
        {
            return sqlSession.insert("Data.addGroupUid", (List<AcessLog>)s[0]);
        }

        if (2 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("times",
                    (String)s[0]);
            return sqlSession.insert("Data.delLog", map);
        }



        return 0;
    }

}
