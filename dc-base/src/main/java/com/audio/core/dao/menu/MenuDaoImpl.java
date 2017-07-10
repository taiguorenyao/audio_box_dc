package com.audio.core.dao.menu;

import com.audio.core.entity.Menu;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/10.
 * e-mail: ooxx@Live.cn
 */
@Repository
public class MenuDaoImpl implements MenuDao {

    @Resource(name="sqlSession")
    public SqlSessionTemplate sqlSession;

    @Override
    public List<Menu> getAllMenu() {
        return sqlSession.selectList("Menu.getAllMenu");
    }
}
