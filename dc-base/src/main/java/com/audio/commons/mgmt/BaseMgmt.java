package com.audio.commons.mgmt;

import com.audio.commons.exception.PortalException;

import javax.servlet.ServletContext;

/**
 * 京瘫佛
 * /*
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  `. . __
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑       永无BUG
 * <p>
 * 管理类的父类
 *
 * @author daigai(gaoxiang)
 * @version [版本号, 2017-1-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseMgmt
{
    /**
     * 初始化
     *
     * @throws PortalException
     */
    public abstract void init(ServletContext servletContext)
            throws PortalException;
}
