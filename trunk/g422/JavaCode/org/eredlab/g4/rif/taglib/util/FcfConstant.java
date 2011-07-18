package org.eredlab.g4.rif.taglib.util;

import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;


/**
 * FlashReport常量表
* @author njdt
 * @since 2010-01-13
 */
public class FcfConstant {
	/**
	 * 2D柱状图
	 */
	private static final String COLUMN_2D = "2DC";
	
	/**
	 * 3D柱状图
	 */
	private static final String COLUMN_3D = "3DC";
	
	/**
	 * 2D柱状组合图
	 */
	private static final String COLUMN_2D_MS = "2DC_MS";
	
	/**
	 * 3D柱状组合图
	 */
	private static final String COLUMN_3D_MS = "3DC_MS";
	
	/**
	 * 2D折线柱状交叉图
	 */
	private static final String LINE_COLUMN_2D_MS = "2DLC_MS";
	
	/**
	 * 3D折线柱状交叉图
	 */
	private static final String LINE_COLUMN_3D_MS = "3DLC_MS";
	
	/**
	 * 折线图
	 */
	private static final String LINE = "L";
	
	/**
	 * 折线组合图
	 */
	private static final String LINE_MS = "L_MS";
	
	/**
	 * 2D饼图
	 */
	private static final String PIE_2D = "2DP";
	
	/**
	 * 3D饼图
	 */
	private static final String PIE_3D = "3DP";
	
	/**
	 * 面积图
	 */
	private static final String AREA = "A";
	
	/**
	 * 面积组合图
	 */
	private static final String AREA_MS = "A_MS";
	
	/**
	 * 环状图
	 */
	private static final String CIRCULARITY = "C";
	
	/**
	 * 漏斗图
	 */
	private static final String FUNNEL = "F";
	
	/**
	 * 2D栏位图
	 */
	private static final String BAR_2D = "2DB";
	
	/**
	 * 2D栏位组合图
	 */
	private static final String BAR_2D_MS = "2DB_MS";
	
	
	
	private static Dto typeDto;
	
	static{
		typeDto = new BaseDto();
		typeDto.put(COLUMN_2D, "FCF_Column2D.swf");
		typeDto.put(COLUMN_3D, "FCF_Column3D.swf");
		typeDto.put(LINE, "FCF_Line.swf");
		typeDto.put(AREA, "FCF_Area2D.swf");
		typeDto.put(PIE_2D, "FCF_Pie2D.swf");
		typeDto.put(PIE_3D, "FCF_Pie3D.swf");
		typeDto.put(CIRCULARITY, "FCF_Doughnut2D.swf");
		typeDto.put(FUNNEL, "FCF_Funnel.swf");
		typeDto.put(COLUMN_2D_MS, "FCF_MSColumn2D.swf");
		typeDto.put(COLUMN_3D_MS, "FCF_MSColumn3D.swf");
		typeDto.put(AREA_MS, "FCF_MSArea2D.swf");
		typeDto.put(LINE_MS, "FCF_MSLine.swf");
		typeDto.put(LINE_COLUMN_2D_MS, "FCF_MSColumn2DLineDY.swf");
		typeDto.put(LINE_COLUMN_3D_MS, "FCF_MSColumn3DLineDY.swf");
		typeDto.put(BAR_2D, "FCF_Bar2D.swf");
		typeDto.put(BAR_2D_MS, "FCF_MSBar2D.swf");
	}
	
	/**
	 * 获取报表模板
	 * @param pType
	 * @return
	 */
	public static String getReportModel(String pType){
		return typeDto.getAsString(pType);
	}
}
