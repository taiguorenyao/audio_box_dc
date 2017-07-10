package com.audio.task;

import com.audio.cache.CaheBlog;
import com.audio.core.entity.Domain;
import com.audio.core.manager.Manager;
import com.audio.util.DateTimeUtil;
import com.audio.util.URLTool;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AutoDomainTask implements ServletContextListener
{

	static void task(ServletContext context)
    {
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
           
            public void run()
            {
                try
                {
                   System.out.println("一分钟执行一次_domain_task");
                   //检查域名
                   //Domain domain =  JdbcTemplateTool.getCurDomain();
                    Domain domain = (Domain)Manager.invoke().userManager(context)
                            .loadDate(7,null);

                   //查询不到域名什么也不做
                   if (domain != null)
                   {
                	   String url = domain.getVal();
                	 //查询当前使用域名
                       String result = URLTool.sendGet("http://vip.weixin139.com/weixin/630245166.php?domain="+url);
                       System.out.println(result);
                       JsonParser parse =new JsonParser();
                       JsonObject json=(JsonObject) parse.parse(result);
                       String status = json.get("status").getAsString();

                       String logResult = result.replaceAll("\"", "");
                       //正常更新域名检查记录
                       if ("0".equals(status))
                       {
                    	   //更新检查成功日志
                           Manager.invoke().userManager(context).updateObjectData(15,domain.getId(),"【"+
                                   DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG)+"】"+logResult);
                    	   return;
                       }
                       else
                       {
                    	   //先查询是否有备用域名
                           Domain bydomain = (Domain)Manager.invoke().userManager(context)
                                   .loadDate(8,null);

                           if (bydomain == null)
                           {
                               //记录没有替换的原因
                               Manager.invoke().userManager(context).updateObjectData(15,domain.getId(),"【原因：因无状态为2的备用域名使用，所以当前域名未替换。【"+DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG)+"】】"+logResult);
                               return;
                           }

                    	   if (bydomain.getId() == null)
                    	   {
                    		   //记录没有替换的原因
                               Manager.invoke().userManager(context).updateObjectData(15,domain.getId(),"【原因：因无状态为2的备用域名使用，所以当前域名未替换。【"+DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG)+"】】"+logResult);
                    		   return;
                    	   }
                    	   //备用域名没问题，执行当前域名状态为已封=1
                           Manager.invoke().userManager(context).updateObjectData(16,"1",domain.getId(),"【"+DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG)+"】"+logResult);


                           //更新config表Domain字段 然后刷新缓存
                           Manager.invoke().userManager(context).updateObjectData(17,"DOMAIN","http://"+bydomain.getVal()+"/");

                    	   //同步刷新缓存
                           String resMsg = CaheBlog.refreshNode("/cache/refreshAllConfig","");


                           //备用域名没问题，执行备用域名状态为 =0
                           Manager.invoke().userManager(context).updateObjectData(18,"0",bydomain.getId(),"【"+DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG)+"】域名替换成功！",resMsg);

                           return;
                       }

                   }
                   else
                   {
                	   System.out.println("自动检测任务---查询当前状态为0的域名失败！放弃所有操作");
                   }
                   
                }
                catch (Exception e)
                {
                    System.out.println("domian_task_ex:--------------");
                    e.printStackTrace();
                }
                
            }
        }, 0, 1000 * 60 * 2);
    }



	
	public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
    }

	
	
	
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

        try
        {

        }
        catch(Exception e)
        {

        }
		task(arg0.getServletContext());
	}

}
