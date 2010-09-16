package com.linkage.app.gqt.backstage.sysparam.entity;
/**
 * 技能积分表
 *
 *
 * @author jiale.wang
 *
 * @create on 2010-8-27 下午02:03:07
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class JnPoint {
	private long id;
	private long paramid;
	private String paramkey;
	private long point;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParamid() {
		return paramid;
	}
	public void setParamid(long paramid) {
		this.paramid = paramid;
	}
	public String getParamkey() {
		return paramkey;
	}
	public void setParamkey(String paramkey) {
		this.paramkey = paramkey;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(long point) {
		this.point = point;
	}
	
	
}
