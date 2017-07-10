package com.audio.task;

import com.audio.core.entity.AcessLog;
import com.audio.core.manager.Manager;
import com.audio.util.DateTimeUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class AutoDataCalculationTask implements ServletContextListener
{

    static int firsts = 0;

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

                   System.out.println("每天凌晨2点执行业务统计数据。");
                   if (firsts == 0)
                   {
                       System.out.println("启动执行不执行业务..");
                       firsts++;
                       System.out.println("启动执行不执行业务.." + firsts);
                   }
                   else
                   {
                       long tj_time_start = System.currentTimeMillis();
                       //按用户分组数据
                       String times = DateTimeUtil.getNow(DateTimeUtil.FORMAT_SHORT);
                       List<AcessLog> logList = (List<AcessLog>)Manager.invoke().dataManager(context).loadAllDate(1,times);
                       long tj_time_end = System.currentTimeMillis();
                       int counts = 0;
                       if (logList != null)
                       {
                           counts = logList.size();
                           System.out.println("["+times+"]统计时长(统计总数["+counts+"])："+ ((tj_time_end-tj_time_start)/1000 ) + "ms");
                           if (!logList.isEmpty())
                           {
                               //数据写表
                               int insCount = Manager.invoke().dataManager(context).updateObjectData(1,logList);
                               System.out.println("["+times+"](统计记录数总数["+insCount+"])：");

                               //删除数据
                               int delCount = Manager.invoke().dataManager(context).updateObjectData(2,times);
                               System.out.println("["+times+"](删除统计过的总数["+delCount+"])：");
                           }
                           else
                           {
                               System.out.println("异常执行，不操作业务。");
                           }


                       }
                       else
                       {
                           System.out.println("["+times+"]统计数据为空");
                       }
                   }


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
            }
        },time, 1000 * 60 * 60 * 24);
    }



	
	public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
    }

	
	
	
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		task(arg0.getServletContext());
	}

}
