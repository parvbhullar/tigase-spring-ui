package org.eredlab.g4.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.report.fcf.Categorie;
import org.eredlab.g4.rif.report.fcf.CategoriesConfig;
import org.eredlab.g4.rif.report.fcf.DataSet;
import org.eredlab.g4.rif.report.fcf.FcfDataMapper;
import org.eredlab.g4.rif.report.fcf.GraphConfig;
import org.eredlab.g4.rif.report.fcf.Set;
import org.eredlab.g4.rif.web.BaseAction;

/**
 * FlashReport标准范例暨教程Action
 * 
 * @author XiongChun
 * @since 2010-10-13
 * @see BaseAction
 */
public class FlashReportAction extends BaseAction {
	/**
	 * FCF 2D柱状图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcf2DColumnInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//X坐标轴名称
		graphConfig.setXAxisName("月度");
		//数字值前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《eRedG4开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); //柱状图颜色
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("2dColumnView");
	}
	
	/**
	 * FCF 3D柱状图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcf3DColumnInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); //柱状图颜色
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("3dColumnView");
	}
	
	/**
	 * FCF 折线图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcfLineInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		//设置水平分割线的颜色
		graphConfig.put("divLineColor", "008ED6");
		//设置水平分割线的透明度
		graphConfig.put("divLineAlpha", "20");
		//设置对水平分割区域使用斑马纹
		graphConfig.put("showAlternateHGridColor", "1");
		//设置斑马纹颜色
		graphConfig.put("AlternateHGridColor", "BFFFFF");
		//设置斑马纹的透明度
		graphConfig.put("alternateHGridAlpha", "20");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); //柱状图颜色
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("lineView");
	}
	
	/**
	 * FCF 2D饼图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcf2DPieInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		graphConfig.setShowNames(new Boolean(true));
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); //柱状图颜色
			set.setIsSliced(dto.getAsString("issliced"));//浮动
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("2dPieView");
	}
	
	/**
	 * FCF 3D饼图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcf3DPieInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); //柱状图颜色
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("3dPieView");
	}
	
	/**
	 * FCF 面积图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcfAreaInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//graphConfig.setShowValues(true);
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		//设置水平分割线的颜色
		graphConfig.put("divLineColor", "008ED6");
		//设置水平分割线的透明度
		graphConfig.put("divLineAlpha", "10");
		//设置对水平分割区域使用斑马纹
		graphConfig.put("showAlternateHGridColor", "1");
		//设置斑马纹颜色
		graphConfig.put("AlternateHGridColor", "BFFFFF");
		//设置斑马纹的透明度
		graphConfig.put("alternateHGridAlpha", "10");
		//graphConfig.put("areaAlpha", "60");
		//graphConfig.put("areaBorderColor", "441570");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			//set.setColor(dto.getAsString("color")); 
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("areaView");
	}
	
	/**
	 * FCF 环状图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcfCircularityInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		graphConfig.setShowNames(new Boolean(true));
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); 
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("circularityView");
	}
	
	/**
	 * FCF 漏斗图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcfFunnelInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        qDto.put("rownum", new Integer(7));
        //查询原始数据
		List list = null;
		if (G4Utils.isOracle()) {
			list = g4Reader.queryForList("getFcfDataList", qDto);
		}else if(G4Utils.isMysql()){
			list = g4Reader.queryForList("getFcfDataListMysql", qDto);
		}
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); 
			set.setAlpha("80");
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("funnelView");
	}
	
	/**
	 * 2D柱形组合图初始化
	 * 综合图和前面的单一图使用的元数据格式是不一样的,请大家注意它们的区别
	 * @param
	 * @return
	 */
	public ActionForward fcf2DColumnMsInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		graphConfig.setCanvasBorderThickness(new Boolean(true));
		//实例化组合种类配置对象
		CategoriesConfig categoriesConfig = new CategoriesConfig();
		List cateList = new ArrayList();
		cateList.add(new Categorie("一月"));
		cateList.add(new Categorie("二月"));
		cateList.add(new Categorie("三月"));
		cateList.add(new Categorie("四月"));
		cateList.add(new Categorie("五月"));
		cateList.add(new Categorie("六月"));
		categoriesConfig.setCategories(cateList);
		List list = getFcfDataList4Group(new BaseDto());
		String xmlString = FcfDataMapper.toFcfXmlData(list, graphConfig, categoriesConfig);
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("2dColumnMsView");
	}
	
	/**
	 * 3D柱形组合图初始化
	 * 综合图和前面的单一图使用的元数据格式是不一样的,请大家注意它们的区别
	 * @param
	 * @return
	 */
	public ActionForward fcf3DColumnMsInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		graphConfig.setCanvasBorderThickness(new Boolean(true));
		//实例化组合种类配置对象
		CategoriesConfig categoriesConfig = new CategoriesConfig();
		List cateList = new ArrayList();
		cateList.add(new Categorie("一月"));
		cateList.add(new Categorie("二月"));
		cateList.add(new Categorie("三月"));
		cateList.add(new Categorie("四月"));
		cateList.add(new Categorie("五月"));
		cateList.add(new Categorie("六月"));
		categoriesConfig.setCategories(cateList);
		List list = getFcfDataList4Group(new BaseDto());
		String xmlString = FcfDataMapper.toFcfXmlData(list, graphConfig, categoriesConfig);
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("3dColumnMsView");
	}
	
	/**
	 * 面积组合图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcfAreaMsInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		graphConfig.setCanvasBorderThickness(new Boolean(true));
		//实例化组合种类配置对象
		CategoriesConfig categoriesConfig = new CategoriesConfig();
		List cateList = new ArrayList();
		cateList.add(new Categorie("一月"));
		cateList.add(new Categorie("二月"));
		cateList.add(new Categorie("三月"));
		cateList.add(new Categorie("四月"));
		cateList.add(new Categorie("五月"));
		cateList.add(new Categorie("六月"));
		cateList.add(new Categorie("七月"));
		cateList.add(new Categorie("八月"));
		cateList.add(new Categorie("九月"));
		cateList.add(new Categorie("十月"));
		cateList.add(new Categorie("十一月"));
		cateList.add(new Categorie("十二月"));
		categoriesConfig.setCategories(cateList);
		List list = getFcfDataList4AreaGroup(new BaseDto());
		String xmlString = FcfDataMapper.toFcfXmlData(list, graphConfig, categoriesConfig);
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("areaMsView");
	}
	
	/**
	 * 折线组合图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcfLineMsInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		graphConfig.setCanvasBorderThickness(new Boolean(true));
		//实例化组合种类配置对象
		CategoriesConfig categoriesConfig = new CategoriesConfig();
		List cateList = new ArrayList();
		cateList.add(new Categorie("一月"));
		cateList.add(new Categorie("二月"));
		cateList.add(new Categorie("三月"));
		cateList.add(new Categorie("四月"));
		cateList.add(new Categorie("五月"));
		cateList.add(new Categorie("六月"));
		cateList.add(new Categorie("七月"));
		cateList.add(new Categorie("八月"));
		cateList.add(new Categorie("九月"));
		cateList.add(new Categorie("十月"));
		cateList.add(new Categorie("十一月"));
		cateList.add(new Categorie("十二月"));
		categoriesConfig.setCategories(cateList);
		List list = getFcfDataList4LineGroup(new BaseDto());
		String xmlString = FcfDataMapper.toFcfXmlData(list, graphConfig, categoriesConfig);
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("lineMsView");
	}
	
	/**
	 * 2D交叉图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcf2DLineColumnInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		graphConfig.setCanvasBorderThickness(new Boolean(true));
		//实例化组合种类配置对象
		CategoriesConfig categoriesConfig = new CategoriesConfig();
		List cateList = new ArrayList();
		cateList.add(new Categorie("一月"));
		cateList.add(new Categorie("二月"));
		cateList.add(new Categorie("三月"));
		cateList.add(new Categorie("四月"));
		cateList.add(new Categorie("五月"));
		cateList.add(new Categorie("六月"));
		cateList.add(new Categorie("七月"));
		cateList.add(new Categorie("八月"));
		cateList.add(new Categorie("九月"));
		cateList.add(new Categorie("十月"));
		cateList.add(new Categorie("十一月"));
		cateList.add(new Categorie("十二月"));
		categoriesConfig.setCategories(cateList);
		List list = getFcfDataList4JCT(new BaseDto());
		String xmlString = FcfDataMapper.toFcfXmlData(list, graphConfig, categoriesConfig);
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("2dLineColumnView");
	}
	
	/**
	 * 3D交叉图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcf3DLineColumnInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		graphConfig.setCanvasBorderThickness(new Boolean(true));
		//实例化组合种类配置对象
		CategoriesConfig categoriesConfig = new CategoriesConfig();
		List cateList = new ArrayList();
		cateList.add(new Categorie("一月"));
		cateList.add(new Categorie("二月"));
		cateList.add(new Categorie("三月"));
		cateList.add(new Categorie("四月"));
		cateList.add(new Categorie("五月"));
		cateList.add(new Categorie("六月"));
		cateList.add(new Categorie("七月"));
		cateList.add(new Categorie("八月"));
		cateList.add(new Categorie("九月"));
		cateList.add(new Categorie("十月"));
		cateList.add(new Categorie("十一月"));
		cateList.add(new Categorie("十二月"));
		categoriesConfig.setCategories(cateList);
		List list = getFcfDataList4JCT(new BaseDto());
		String xmlString = FcfDataMapper.toFcfXmlData(list, graphConfig, categoriesConfig);
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("3dLineColumnView");
	}
	
	/**
	 * FCF 交互图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcfAdvancedInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		graphConfig.setSubcaption("点击柱子看看交互效果");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "2");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); //柱状图颜色
			set.setJsFunction("fnMyJs(\"xiongchun\")"); //调用的JS函数
			//set.setLink("这里可以是一个下钻的url链接");
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("3dColumnView");
	}
	
	/**
	 * 获取FlashReport元数据 (交叉图)
	 * @param pDto
	 * @return
	 */
	private List getFcfDataList4JCT(Dto pDto){
		pDto.put("rownum", "12");
		List dataList = new ArrayList();
		DataSet dataSet1 = new DataSet();
		dataSet1.setSeriesname("产品A");
		dataSet1.setColor("FDC12E");
		dataSet1.setShowValues(new Boolean(false));
		pDto.put("product", "1");
		List alist = null;
		if (G4Utils.isOracle()) {
			alist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			alist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List aSetList = new ArrayList();
		for (int i = 0; i < alist.size(); i++) {
			Dto dto = (BaseDto)alist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			aSetList.add(set);
		}
		dataSet1.setData(aSetList);
		dataList.add(dataSet1);
		
		DataSet dataSet2 = new DataSet();
		dataSet2.setSeriesname("产品B");
		dataSet2.setColor("44BC2F");
		dataSet2.setShowValues(new Boolean(false));
		pDto.put("product", "2");
		List blist = null;
		if (G4Utils.isOracle()) {
			blist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			blist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List bSetList = new ArrayList();
		for (int i = 0; i < blist.size(); i++) {
			Dto dto = (BaseDto)blist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			bSetList.add(set);
		}
		dataSet2.setData(bSetList);
		dataList.add(dataSet2);
		
		DataSet dataSet3 = new DataSet();
		dataSet3.setSeriesname("合计");
		dataSet3.setColor("3CBBD7");
		dataSet3.setShowValues(new Boolean(false));
		dataSet3.setParentYAxis(new Boolean(true));
		List sumlist = g4Reader.queryForList("getFcfSumDataList", pDto);
		List sumSetList = new ArrayList();
		for (int i = 0; i < sumlist.size(); i++) {
			Dto dto = (BaseDto)sumlist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			sumSetList.add(set);
		}
		dataSet3.setData(sumSetList);
		dataList.add(dataSet3);
		return dataList;
	}
	
	/**
	 * 获取FlashReport元数据 (折线组合图)
	 * @param pDto
	 * @return
	 */
	private List getFcfDataList4LineGroup(Dto pDto){
		pDto.put("rownum", "12");
		List dataList = new ArrayList();
		DataSet dataSet1 = new DataSet();
		dataSet1.setSeriesname("产品A");
		dataSet1.setColor("FDC12E");
		pDto.put("product", "1");
		List alist = null;
		if (G4Utils.isOracle()) {
			alist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			alist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List aSetList = new ArrayList();
		for (int i = 0; i < alist.size(); i++) {
			Dto dto = (BaseDto)alist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			aSetList.add(set);
		}
		dataSet1.setData(aSetList);
		dataList.add(dataSet1);
		
		DataSet dataSet2 = new DataSet();
		dataSet2.setSeriesname("产品B");
		dataSet2.setColor("44BC2F");
		pDto.put("product", "2");
		List blist = null;
		if (G4Utils.isOracle()) {
			blist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			blist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List bSetList = new ArrayList();
		for (int i = 0; i < blist.size(); i++) {
			Dto dto = (BaseDto)blist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			bSetList.add(set);
		}
		dataSet2.setData(bSetList);
		dataList.add(dataSet2);
		return dataList;
	}
	
	/**
	 * 获取FlashReport元数据 (面积组合图)
	 * @param pDto
	 * @return
	 */
	private List getFcfDataList4AreaGroup(Dto pDto){
		pDto.put("rownum", "12");
		List dataList = new ArrayList();
		DataSet dataSet1 = new DataSet();
		dataSet1.setSeriesname("产品A");
		dataSet1.setColor("FDC12E");
		dataSet1.setAreaBorderColor("FDC12E");
		dataSet1.setAreaBorderThickness("1");
		dataSet1.setAreaAlpha("70");
		pDto.put("product", "1");
		List alist = null;
		if (G4Utils.isOracle()) {
			alist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			alist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List aSetList = new ArrayList();
		for (int i = 0; i < alist.size(); i++) {
			Dto dto = (BaseDto)alist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			aSetList.add(set);
		}
		dataSet1.setData(aSetList);
		dataList.add(dataSet1);
		
		DataSet dataSet2 = new DataSet();
		dataSet2.setSeriesname("产品B");
		dataSet2.setColor("56B9F9");
		dataSet2.setAreaBorderColor("56B9F9");
		dataSet2.setAreaBorderThickness("2");
		dataSet2.setAreaAlpha("50");
		pDto.put("product", "2");
		List blist = null;
		if (G4Utils.isOracle()) {
			blist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			blist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List bSetList = new ArrayList();
		for (int i = 0; i < blist.size(); i++) {
			Dto dto = (BaseDto)blist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			bSetList.add(set);
		}
		dataSet2.setData(bSetList);
		dataList.add(dataSet2);
		return dataList;
	}
	
	/**
	 * 获取FlashReport元数据 (柱状组合图)
	 * @param pDto
	 * @return
	 */
	private List getFcfDataList4Group(Dto pDto){
		pDto.put("rownum", "6");
		List dataList = new ArrayList();
		DataSet dataSet1 = new DataSet();
		dataSet1.setSeriesname("产品A");
		dataSet1.setColor("FDC12E");
		pDto.put("product", "1");
		List alist = null;
		if (G4Utils.isOracle()) {
			alist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			alist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List aSetList = new ArrayList();
		for (int i = 0; i < alist.size(); i++) {
			Dto dto = (BaseDto)alist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			aSetList.add(set);
		}
		dataSet1.setData(aSetList);
		dataList.add(dataSet1);
		
		DataSet dataSet2 = new DataSet();
		dataSet2.setSeriesname("产品B");
		dataSet2.setColor("56B9F9");
		pDto.put("product", "2");
		List blist = null;
		if (G4Utils.isOracle()) {
			blist = g4Reader.queryForList("getFcfDataList", pDto);
		}else if(G4Utils.isMysql()){
			blist = g4Reader.queryForList("getFcfDataListMysql", pDto);
		}
		List bSetList = new ArrayList();
		for (int i = 0; i < blist.size(); i++) {
			Dto dto = (BaseDto)blist.get(i);
			Set set = new Set();
			set.setValue(dto.getAsString("value"));
			bSetList.add(set);
		}
		dataSet2.setData(bSetList);
		dataList.add(dataSet2);
		return dataList;
	}
	
	/**
	 * FCF 2D栏位图初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward fcf2DBarInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//X坐标轴名称
		graphConfig.setXAxisName("月度");
		//数字值前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《eRedG4开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
        Dto qDto = new BaseDto();		
        qDto.put("product", "1");
        //查询原始数据
		List list = g4Reader.queryForList("getFcfDataList", qDto);
		List dataList = new ArrayList();
		//将原始数据对象转换为框架封装的Set报表数据对象
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			//实例化一个图表元数据对象
			Set set = new Set();
			set.setName(dto.getAsString("name")); //名称
			set.setValue(dto.getAsString("value")); //数据值
			set.setColor(dto.getAsString("color")); //柱状图颜色
			dataList.add(set);
		}
		//将图表数据转为Flash能解析的XML资料格式
		String xmlString = FcfDataMapper.toFcfXmlData(dataList, graphConfig);
		//此Key对应的<flashReport />标签的datavar属性
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("2dBarView");
	}
	
	/**
	 * 2D栏位组合图初始化
	 * 综合图和前面的单一图使用的元数据格式是不一样的,请大家注意它们的区别
	 * @param
	 * @return
	 */
	public ActionForward fcf2DBarMsInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//实例化一个图形配置对象
		GraphConfig graphConfig = new GraphConfig();
		//主标题
		graphConfig.setCaption("Google软件2010年月度销售业绩图表");
		//设置数字值的前缀
		graphConfig.setNumberPrefix("$");
		//使用这种方式可以加入框架没有封装的原生报表属性,原生属可以参考《开发指南》的相关章节
		//graphConfig.put("propertyName", "value");
		graphConfig.setCanvasBorderThickness(new Boolean(true));
		//实例化组合种类配置对象
		CategoriesConfig categoriesConfig = new CategoriesConfig();
		List cateList = new ArrayList();
		cateList.add(new Categorie("一月"));
		cateList.add(new Categorie("二月"));
		cateList.add(new Categorie("三月"));
		cateList.add(new Categorie("四月"));
		cateList.add(new Categorie("五月"));
		cateList.add(new Categorie("六月"));
		categoriesConfig.setCategories(cateList);
		List list = getFcfDataList4Group(new BaseDto());
		String xmlString = FcfDataMapper.toFcfXmlData(list, graphConfig, categoriesConfig);
		request.setAttribute("xmlString", xmlString);
		return mapping.findForward("2dBarMsView");
	}
}
