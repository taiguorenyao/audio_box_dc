package com.audio.core.service.user;

import com.audio.core.dao.user.UserDao;
import com.audio.core.entity.*;
import com.audio.util.AESEncrypter;
import com.audio.util.PaginationSupport;
import com.audio.util.StringUtil;
import com.audio.util.UBB;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService
{

    @Resource
    private UserDao userDao;

    @Override
    public List<User> loadUserByLoginname(String username)
    {
        return userDao.loadUserByLoginname(username);
    }

    public BoxUser loadUserByAccount(String account)
    {
        try
        {
            return userDao.loadUserByAccount(account);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean loadIncode(String code)
    {
        try
        {
            return userDao.loadIncode(code);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Object loadDate(int type, Object... s)
    {
        try
        {
            return userDao.loadDate(type,s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public String saveBoxUser(BoxUser boxUser, String incode)
    {
        try
        {
            //注册用户
            //变更邀请码状态
            String id = StringUtil.getId();
            boxUser.setId(id);
            boxUser.setPwd(AESEncrypter.getInstance().encrypt(boxUser.getPwd(),
                    AESEncrypter.VOCE));
            boxUser.setAccount(UBB.filterHtml(boxUser.getAccount()));
            boxUser.setPhone(UBB.filterHtml(boxUser.getPhone()));
            boxUser.setQq(UBB.filterHtml(boxUser.getQq()));
            boxUser.setT_name(UBB.filterHtml(boxUser.getT_name()));
            userDao.saveBoxuser(boxUser);
            userDao.updateInCode(1, "1", incode, boxUser.getAccount());
            //初始化15条广告数据
            List<vipAd> viplist = new ArrayList<vipAd>();

            for (int i = 0; i <= 14; i++)
            {
                vipAd vip = new vipAd();
                vip.setUid(id);
                viplist.add(vip);
            }
            //批量数据
            userDao.saveVipad(viplist);
            return "true";
        }
        catch (DuplicateKeyException e1)
        {
            return "ck_account";
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return "false";
    }

    public PaginationSupport<PersonAviEvt> getPersonAviList(
            int beginPage, int pageSize, String... str)
    {

        PaginationSupport<PersonAviEvt> pageList = new PaginationSupport<PersonAviEvt>();
        List<PersonAviEvt> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getPersonAviListSize(1, str[0]);
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = userDao.getPersonAviList(startPage, endPage, str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/my/auditList");
        return pageList;
    }

    public boolean updateData(int type, String... s)
    {
        try
        {
            int count = userDao.updateData(type, s);
            if (count > 0)
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return false;
    }

    public boolean updateObjectData(int type, Object... s)
    {
        try
        {
            int count = userDao.updateObjectData(type, s);
            if (count > 0)
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return false;
    }

    public List<? extends BaseRedis> loadAllDate(int type, Object... s)
    {
        try
        {
            return userDao.loadAllDate(type, s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SysUser loadSysUserByAccount(String username)
    {
        return userDao.loadSysUserByAccount(username);
    }

    public PaginationSupport<InCode> getInCodeList(
            int beginPage, int pageSize, String... str)
    {

        PaginationSupport<InCode> pageList = new PaginationSupport<InCode>();
        List<InCode> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getListSize(1,"");
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = (List<InCode>) userDao.getList(1,
                    startPage,
                    endPage,
                    str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/sys/incodelist");
        return pageList;
    }



    public PaginationSupport<BoxUser> getUserList(
            int beginPage, int pageSize, String... str)
    {
        PaginationSupport<BoxUser> pageList = new PaginationSupport<BoxUser>();
        List<BoxUser> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getListSize(2,str);
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = (List<BoxUser>) userDao.getList(2,
                    startPage,
                    endPage,
                    str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/sys/userlist");
        return pageList;
    }


    public PaginationSupport<PersonAviEvt> getPersonAviEvtList(
            int beginPage, int pageSize, String... str)
    {
        PaginationSupport<PersonAviEvt> pageList = new PaginationSupport<PersonAviEvt>();
        List<PersonAviEvt> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getListSize(3,"");
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = (List<PersonAviEvt>) userDao.getList(3,
                    startPage,
                    endPage,
                    str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/sys/aviadlist");
        return pageList;
    }

    public PaginationSupport<Config> getConfigList(
            int beginPage, int pageSize, String... str)
    {
        PaginationSupport<Config> pageList = new PaginationSupport<Config>();
        List<Config> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getListSize(4,"");
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = (List<Config>) userDao.getList(4,
                    startPage,
                    endPage,
                    str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/sys/configlist");
        return pageList;
    }


    public PaginationSupport<Domain> getDomainCenterList(
            int beginPage, int pageSize, String... str)
    {

        PaginationSupport<Domain> pageList = new PaginationSupport<Domain>();
        List<Domain> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getListSize(6,"");
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = (List<Domain>) userDao.getList(6,
                    startPage,
                    endPage,
                    str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/sys/domaincenterlist");
        return pageList;
    }

    public PaginationSupport<Domain> getDomainList(
            int beginPage, int pageSize, String... str)
    {

        PaginationSupport<Domain> pageList = new PaginationSupport<Domain>();
        List<Domain> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getListSize(5,"");
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = (List<Domain>) userDao.getList(5,
                    startPage,
                    endPage,
                    str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/sys/domaincenterlist");
        return pageList;
    }

    public PaginationSupport<Notice> getNoticeList(
            int beginPage, int pageSize, String... str)
    {
        PaginationSupport<Notice> pageList = new PaginationSupport<Notice>();
        List<Notice> vList = null;
        int count = 0;
        int totalPage = 0;
        try
        {
            //查询总条数
            count = userDao.getListSize(7,"");
            if (count == 0)
            {
                return null;
            }
            //得到总页数
            totalPage = StringUtil.getTotalPage(count, pageSize);

            //如果当前页大于总页数，自动跳最后一页
            if (beginPage > totalPage)
            {
                beginPage = totalPage;
            }

            int startPage = 0;
            int endPage = pageSize;
            if (beginPage == 1)
            {
                startPage = 0;
            }
            else
            {
                startPage = (beginPage - 1) * pageSize;
            }

            vList = (List<Notice>) userDao.getList(7,
                    startPage,
                    endPage,
                    str);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pageList.setItems(vList);
        pageList.setTotalRecord(String.valueOf(count));
        pageList.setCurPage(String.valueOf(beginPage));
        pageList.setPageSize(String.valueOf(pageSize));
        pageList.setParams("/sys/noticelist");
        return pageList;
    }


}
