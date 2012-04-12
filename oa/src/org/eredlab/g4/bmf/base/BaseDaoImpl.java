package org.eredlab.g4.bmf.base;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.PKey;
import org.eredlab.g4.ccl.datastructure.impl.BasePo;
import org.eredlab.g4.ccl.properties.PropertiesFactory;
import org.eredlab.g4.ccl.properties.PropertiesFile;
import org.eredlab.g4.ccl.properties.PropertiesHelper;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 数据访问实现类(封装后)<br>
 * 基于Spring对iBatis的支持机制实现,支持实体对象的数据操作
 * <b>为了简化Dao层面的开发,已经放弃这种封装方式！不推荐使用。<br>
 * 以后不推荐使用
 * 
 * @author XiongChun
 * @since 2009-07-21
 * @see org.springframework.orm.ibatis.support.SqlMapClientDaoSupport
 */
public class BaseDaoImpl extends SqlMapClientDaoSupport implements BaseDao {
	Log log = LogFactory.getLog(BaseDaoImpl.class);
	
	protected static PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);

	/**
	 * 由配置文件dao.xml传过来的参数
	 */
	private String domainName;

	/**
	 * 根据主键删除一条记录
	 * 
	 * @param PKey
	 *            主键
	 */
	public void insertDomain(BasePo domain) {
		super.getSqlMapClientTemplate().insert("insert" + getDomainName(), domain);
	}

	/**
	 * 插入一条记录
	 * 
	 * @param domain
	 *            要插入的实体对象
	 */
	public void deleteDomainByKey(PKey key) {
		// 对非空传参对象进行非空校验
		key.validateNullAble();
		super.getSqlMapClientTemplate().delete("delete" + getDomainName() + "ByKey", key);
	}

	/**
	 * 根据主键查询一条数据
	 * 
	 * @param PKey
	 *            主键
	 * @return Object 返回的实体对象
	 */
	public Object queryDomainByKey(PKey key) {
		// 对非空传参对象进行非空校验
		key.validateNullAble();
		return super.getSqlMapClientTemplate().queryForObject("query" + getDomainName() + "ByKey", key);
	}

	/**
	 * 根据Dto查询数据
	 * 
	 * @param dto
	 *            传入的Dto查询参数Dto对象
	 * @return List 返回的List记录集
	 */
	public List queryDomainsByDto(Dto dto) {
		List lst = super.getSqlMapClientTemplate().queryForList("query" + getDomainName() + "sByDto", dto);
		return lst;
	}
	
	/**
	 * 按分页查询
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public List queryForPage(String statementName, Dto qDto) {
		return super.getSqlMapClientTemplate().queryForList(statementName, qDto, qDto.getAsInteger("start").intValue(),
				qDto.getAsInteger("end").intValue());
	}

	/**
	 * 根据Key更新一条记录
	 * 
	 * @param domain
	 *            实体领域对象
	 */
	public void updateDomainByKey(BasePo domain) {
		PKey key = domain.getPk();
		// 对非空传参对象进行非空校验
		key.validateNullAble();
		super.getSqlMapClientTemplate().update("update" + getDomainName() + "ByKey", domain);
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
