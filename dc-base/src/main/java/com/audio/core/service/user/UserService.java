package com.audio.core.service.user;

import com.audio.core.entity.*;
import com.audio.util.PaginationSupport;

import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
public interface UserService
{
    List<User> loadUserByLoginname(String username);

    BoxUser loadUserByAccount(String account);

    boolean loadIncode(String code);

    String saveBoxUser(BoxUser boxUser, String incode) throws Exception;

    PaginationSupport<PersonAviEvt> getPersonAviList(
            int beginPage, int pageSize, String... str);

    boolean updateData (int type,String... s);

    boolean updateObjectData (int type,Object... s);

    List<? extends BaseRedis> loadAllDate(int type, Object... s);

    Object loadDate(int type, Object... s);

    SysUser loadSysUserByAccount(String account);


    PaginationSupport<InCode> getInCodeList(
            int beginPage, int pageSize, String... str);

    PaginationSupport<BoxUser> getUserList(
            int beginPage, int pageSize, String... str);


    PaginationSupport<PersonAviEvt> getPersonAviEvtList(
            int beginPage, int pageSize, String... str);


    PaginationSupport<Config> getConfigList(
            int beginPage, int pageSize, String... str);

    PaginationSupport<Domain> getDomainList(
            int beginPage, int pageSize, String... str);


    PaginationSupport<Domain> getDomainCenterList(
            int beginPage, int pageSize, String... str);

    PaginationSupport<Notice> getNoticeList(
            int beginPage, int pageSize, String... str);

}
