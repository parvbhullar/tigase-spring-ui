package com.ivyinfo.framework.service.sequence;

import com.ivyinfo.framework.service.server.SpringContextUtil;

public class SequenceServices implements ISequence{
	private SequenceInterface sequenceDAO =(SequenceInterface) SpringContextUtil.getBean("Sequence");

	public long getMaxId(String tableName) throws Exception {
		return sequenceDAO.getMaxId(tableName);
	}
	
	
}
