package org.njdt.gg.ccl.id.sequence;

import org.njdt.gg.ccl.id.CreateSequnceException;

/**
 * AbstractRollingSequenceGenerator
 * 此代码源于开源项目E3,原作者：黄云辉
 * 
* @author njdt
 * @since 2010-03-17
 * @see DefaultSequenceGenerator
 */
public abstract class AbstractRollingSequenceGenerator extends DefaultSequenceGenerator{
	
	public long next() throws CreateSequnceException {
		if ( isResetCount() ){
			this.currCount = this.minValue;
			maxCount = this.currCount;
			sequenceStorer.updateMaxValueByFieldName(maxCount, this.getId());
		}
		return super.next();
	}
	
   abstract protected boolean isResetCount();

}
