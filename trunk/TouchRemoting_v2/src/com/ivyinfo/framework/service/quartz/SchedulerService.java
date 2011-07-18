package com.ivyinfo.framework.service.quartz;

import java.util.Date;
import java.util.HashMap;

import org.quartz.CronExpression;
import org.quartz.JobDetail;
import org.quartz.Scheduler;


/**
 * 			//设置高度任务
			//Date startTime = this.parse("2009-06-24 14:00:00");
			//Date endTime =  this.parse("2009-06-01 21:55:00");
			//每10秒中执行调试一次
			//schedulerService.schedule(job,Scheduler.DEFAULT_GROUP, "0/10 * * ? * * *"); 
	        
			//2009-06-01 21:50:00开始执行调度
			//schedulerService.schedule(job,Scheduler.DEFAULT_GROUP,startTime);
	
			//2009-06-01 21:50:00开始执行调度，2009-06-01 21:55:00结束执行调试
			//schedulerService.schedule(job,Scheduler.DEFAULT_GROUP,startTime,endTime);
			
			//2009-06-01 21:50:00开始执行调度，执行5次结束
			//schedulerService.schedule(job,Scheduler.DEFAULT_GROUP,startTime,null,5);
	
			//2009-06-01 21:50:00开始执行调度，每隔20秒执行一次，执行5次结束
			//schedulerService.schedule(job,Scheduler.DEFAULT_GROUP,startTime,null,5,20);
			
//	        try {
//	            // wait 90 seconds to show jobs
//	            Thread.sleep(90L * 1000L); 
//	            // executing...
//	        } catch (Exception e) {
//	        }
//	        schedulerService.deleteJob(job, Scheduler.DEFAULT_GROUP);
//	        System.err.println("==============emailJob delete=============");
 * 
 * @author Administrator
 *
 */
public interface SchedulerService {
	
	/**
	 * 删除任务
	 * @param jobDetail 任务信息
	 * @param group 组或者userid，区分不同人的任务
	 */
	public void deleteJob(JobDetail jobDetail,String group)throws Exception;
	
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param jobDetail 任务信息
	 * @param group 组或者userid，区分不同人的任务
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	public void schedule(JobDetail jobDetail,String group, String cronExpression)throws Exception;

	
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param jobDetail 任务信息
	 * @param group 组或者userid，区分不同人的任务
	 * @param cronExpression Quartz CronExpression
	 */
	public void schedule(JobDetail jobDetail,String group,CronExpression cronExpression)throws Exception;
	
	
	/**
	 * 在startTime时执行调试一次
	 * @param jobDetail 任务信息
	 * @param group 组或者userid，区分不同人的任务
	 * @param startTime 调度开始时间
	 */
	public void schedule(JobDetail jobDetail,String group,Date startTime)throws Exception;
	
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * @param jobDetail 任务信息
	 * @param group 组或者userid，区分不同人的任务
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 */
	public void schedule(JobDetail jobDetail,String group,Date startTime,Date endTime)throws Exception;
	
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param jobDetail 任务信息
	 * @param group 组或者userid，区分不同人的任务
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	public void schedule(JobDetail jobDetail,String group,Date startTime,Date endTime,int repeatCount)throws Exception;
	
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param jobDetail 任务信息
	 * @param group 组或者userid，区分不同人的任务
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	public void schedule(JobDetail jobDetail,String group,Date startTime,Date endTime,int repeatCount,long repeatInterval)throws Exception;
	
	/**
	 * 维护数据库
	 * @param jobname  任务名
	 * @param grouptype  任务组类型  如果是机构：machine  如果是部门：dept   如果是组： group   如果是个人： user
	 * @param groupid  任务组id     如果是机构：DEFAULT  如果是部门：[D]id  如果是组： [G]id   如果是个人：[U]id
	 * @param jobclass  任务调用类 class  
	 * @param jobRuleName  任务调用规则  规则组名.规则名       PS：要么有jobclass ， 要么有jobRuleName 不能都为空，必须有一个
	 * @param JobCronexpression  任务调用时间   * * * * * ？   PS: 秒 分 时 日 月 年
	 * @param jobstarttime 任务开始时间
	 * @param jobendtime 任务结束时间  PS：必须有开始时间  否则为空
	 * @param jobrepeatcount  任务执行次数 重复执行repeatCount次
	 * @param jobrepeatinterval 任务每隔repeatInterval秒执行一次 
	 * @param jobState 任务状态 1：启动 0：停止
	 * @param type 维护类型  insert：新增  update：修改  delete：删除
	 * @return
	 * @throws Exception
	 */
	public SchedulerTimeBean setScheduler(String jobname, String grouptype, String groupid, 
			String jobclass, String jobRuleName, String JobCronexpression,
			String jobstarttime, String jobendtime, int jobrepeatcount, int jobrepeatinterval, String jobState, String type)throws Exception ;
	
	/**
	 * 启动所有任务
	 * @throws Exception
	 */
	public void startAllJob() throws Exception;
	
	/**
	 * 启动一个任务  PS：先删除再启动
	 * @param jobName  任务名称
	 * @throws Exception
	 */
	public void startOneJob(String jobName) throws Exception;
	
	/**
	 * 获取所有任务信息
	 * @return  HashMap<SchedulerTimeBean>
	 * @throws Exception
	 */
	public HashMap getTimeBean() throws Exception;
	
	/**
	 * 获取一个任务信息
	 * @param jobName  任务名称
	 * @return HashMap<SchedulerTimeBean>
	 * @throws Exception
	 */
	public HashMap getTimeBean(String jobName) throws Exception;
}