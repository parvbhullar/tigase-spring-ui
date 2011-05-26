package com.ivyinfo.framework.service.quartz;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ivyinfo.framework.service.sequence.Sequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class SchedulerServiceImpl implements SchedulerService {
	private Logger logger =  Logger.getLogger("SchedulerServiceImpl");
	
	protected JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void schedule(JobDetail jobDetail,String group, String cronExpression) throws Exception {
		try {
			schedule(jobDetail,group, new CronExpression(cronExpression));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void schedule(JobDetail jobDetail,String group, CronExpression cronExpression) throws Exception {
		Scheduler scheduler = (Scheduler) SpringContextUtil.getBean("quartzScheduler");
		scheduler.addJob(jobDetail, true);
		CronTrigger cronTrigger = new CronTrigger(jobDetail.getName(), group, jobDetail.getName(),group);
		cronTrigger.setCronExpression(cronExpression);
		scheduler.scheduleJob(cronTrigger);
		scheduler.rescheduleJob(jobDetail.getName(), group, cronTrigger);
	}


	public void schedule(JobDetail jobDetail, String group,Date startTime) throws Exception {
		schedule(jobDetail,group, startTime, null);
		
	}

	public void schedule(JobDetail jobDetail, String group, Date startTime, Date endTime) throws Exception {
		schedule(jobDetail,group, startTime, endTime, 0);
		
	}

	public void schedule(JobDetail jobDetail, String group, Date startTime, Date endTime, int repeatCount) throws Exception {
		schedule(jobDetail,group, startTime, endTime, 0, 0L);
		
	}
	
	public void schedule(JobDetail jobDetail, String group, Date startTime, Date endTime, int repeatCount, long repeatInterval) throws Exception {
		Scheduler scheduler = (Scheduler) SpringContextUtil.getBean("quartzScheduler");
		scheduler.addJob(jobDetail, true);
		SimpleTrigger SimpleTrigger = new SimpleTrigger(jobDetail.getName(), group, jobDetail.getName(),group, startTime, endTime, repeatCount, repeatInterval);
		scheduler.scheduleJob(SimpleTrigger);
		scheduler.rescheduleJob(jobDetail.getName(), group, SimpleTrigger);
		scheduler.start();
	}
	

	public void deleteJob(JobDetail jobDetail, String group) throws Exception {
		Scheduler scheduler = (Scheduler) SpringContextUtil.getBean("quartzScheduler");
		scheduler.deleteJob(jobDetail.getName(), group);
	}
	
	public void startAllJob() throws Exception{
		startOneJob("");
	}
	
	public void startOneJob(String jobName) throws Exception{
		try{
			HashMap timeMap = getTimeBean(jobName);
			JobDetail job = null;
			System.err.println("timeMap:"+timeMap.size());
			if(timeMap != null){
				for(Iterator it = timeMap.entrySet().iterator(); it.hasNext(); ){
					Map.Entry e = (Map.Entry)it.next();
					SchedulerTimeBean bean = (SchedulerTimeBean)e.getValue();
					
					if(bean.getJobClass().indexOf("[AIJOB]")>-1){
						Class c=Class.forName("com.ivyinfo.common.framework.structure.quartz.JobEngine");	
						job = new JobDetail(bean.getJobname(), bean.getGroupid(), c);
						job.setDescription(bean.getJobClass().substring(7,bean.getJobClass().length()));
					}else{
						Class c=Class.forName(bean.getJobClass());
						System.err.println("bean.getJobClass():"+bean.getJobClass());
						job = new JobDetail(bean.getJobname(), bean.getGroupid(), c);
					}
						
					deleteJob(job, bean.getGroupid());
					logger.log(Level.INFO, "[JOB]["+String.valueOf(e.getKey())+"]deleteJob...");
					if("1".equals(bean.getJobstate())){
						if("C".equals(bean.getJobtype())){
							schedule(job,bean.getGroupid(), bean.getJobCronExpression());
						}else if("S".equals(bean.getJobtype())){
							schedule(job, bean.getGroupid(), parseStringToDate(bean.getJobStartTime()));
						}else if("SE".equals(bean.getJobtype())){	
							schedule(job, bean.getGroupid(), parseStringToDate(bean.getJobStartTime()), parseStringToDate(bean.getJobEndTime()));
						}else if("SERC".equals(bean.getJobtype())){	
							schedule(job, bean.getGroupid(), parseStringToDate(bean.getJobStartTime()), parseStringToDate(bean.getJobEndTime()), bean.getJobRepeatCount());
						}else if("SERCRI".equals(bean.getJobtype())){	
							schedule(job, bean.getGroupid(), parseStringToDate(bean.getJobStartTime()), parseStringToDate(bean.getJobEndTime()), bean.getJobRepeatCount(), bean.getJobRepeatInterval());
						}
						logger.log(Level.INFO, "[JOB]["+String.valueOf(e.getKey())+"]start...");
					}
					
				}	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.log(Level.ERROR,ex.getMessage());
		}
	}	
	
	public HashMap getTimeBean() throws Exception {
		return getTimeBean("");
	}
	
	protected class schedulertime_marc implements RowMapper<SchedulerTimeBean> {
		public SchedulerTimeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.err.println("=======schedulertime_marc=========");
			SchedulerTimeBean bean = new SchedulerTimeBean();
			bean.setJobname(rs.getString("jobname"));
			bean.setGrouptype(rs.getString("grouptype"));
			bean.setGroupid(rs.getString("groupid"));
			bean.setJobClass(rs.getString("jobClass"));
			bean.setJobstate(rs.getString("jobstate"));
			bean.setJobtype(rs.getString("jobtype"));
			bean.setJobCronExpression(rs.getString("jobCronExpression"));
			bean.setJobStartTime(rs.getString("jobStartTime"));
			bean.setJobEndTime(rs.getString("jobEndTime"));
			bean.setJobRepeatCount(rs.getInt("jobRepeatCount"));
			bean.setJobRepeatInterval(rs.getInt("jobRepeatInterval"));
			return bean;
		}
	}

	public HashMap<String,SchedulerTimeBean> getTimeBean(String jobName) throws Exception {
		try {
			HashMap<String,SchedulerTimeBean> timeMap = new HashMap<String,SchedulerTimeBean>();
			StringBuffer strSqlBuf = new StringBuffer();
			if(jobName.equals("")){
				strSqlBuf.append("Select jobname,grouptype,groupid,jobClass,jobstate,jobtype,jobCronExpression,jobStartTime,jobEndTime,jobRepeatCount,jobRepeatInterval from t_sys_schedulertime");
			}else{
				strSqlBuf.append("Select jobname,grouptype,groupid,jobClass,jobstate,jobtype,jobCronExpression,jobStartTime,jobEndTime,jobRepeatCount,jobRepeatInterval from t_sys_schedulertime where jobname = '"+jobName+"' ");
			}
			System.err.println("==========:"+strSqlBuf.toString());
			System.err.println("=======jdbcTemplate===:"+jdbcTemplate);
			List<SchedulerTimeBean> ls = jdbcTemplate.query(strSqlBuf.toString(), new schedulertime_marc());
			System.err.println("ls.size():"+ls.size());
			for(int i=0; i<ls.size(); i++){
				SchedulerTimeBean bean = (SchedulerTimeBean)ls.get(i);
				timeMap.put(bean.getJobname(), bean);
			}
			
			return timeMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SchedulerTimeBean setScheduler(String jobname, String grouptype, String groupid, 
			String jobclass, String jobRuleName, String JobCronexpression,
			String jobstarttime, String jobendtime, int jobrepeatcount, int jobrepeatinterval, String jobState, String type)throws Exception {
		
		SchedulerTimeBean stbean = new SchedulerTimeBean();
		stbean.setJobname(jobname);
		stbean.setGrouptype(grouptype);
		stbean.setGroupid(groupid);
		if(jobclass != null && !"".equals(jobclass))stbean.setJobClass(jobclass);
		if(jobRuleName != null && !"".equals(jobRuleName))stbean.setJobClass("[AIJOB]"+jobRuleName);
		String typeC = "";
		if(JobCronexpression != null && !"".equals(JobCronexpression)){
			stbean.setJobCronExpression(JobCronexpression);
			typeC = "C";
		}
		String typeS = "";
		if(jobstarttime != null && !"".equals(jobstarttime)){
			stbean.setJobStartTime(jobstarttime);
			typeS = "S";
		}
		String typeE = "";
		if(jobendtime != null && !"".equals(jobendtime)){
			stbean.setJobEndTime(jobendtime);
			typeE = "E";
		}
		String typeRC = "";
		if(jobrepeatcount != 0 ){
			stbean.setJobRepeatCount(jobrepeatcount);
			typeRC = "RC";
		}
		String typeRI = "";
		if(jobrepeatinterval != 0){
			stbean.setJobRepeatInterval(jobrepeatinterval);
			typeRI = "RI";
		}
		stbean.setJobtype(typeC + typeS + typeE + typeRC + typeRI);
		
		if("insert".equals(type)){
			stbean.setJobstate("1");
			insertScheduler(stbean);
		}else if("update".equals(type)){
			stbean.setJobstate(jobState);
			updateScheduler(stbean);
		}else if("delete".equals(type)){
			JobDetail job = null;
			if(stbean.getJobClass().indexOf("[AIJOB]")>-1){
				Class c=Class.forName("com.ivyinfo.common.framework.structure.quartz.JobEngine");	
				job = new JobDetail(stbean.getJobname(), stbean.getGroupid(), c);
				job.setDescription(stbean.getJobClass().substring(7,stbean.getJobClass().length()));
			}else{
				Class c=Class.forName(stbean.getJobClass());
				job = new JobDetail(stbean.getJobname(), stbean.getGroupid(), c);
			}
			job.setDescription(stbean.getJobClass().substring(7,stbean.getJobClass().length()));
			deleteJob(job, stbean.getGroupid());
			
			deleteScheduler(stbean);
		}
		return stbean;
	}
	
	
/**
 * 新增
 * @param stbean
 * @throws Exception
 */	
	private void insertScheduler(SchedulerTimeBean stbean)throws Exception {
		Sequence sequence = (Sequence) SpringContextUtil.getBean("Sequence");
		long id = sequence.getMaxId("t_sys_schedulertime");
		
		StringBuffer strSqlBuf = new StringBuffer();
		strSqlBuf.append("insert into t_sys_schedulertime ");
		strSqlBuf.append("(id,jobname,grouptype,groupid,jobclass,jobstate,jobtype,jobcronexpression,jobstarttime,jobendtime,jobrepeatcount,jobrepeatinterval) ");
		strSqlBuf.append("values (");
		strSqlBuf.append("?,?,?,?,?,?,  ?,?,?,?,?,?");
		strSqlBuf.append(")");

		Object[] params_inst = new Object[] {id,stbean.getJobname(),stbean.getGrouptype(),stbean.getGroupid(),stbean.getJobClass(),stbean.getJobstate(),stbean.getJobtype(),stbean.getJobCronExpression(),stbean.getJobStartTime(),stbean.getJobEndTime(),stbean.getJobRepeatCount(),stbean.getJobRepeatInterval()};
		int types_inst[] = new int[] {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(strSqlBuf.toString(), params_inst, types_inst); 
		
	}
	
	private void updateScheduler(SchedulerTimeBean stbean)throws Exception {
		StringBuffer strSqlBuf =  new StringBuffer();
		strSqlBuf.append("update t_sys_schedulertime set ");
		strSqlBuf.append("jobname = ?,");
		strSqlBuf.append("grouptype = ?,");
		strSqlBuf.append("groupid = ?,");
		strSqlBuf.append("jobclass = ?,");
		strSqlBuf.append("jobstate = ?,");
		strSqlBuf.append("jobtype = ?,");
		strSqlBuf.append("jobcronexpression = ?,");
		strSqlBuf.append("jobstarttime = ?,");
		strSqlBuf.append("jobendtime = ?,");
		strSqlBuf.append("jobrepeatcount = ?,");
		strSqlBuf.append("jobrepeatinterval = ? ");
		strSqlBuf.append(" where jobname = ?");

		Object[] params_inst = new Object[] {stbean.getJobname(),stbean.getGrouptype(),stbean.getGroupid(),stbean.getJobClass(),stbean.getJobstate(),stbean.getJobtype(),stbean.getJobCronExpression(),stbean.getJobStartTime(),stbean.getJobEndTime(),stbean.getJobRepeatCount(),stbean.getJobRepeatInterval(),stbean.getId()};
		int types_inst[] = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER};
		jdbcTemplate.update(strSqlBuf.toString(), params_inst, types_inst); 
	}
	
	private void deleteScheduler(SchedulerTimeBean stbean)throws Exception {
		StringBuffer strSqlBuf =  new StringBuffer();
		strSqlBuf.append("delete from t_sys_schedulertime ");
		strSqlBuf.append(" where jobname = '"+stbean.getJobname()+"'");
		jdbcTemplate.execute(strSqlBuf.toString());
	}
	
	
	public Date parseStringToDate(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
