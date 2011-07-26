package org.njdt.gg.bmf.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.njdt.gg.ccl.properties.PropertiesFactory;
import org.njdt.gg.ccl.properties.PropertiesFile;
import org.njdt.gg.ccl.properties.PropertiesHelper;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.exception.G4Exception;
import org.njdt.gg.ccl.exception.PrcException;
import org.njdt.gg.ccl.util.G4Utils;
import org.njdt.gg.ccl.util.GlobalConstants;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 数据访问实现类(原生)<br>
 * 基于Spring对iBatis的支持机制实现,支持自定义的数据操作
 * 
* @author njdt
 * @since 2009-07-23
 * @see org.springframework.orm.ibatis.support.SqlMapClientDaoSupport
 */
public class IDaoImpl extends SqlMapClientDaoSupport implements IDao {

	/**
	 * 插入一条记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            要插入的对象(map javaBean)
	 */
	public void insert(String statementName, Object parameterObject) {
		getSqlMapClientTemplate().insert(statementName, parameterObject);
	}

	/**
	 * 插入一条记录
	 * 
	 * @param SQL语句ID号
	 */
	public void insert(String statementName) {
		getSqlMapClientTemplate().insert(statementName, new BaseDto());
	}

	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public Object queryForObject(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 */
	public Object queryForObject(String statementName) {
		return getSqlMapClientTemplate().queryForObject(statementName, new BaseDto());
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public List queryForList(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().queryForList(statementName, parameterObject);
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 */
	public List queryForList(String statementName) {
		return getSqlMapClientTemplate().queryForList(statementName, new BaseDto());
	}

	/**
	 * 按分页查询
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public List queryForPage(String statementName, Dto qDto) {
		Integer start = qDto.getAsInteger("start");
		Integer end = qDto.getAsInteger("end");
		if (G4Utils.isEmpty(start) || G4Utils.isEmpty(end)) {
			try {
				throw new G4Exception(GlobalConstants.ERR_MSG_QUERYFORPAGE_STRING);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return getSqlMapClientTemplate().queryForList(statementName, qDto, start.intValue(), end.intValue());
	}

	/**
	 * 更新记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            更新对象(map javaBean)
	 */
	public int update(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}

	/**
	 * 更新记录
	 * 
	 * @param SQL语句ID号
	 */
	public int update(String statementName) {
		return getSqlMapClientTemplate().update(statementName, new BaseDto());
	}

	/**
	 * 删除记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            更新对象(map javaBean)
	 */
	public int delete(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().delete(statementName, parameterObject);
	}

	/**
	 * 删除记录
	 * 
	 * @param SQL语句ID号
	 */
	public int delete(String statementName) {
		return getSqlMapClientTemplate().delete(statementName, new BaseDto());
	}

	/**
	 * 调用存储过程<br>
	 * 存储过程成功返回标志缺省：appCode=1
	 * 
	 * @param prcName
	 *            存储过程ID号
	 * @param parameterObject
	 *            参数对象(入参、出参)
	 * @throws PrcException
	 *             存储过程调用异常
	 */
	public void callPrc(String prcName, Dto prcDto) throws PrcException {
//		PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);
//		String callPrcSuccessFlag = pHelper.getValue("callPrcSuccessFlag", "1");
		getSqlMapClientTemplate().queryForObject(prcName, prcDto);
//		if (G4Utils.isEmpty(prcDto.getAppCode()) || !prcDto.getAppCode().equals(callPrcSuccessFlag)) {
//			throw new PrcException(prcName, prcDto.getAppCode(), prcDto.getErrorMsg());
//		}
	}

	/**
	 * 调用存储过程<br>
	 * 存储过程成功返回标志自定义：appCode=successFlag(自定义的传入变量)
	 * 
	 * @param prcName
	 *            存储过程ID号
	 * @param parameterObject
	 *            参数对象(入参、出参)
	 * @param prcName
	 *            存储过程调用成功返回的成功代码值
	 * @throws PrcException
	 *             存储过程调用异常
	 */
	public void callPrc(String prcName, Dto prcDto, String successFlag) throws PrcException {
		getSqlMapClientTemplate().queryForObject(prcName, prcDto);
		if (G4Utils.isEmpty(prcDto.getAppCode()) || !prcDto.getAppCode().equals(successFlag)) {
			throw new PrcException(prcName, prcDto.getAppCode(), prcDto.getErrorMsg());
		}
	}

	/**
	 * 获取Connection对象<br>
	 * 说明：虽然向Dao消费端暴露了获取Connection对象的方法但不建议直接获取Connection对象进行JDBC操作
	 * 
	 * @return 返回Connection对象
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return getSqlMapClientTemplate().getDataSource().getConnection();
	}

	/**
	 * 获取DataSource对象<br>
	 * 说明：虽然向Dao消费端暴露了获取DataSource对象的方法但不建议直接获取DataSource对象进行JDBC操作
	 * 
	 * @return 返回DataSource对象
	 */
	public DataSource getDataSourceFromSqlMap() throws SQLException {
		return getSqlMapClientTemplate().getDataSource();
	}

	/**
	 * 获取SqlMapClientTemplate对象<br>
	 * 
	 * @return 返回SqlMapClientTemplate对象
	 */
	public SqlMapClientTemplate getSqlMapClientTpl() {
		return getSqlMapClientTemplate();
	}
}
