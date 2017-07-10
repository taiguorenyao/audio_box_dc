package com.audio.core.service.permission;

import com.audio.core.dao.permission.PermissionDao;
import com.audio.core.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
@Service(value="permissionService")
public class PermissionServiceImpl implements PermissionService
{
    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Resource
    private PermissionDao permissionDao;


    @Override
    public List<Permission> getAllPermission() {
        try{
            return permissionDao.getAllPermission();
        }catch (Exception e){
            logger.info("权限获取失败",e);
        }
        return null;
    }
}
