package org.njdt.gg.ccl.id;

/**
 * SequenceGenerator 此代码源于开源项目E3,原作者：黄云辉
 * 
* @author njdt
 * @since 2010-03-17
 */
public interface SequenceGenerator {
	public long next() throws CreateSequnceException;
}
