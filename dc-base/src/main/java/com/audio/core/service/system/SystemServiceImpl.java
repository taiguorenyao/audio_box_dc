package com.audio.core.service.system;

import com.audio.core.dao.system.SystemDao;
import com.audio.core.entity.Config;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
@Service(value = "systemService")
public class SystemServiceImpl implements SystemService
{

    @Resource
    private SystemDao systemDao;

    @Override
    public List<Config> getSaBdConfig()
    {
        return systemDao.getSaBdConfig();
    }
}
