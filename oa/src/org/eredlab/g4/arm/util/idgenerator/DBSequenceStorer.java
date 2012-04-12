package org.eredlab.g4.arm.util.idgenerator;

import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.id.SequenceStorer;
import org.eredlab.g4.ccl.id.StoreSequenceException;

/**
 * ID数据库逻辑存储器
 * @author XiongChun
 * @since 2010-03-21
 */
public class DBSequenceStorer implements SequenceStorer{
	
	private IDao g4Dao = (IDao)SpringBeanLoader.getSpringBean("g4Dao");
	
	/**
	 * 返回当前最大序列号
	 */
	public long load(String pIdColumnName) throws StoreSequenceException {
		Dto dto = new BaseDto();
		dto.put("fieldname", pIdColumnName);
		dto = (BaseDto)g4Dao.queryForObject("IdGenerator.getEaSequenceByFieldName", dto);
		Long maxvalue = dto.getAsLong("maxid");
		return maxvalue.longValue();
	}
	
	/**
	 * 写入当前生成的最大序列号值
	 */
	public void  updateMaxValueByFieldName(long pMaxId, String pIdColumnName) throws StoreSequenceException {
		Dto dto = new BaseDto();
		dto.put("maxid", String.valueOf(pMaxId));
		dto.put("fieldname", pIdColumnName);
		g4Dao.update("IdGenerator.updateMaxValueByFieldName", dto);
	}
}
