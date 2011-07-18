package com.ivyinfo.framework.service.quartz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import com.ivyinfo.framework.service.server.SpringContextUtil;

public class SchedulerServlet  extends HttpServlet {
	private Logger logger =  Logger.getLogger("SchedulerServlet");
	
	public void init() throws ServletException {
		logger.log(Level.INFO, "[SchedulerServlet] INIT ...");
		try{
			SchedulerService schedulerService = (SchedulerService) SpringContextUtil.getBean("schedulerService");
			schedulerService.startAllJob();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//	    public static void main(String[] args) {   
//	        try {   
//	            //通过SchedulerFactory获取一个调度器实例   
//	            SchedulerFactory sf = new StdSchedulerFactory();   
//	            Scheduler sched = sf.getScheduler();   
//	            //创建一个JobDetail实现，并指定此JobDetail的任务类   
//	            JobDetail job = new JobDetail("job1", "group1", Test.class);   
//	            //通过SimpleTrigger定义调度规则：马上启动，每2秒运行一次，共运行100次   
//	            SimpleTrigger trigger = new SimpleTrigger("trigger1", "group1");   
//	            trigger.setStartTime(new Date());   
//	            trigger.setRepeatInterval(2000);   
//	            trigger.setRepeatCount(100);   
//	            //注册调度规则SimpleTrigger与JobDetail绑定关系   
//	            sched.scheduleJob(job, trigger);   
//	            //启动调度   
//	            sched.start();   
//	        } catch (SchedulerException e) {   
//	            // TODO Auto-generated catch block   
//	            e.printStackTrace();   
//	        }   
//	    }    

//	public static void main(String[] args) throws Exception {   
//		//只要加载配置文件就可以了,   
//		ApplicationContext context = new ClassPathXmlApplicationContext("com/ivyinfo/common/framework/config/applicationContext.xml");
//		BeanFactory factory = (BeanFactory) context;
//		SchedulerService schedulerService = (SchedulerService)factory.getBean("schedulerService");
//		//startCalendarTimeJob();
//		System.out.println("*****完毕******");
//	}
}
