package com.audio.core.dao.user;

import com.audio.core.entity.*;
import com.audio.util.PaginationSupport;

import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
public interface UserDao
{
    List<User> loadUserByLoginname(String username);

    List<UserRole> getUserRoleByUserId(String username);

    BoxUser loadUserByAccount(String account);

    SysUser loadSysUserByAccount(String account);

    boolean loadIncode(String code);

    boolean saveBoxuser(BoxUser boxUser);

    boolean updateInCode(int type, String... val);


    List<PersonAviEvt> getPersonAviList(int pageStart, int pageSize, String... str);

    int getPersonAviListSize(int type,String... str);


    int updateData (int type,String... s);

    int updateObjectData (int type,Object... s);

    int saveVipad(List<vipAd> vipAdList);

    List<? extends BaseRedis> loadAllDate(int type,Object... s);


    List<? extends BaseRedis> getList(int type, int pageStart, int pageSize, String... str);

    int getListSize(int type,String... str);

    Object loadDate(int type, Object... s);


}
