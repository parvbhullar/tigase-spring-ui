package com.ivyinfo.framework.service.quartz;

public class SchedulerTimeBean {
	
	private int id;
	private String jobname;
	private String grouptype;
	private String groupid;
	private String jobClass;
	private String jobstate; 
	private String jobtype; 
	private String jobCronExpression; 
	private String jobStartTime;
	private String jobEndTime; 
	private int jobRepeatCount; 
	private int jobRepeatInterval;
	
	
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getJobCronExpression() {
		return jobCronExpression;
	}
	public void setJobCronExpression(String jobCronExpression) {
		this.jobCronExpression = jobCronExpression;
	}
	public String getJobEndTime() {
		return jobEndTime;
	}
	public void setJobEndTime(String jobEndTime) {
		this.jobEndTime = jobEndTime;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public int getJobRepeatCount() {
		return jobRepeatCount;
	}
	public void setJobRepeatCount(int jobRepeatCount) {
		this.jobRepeatCount = jobRepeatCount;
	}
	public int getJobRepeatInterval() {
		return jobRepeatInterval;
	}
	public void setJobRepeatInterval(int jobRepeatInterval) {
		this.jobRepeatInterval = jobRepeatInterval;
	}
	public String getJobStartTime() {
		return jobStartTime;
	}
	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}
	public String getJobstate() {
		return jobstate;
	}
	public void setJobstate(String jobstate) {
		this.jobstate = jobstate;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
}
