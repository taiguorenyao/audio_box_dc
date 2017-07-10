package com.audio.core.dao.data;

import com.audio.core.entity.AcessLog;
import com.audio.core.entity.BaseRedis;

import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/10.
 * e-mail: ooxx@Live.cn
 */
public interface DataDao
{

    void saveDataLog(AcessLog acessLog);

    List<? extends BaseRedis> loadAllDate(int type,Object... s);

    int updateObjectData (int type,Object... s);

}
