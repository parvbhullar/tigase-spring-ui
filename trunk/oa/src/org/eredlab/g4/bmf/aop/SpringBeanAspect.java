package org.eredlab.g4.bmf.aop;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.bmf.base.IDao;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.properties.PropertiesFactory;
import org.eredlab.g4.ccl.properties.PropertiesFile;
import org.eredlab.g4.ccl.properties.PropertiesHelper;
import org.eredlab.g4.ccl.util.G4Utils;

/**
 * SpringBean监控切面
 * 
 * @author XiongChun
 * @since 2010-09-21
 */
public class SpringBeanAspect {
	
	private static Log log = LogFactory.getLog(SpringBeanAspect.class);

	/**
	 * Service方法调用通知
	 * synchronized:标记为同步方法主要是为处理开启切面监控时候造成死锁的问题.
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public synchronized Object doServiceAround(ProceedingJoinPoint pjp) throws Throwable {
		String activeTime = G4Utils.getCurrentTime();
		String clazzString = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String fullPath = clazzString + "." + methodName;
		int flag = clazzString.indexOf("$");
		if (flag < 0)
			log.info("开始业务处理[" + methodName + "];全路径[" + fullPath + "]");
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		if (flag < 0)
			log.info("结束业务处理[" + methodName + "];耗时:" + time + "毫秒;全路径[" + fullPath + "]");
		Dto mDto = new BaseDto();
		mDto.put("activetime", activeTime);
		mDto.put("advisetype", ArmConstants.ADVISETYPE_CALL);
		mDto.put("clazz", clazzString);
		mDto.put("costtime", new BigDecimal(time));
		mDto.put("exception", null);
		mDto.put("methodname", methodName);
		mDto.put("pointcuttype", ArmConstants.POINTCUTTYPE_BPO);
		if (flag < 0)
			saveEabeanMonitorDomain(mDto);
		return retVal;
	}

	/**
	 * Service方法异常通知
	 * synchronized:标记为同步方法主要是为处理开启切面监控时候造成死锁的问题.
	 * @param jp
	 * @param ex
	 */
	public synchronized void doServiceThrowing(JoinPoint jp, Throwable ex) {
		String activeTime = G4Utils.getCurrentTime();
		String clazzString = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String fullPath = clazzString + "." + methodName;
		int flag = clazzString.indexOf("$");
		if (flag < 0) {
			log.info("业务处理时发生了异常:[" + fullPath + "]");
			ex.printStackTrace();
		}
		Dto mDto = new BaseDto();
		mDto.put("activetime", activeTime);
		mDto.put("advisetype", ArmConstants.ADVISETYPE_CATCH);
		mDto.put("clazz", clazzString);
		mDto.put("costtime", new BigDecimal(0));
		mDto.put("exception", ex.getMessage());
		mDto.put("methodname", methodName);
		mDto.put("pointcuttype", ArmConstants.POINTCUTTYPE_BPO);
		if (flag < 0)
			saveEabeanMonitorDomain(mDto);
	}

	/**
	 * 持久化监控信息
	 * 
	 * @param pEabeanMonitorDomain
	 */
	private void saveEabeanMonitorDomain(Dto pDto) {
		PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);
		String value = pHelper.getValue("beanMonitor", "1");
		if (value.equals("1")) {
			String monitorid = IDHelper.getMonitorID();
			pDto.put("monitorid", monitorid);
			IDao g4Dao = (IDao)SpringBeanLoader.getSpringBean("g4Dao");
			g4Dao.insert("Monitor.saveEaBeanMonitorDomain", pDto);
		}
	}
}
