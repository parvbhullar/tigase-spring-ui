package org.eredlab.g4.rif.report.fcf;

import java.util.HashMap;
import java.util.List;

import org.eredlab.g4.rif.web.BaseAction;

/**
 * FlashReport组合图数据实体
 * 
 * @author XiongChun
 * @since 2010-07-13
 * @see BaseAction
 */
public class DataSet extends HashMap {
	/**
	 * 设置组名
	 * @param seriesname
	 */
	public void setSeriesname(String seriesname) {
		this.put("seriesname", seriesname);
	}
	
	/**
	 * 设置组颜色
	 * @param color
	 */
	public void setColor(String color) {
		this.put("color", color);
	}
	
	/**
	 * 设置分组数据
	 * @param list
	 */
	public void setData(List list) {
		this.put("dataList", list);
	}
	
	/**
	 * 设置面积透明度<br>
	 * <b>只适用面积组合图
	 * @param pAreaAlpha
	 */
	public void setAreaAlpha(String pAreaAlpha){
		this.put("areaAlpha", pAreaAlpha);
	}
	
	/**
	 * 设置是否显示面积边框<br>
	 * <b>只适用面积组合图
	 * @param pShowAreaBorder
	 */
	public void setShowAreaBorder(Boolean pShowAreaBorder){
		this.put("showAreaBorder", pShowAreaBorder.booleanValue() ? "1" : "0");
	}
	
	/**
	 * 设置面积边框粗细<br>
	 * <b>只适用面积组合图
	 * @param pAreaBorderThickness
	 */
	public void setAreaBorderThickness(String pAreaBorderThickness){
		this.put("areaBorderThickness", pAreaBorderThickness);
	}
	
	/**
	 * 设置面积边框颜色<br>
	 * <b>只适用面积组合图
	 * @param pAreaBorderThickness
	 */
	public void setAreaBorderColor(String pAreaBorderColor){
		this.put("areaBorderColor", pAreaBorderColor);
	}
	
	/**
	 * 设置是否显示数据
	 * @param pAreaBorderThickness
	 */
	public void setShowValues(Boolean pShowValues){
		this.put("showValues", pShowValues.booleanValue() ? "1" : "0");
	}
	
	/**
	 * 设置是否为总线<br>
	 * <b>只适用于交叉图
	 * @param pAreaBorderThickness
	 */
	public void setParentYAxis(Boolean parentYAxis){
		this.put("parentYAxis", parentYAxis.booleanValue() ? "S" : "");
	}
	
	
	
    /**
     * 获取分组数据
     * @return List
     */
	public List getData() {
		return (List)this.get("dataList");
	}
}
