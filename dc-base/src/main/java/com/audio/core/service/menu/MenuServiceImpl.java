package com.audio.core.service.menu;

import com.audio.core.dao.menu.MenuDao;
import com.audio.core.entity.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/10.
 * e-mail: ooxx@Live.cn
 */
@Service(value = "menuService")
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> getAllMenu() {
        try{
            return menuDao.getAllMenu();
        }catch (Exception e){
            logger.info("菜单获取失败",e);
        }
        return null;
    }
}
