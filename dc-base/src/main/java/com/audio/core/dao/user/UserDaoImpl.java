package com.audio.core.dao.user;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */

import com.audio.core.entity.*;
import com.audio.util.StringUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao
{
    @Resource(name="sqlSession")
    public SqlSessionTemplate sqlSession;

    @Override
    public List<User> loadUserByLoginname(String username)
    {
        return sqlSession.selectList("User.loadUserByLoginname", username);
    }

    @Override
    public List<UserRole> getUserRoleByUserId(String userId)
    {
        return sqlSession.selectList("User.getUserRoleByUserId", userId);
    }

    @Override
    public BoxUser loadUserByAccount(String account)
    {
        return sqlSession.selectOne("User.loadUserByAccount", account);
    }

    @Override
    public SysUser loadSysUserByAccount(String account)
    {
        return sqlSession.selectOne("User.loadSysUserByAccount", account);
    }

    public Object loadDate(int type, Object... s)
    {
        if (1 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("uid",
                    (String)s[0]);
            return sqlSession.selectOne("User.loadBoxUserById", map);
        }

        if (2 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("id",
                    (String)s[0]);
            return sqlSession.selectOne("User.loadConfigById", map);
        }

        if (3 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("id",
                    (String)s[0]);
            return sqlSession.selectOne("User.loadUserById", map);
        }

        if (4 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("userInCode",
                    (String)s[0]);
            return sqlSession.selectOne("User.loadUserByCode", map);
        }

        if (5== type)
        {
            Map<String, Object> map = StringUtil.getParamMap("account",
                    (String)s[0]);
            return sqlSession.selectOne("User.getUserUnderCount", map);
        }

        if (6 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("account",
                    (String)s[0]);
            return sqlSession.selectOne("User.getUserUnderCount", map);
        }

        if (7 == type)
        {
            return sqlSession.selectOne("User.getCurDomain");
        }

        if (8 == type)
        {
            return sqlSession.selectOne("User.getByDomain");
        }

        if (9 == type)
        {
            return sqlSession.selectOne("User.getCurDomainCenter");
        }

        if (10 == type)
        {
            return sqlSession.selectOne("User.getByDomainCenter");
        }

        return null;
    }

    @Override
    public boolean loadIncode(String code)
    {
        Integer count = sqlSession.selectOne("User.loadIncode", code);
        if (count > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean saveBoxuser(BoxUser boxUser)
    {
        int count = sqlSession.insert("User.saveBoxuser", boxUser);
        if (count > 0)
        {
            return true;
        }
        return false;
    }

    @Validated
    public boolean updateInCode(int type, String... val)
    {
        if (1 == type)
        {
            Map<String, Object> map = StringUtil.getParamMap("state",
                    Integer.parseInt(val[0]),
                    "code",
                    val[1],
                    "account",
                    val[2]);
            sqlSession.update("User.updateInCode", map);
        }

        return false;
    }

    public List<PersonAviEvt> getPersonAviList(int pageStart, int pageSize,
            String... str)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("curPage", pageStart);
        map.put("pageSize", pageSize);
        map.put("uid", str[0]);
        return sqlSession.selectList("User.getPersonAviList",
                map);
    }

    public int getPersonAviListSize(int type, String... str)
    {
        if (1 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", str[0]);
            return (Integer) sqlSession.selectOne("User.getPersonAviListSize",
                    map);
        }
        return 0;
    }

    public int updateData(int type, String... s)
    {
        if (1 == type)
        {
            return sqlSession.update("User.delAudio", s[0]);
        }

        if (2 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", s[0]);
            return sqlSession.update("User.delConfig", map);
        }


        if (3 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", s[0]);
            return sqlSession.update("User.delPersonAvi", map);
        }

        if (4 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", s[0]);
            return sqlSession.update("User.deldomains", map);
        }

        if (5 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", s[0]);
            return sqlSession.update("User.deldomains_center", map);
        }

        if (6 == type)
        {
            return sqlSession.update("User.delPersonAviall");
        }
        return 0;
    }

    public int updateObjectData(int type, Object... s)
    {
        if (1 == type)
        {
            return sqlSession.insert("User.addAudio", (PersonAviEvt) s[0]);
        }

        if (2 == type)
        {
            return sqlSession.insert("User.addPersonAd", (PersonAdEvt) s[0]);
        }

        if (3 == type)
        {
            return sqlSession.insert("User.updatePersonAd", (PersonAdEvt) s[0]);
        }

        if (4 == type)
        {
            return sqlSession.update("User.updateVipad", (vipAd) s[0]);
        }

        if (5 == type)
        {
            return sqlSession.update("User.addInCode", (InCode) s[0]);
        }

        if (6 == type)
        {
            return sqlSession.update("User.updateUserByid", (BoxUser) s[0]);
        }

        if (7 == type)
        {
            return sqlSession.insert("User.addConfig", (Config) s[0]);
        }

        if (8 == type)
        {
            return sqlSession.insert("User.updateConfig", (Config) s[0]);
        }

        if (9 == type)
        {
            return sqlSession.insert("User.delConfig",  s[0]);
        }

        if (10 == type)
        {
            return sqlSession.insert("User.delUser",  s[0]);
        }

        if (11 == type)
        {
            return sqlSession.update("User.updateUserIncodeByid", (BoxUser) s[0]);
        }

        if (12 == type)
        {
            return sqlSession.update("User.addDomain", (Domain) s[0]);
        }

        if (13 == type)
        {
            return sqlSession.update("User.delDomain", (String) s[0]);
        }

        if (15 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", s[0]);
            map.put("txt", s[1]);
            return sqlSession.update("User.updateDoMain_1", map);
        }

        if (16 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", s[0]);
            map.put("id", s[1]);
            map.put("txt", s[2]);
            return sqlSession.update("User.updateDoMain_2", map);
        }

        if (17 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("key", s[0]);
            map.put("value", s[1]);
            return sqlSession.update("User.updateConfig_Domain", map);
        }

        if (18 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", s[0]);
            map.put("id", s[1]);
            map.put("txt", s[2]);
            map.put("nodeTxt", s[3]);
            return sqlSession.update("User.updateDoMain_3", map);
        }


        if (19 == type)
        {
            return sqlSession.update("User.addDomainCenter", (Domain) s[0]);
        }

        if (20 == type)
        {
            return sqlSession.update("User.delDomainCenter", (String) s[0]);
        }

        if (21 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", s[0]);
            map.put("txt", s[1]);
            return sqlSession.update("User.updateDoMain_center_1", map);
        }

        if (22 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", s[0]);
            map.put("id", s[1]);
            map.put("txt", s[2]);
            return sqlSession.update("User.updateDoMain_center_2", map);
        }

        if (23 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", s[0]);
            map.put("id", s[1]);
            map.put("txt", s[2]);
            map.put("nodeTxt", s[3]);
            return sqlSession.update("User.updateDoMain_center_3", map);
        }

        if (24 == type)
        {
            return sqlSession.insert("User.addNotice", (Notice) s[0]);
        }

        if (25 == type)
        {
            return sqlSession.insert("User.delNotice", s[0]);
        }

        if (26 == type)
        {
            return sqlSession.insert("User.delAllAudioByuid", s[0]);
        }

        if (27 == type)
        {
            return sqlSession.insert("User.addAudioMaxs", (List<PersonAviEvt>)s[0]);
        }


        return 0;
    }

    public List<? extends BaseRedis> loadAllDate(int type, Object... s)
    {
        if (1 == type)
        {
            return sqlSession.selectList("User.getAllAudio");
        }

        if (2 == type)
        {
            return sqlSession.selectList("User.getAllConfig");
        }

        if (3 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", s[0]);
            return sqlSession.selectList("User.getAllAviById", map);
        }

        if (4 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", s[0]);
            return sqlSession.selectList("User.getAllPersonAdById", map);
        }

        if (5 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", s[0]);
            return sqlSession.selectList("User.getAllvipadByUid", map);
        }

        if (6 == type)
        {
            return sqlSession.selectList("User.getAllvipad");
        }

        if (7 == type)
        {
            return sqlSession.selectList("User.getAllPersonAd");
        }

        if (8 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", s[0]);
            return sqlSession.selectList("User.getUserDown", map);
        }

        if (9 == type)
        {
            String count =  s[0]+"";
            try
            {
                Integer.parseInt(count);
            }
            catch (Exception e)
            {
                count = "5";
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("count", Integer.parseInt(count));
            return sqlSession.selectList("User.getTopNotice", map);
        }

        return null;
    }

    public int saveVipad(List<vipAd> vipAdList)
    {
        return sqlSession.insert("User.addVipad", vipAdList);
    }

    public List<? extends BaseRedis> getList(int type, int pageStart, int pageSize,
            String... str)
    {
        if (1 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("curPage", pageStart);
            map.put("pageSize", pageSize);
            return sqlSession.selectList("User.getInCodeList",
                    map);
        }
        if (2 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("curPage", pageStart);
            map.put("pageSize", pageSize);
            map.put("keyword",str[0]);
            return sqlSession.selectList("User.getUserList",
                    map);
        }

        if (3 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("curPage", pageStart);
            map.put("pageSize", pageSize);
            return sqlSession.selectList("User.aviAdList",
                    map);
        }

        if (4 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("curPage", pageStart);
            map.put("pageSize", pageSize);
            return sqlSession.selectList("User.ConfigList",
                    map);
        }

        if (5 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("curPage", pageStart);
            map.put("pageSize", pageSize);
            return sqlSession.selectList("User.DomainList",
                    map);
        }

        if (6 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("curPage", pageStart);
            map.put("pageSize", pageSize);
            return sqlSession.selectList("User.DomainListCenter",
                    map);
        }

        if (7 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("curPage", pageStart);
            map.put("pageSize", pageSize);
            return sqlSession.selectList("User.NoticeList",
                    map);
        }

        return null;
    }

    public int getListSize(int type, String... str)
    {
        if (1 == type)
        {
            return (Integer) sqlSession.selectOne("User.getInCodeListSize");
        }

        if (2 == type)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("keyword", str[0]);
            return (Integer) sqlSession.selectOne("User.getUserListSize",map);
        }

        if (3 == type)
        {
            return (Integer) sqlSession.selectOne("User.aviAdListSize");
        }

        if (4 == type)
        {
            return (Integer) sqlSession.selectOne("User.ConfigListSize");
        }

        if (5 == type)
        {
            return (Integer) sqlSession.selectOne("User.DomainListSize");
        }

        if (6 == type)
        {
            return (Integer) sqlSession.selectOne("User.DomainListCenterSize");
        }

        if (7 == type)
        {
            return (Integer) sqlSession.selectOne("User.NoticeListSize");
        }

        return 0;
    }

}
