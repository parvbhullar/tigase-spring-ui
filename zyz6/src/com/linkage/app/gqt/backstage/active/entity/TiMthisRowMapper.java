package com.linkage.app.gqt.backstage.active.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.init.entity.OrgArea;

public class TiMthisRowMapper implements RowMapper<TiMthis>{
	/*
	  GATEWAYID     VARCHAR2(10),
	  USERTABLENAME VARCHAR2(32),
	  DSTID         VARCHAR2(64) not null,
	  SRCID         VARCHAR2(64),
	  FEEID         VARCHAR2(64),
	  MSGCONTENT    VARCHAR2(140),
	  LINKID        VARCHAR2(20),
	  SERVICEID     VARCHAR2(10),
	  RECORDTYPE    NUMBER(2),
	  COUNTFEE      NUMBER(16),
	  FIXEDFEE      NUMBER(16),
	  MESSAGE_ID    VARCHAR2(50),
	  COMMITTIME    DATE,
	  RETRYTIMES    NUMBER(2),
	  ACTID         NUMBER(8),
	  ACTNAME       VARCHAR2(6)
	  */
	public TiMthis mapRow(ResultSet rs, int arg1) throws SQLException {
		TiMthis tiMthis=new TiMthis();
		tiMthis.setGatewayid(rs.getString("GATEWAYID"));
		tiMthis.setUsertablename(rs.getString("USERTABLENAME"));
		tiMthis.setDstid(rs.getString("DSTID"));
		tiMthis.setSrcid(rs.getString("SRCID"));
		tiMthis.setFeeid(rs.getString("FEEID"));
		tiMthis.setMsgcontent(rs.getString("MSGCONTENT"));
		tiMthis.setLinkid(rs.getString("LINKID"));
		tiMthis.setServiceid(rs.getString("SERVICEID"));
		tiMthis.setRecordtype(rs.getLong("RECORDTYPE"));
		tiMthis.setCountfee(rs.getLong("COUNTFEE"));
		tiMthis.setFixedfee(rs.getLong("FIXEDFEE"));
		tiMthis.setMessageId(rs.getString("MESSAGE_ID"));
		tiMthis.setCommittime(rs.getDate("COMMITTIME"));
		tiMthis.setRetrytimes(rs.getLong("RETRYTIMES"));
		tiMthis.setActid(rs.getLong("ACTID"));
		tiMthis.setActName(rs.getString("ACTNAME"));
		return tiMthis;
	}
}
