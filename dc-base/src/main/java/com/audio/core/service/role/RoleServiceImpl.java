package com.audio.core.service.role;

import com.audio.core.dao.role.RoleDao;
import com.audio.core.entity.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
@Service(value="roleService")
public class RoleServiceImpl implements RoleService
{

    @Resource
    private RoleDao roleDao;


    /**
     * 获取所有角色
     * @return
     */
    public List<Role> getAllRole()
    {
        try
        {
           return roleDao.getAllRole();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
