package com.audio.core.service.data;

import com.audio.core.dao.data.DataDao;
import com.audio.core.entity.AcessLog;
import com.audio.core.entity.BaseRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
@Service(value = "dataService")
public class DataServiceImpl implements DataService
{
    @Resource
    private DataDao dataDao;

    public void saveAccessLog(AcessLog acessLog)
    {
        dataDao.saveDataLog(acessLog);
    }

    public List<? extends BaseRedis> loadAllDate(int type, Object... s)
    {
        try
        {
            return dataDao.loadAllDate(type, s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public int updateObjectData(int type, Object... s)
    {
        try
        {
            return dataDao.updateObjectData(type, s);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }
}
