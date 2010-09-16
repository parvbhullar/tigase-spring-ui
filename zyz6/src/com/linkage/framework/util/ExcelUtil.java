package com.linkage.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * excel工具类
 * (提供excel读取方法)
 *
 * @author jiale.wang
 *
 * @create on 2009-8-10 下午02:33:37
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class ExcelUtil {
	/**
	 * Excel读取方法
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static List<Object[]> readExcel(String path)
	{
		InputStream is = null;
		Workbook rwb = null;
		try{
			is = new FileInputStream(new File(path));
			rwb = Workbook.getWorkbook(is);
			Sheet st = rwb.getSheet(0);
			if(st!=null){
				int rows = st.getRows();
				int cols = st.getColumns();
				List<Object[]> list = new ArrayList<Object[]>();
				for(int i=0;i<rows;i++)
				{
					if(i>0){
						Object[] obj = new Object[cols];
						for (int j = 0; j < cols; j++) {
							obj[j] = st.getCell(j, i).getContents();
						}
						list.add(obj);
					}
				}
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			rwb.close();
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Excel导出
	 * @param os
	 * @param title
	 * @param lists
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void writeExcel(OutputStream os, String[] title, List lists) 
						throws IOException, RowsExceededException, WriteException  {
		// 创建可以写入的Excel工作薄(默认运行生成的文件在tomcat/bin下 )
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		// 生成工作表,(name:First Sheet,参数0表示这是第一页)
		WritableSheet sheet = wwb.createSheet("First Sheet", 0);		  
	  	// 开始写入第一行(即标题栏)
        for (int i=0; i<title.length; i++) {
            // 用于写入文本内容到工作表中去
            Label label = null;
            // 在Label对象的构造中指明单元格位置(参数依次代表列数、行数、内容 )      
            label = new Label(i, 0, title[i]);
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }
        // 开始写入内容
        for (int row=0; row<lists.size(); row++) {        
             // 获取一条(一行)记录
             List list = (List) lists.get(row);
             // 数据是文本时(用label写入到工作表中)
             for (int col=0; col<list.size(); col++) {                
               String listvalue = "";
               if(null != list.get(col)){
            	   listvalue = (String)list.get(col);
               }
               Label label = null;
               label = new Label(col, row+1, listvalue);
                     sheet.addCell(label);            
             }   
        } 		    
		/*
	        生成一个保存数字的单元格,必须使用Number的完整包路径,否则有语法歧义,值为789.123
	       jxl.write.Number number = new jxl.write.Number(col, row, 555.12541);
	       sheet.addCell(number);
		 */

		 /*
		    生成一个保存日期的单元格,必须使用DateTime的完整包路径,否则有语法歧义,值为new Date()
		    jxl.write.DateTime date = new jxl.write.DateTime(col, row, new java.util.Date());
		    sheet.addCell(date);
		  */
		  // 写入数据
		  wwb.write();
		  // 关闭文件
		  wwb.close();
		  // 关闭输出流
		  os.close();
	}
	
	/**
	  * 向客户端下载文件,弹出下载框.
	  * 
	  * @param response(HttpServletResponse)
	  * @param file(需要下载的文件)
	  * @param isDel(下载完成后是否删除该文件)
	  * @throws IOException 
	  */
	 public static void exportFile(HttpServletResponse response, 
			 						File file, boolean isDel) throws IOException {
	  OutputStream out = null;
	  InputStream in = null;
	 
//	  // 获得文件名
	  String filename = URLEncoder.encode(file.getName(), "UTF-8"); 
//	  // 定义输出类型(下载)
	  response.setContentType("application/force-download");
	  response.setHeader("Location", filename);
//	  // 定义输出文件头
	  response.setHeader("Content-Disposition", "attachment;filename=" + filename); 
	  // 取得输出流
	  out = response.getOutputStream();
	  in = new FileInputStream(file.getPath());
	    
	  byte[] buffer = new byte[1024];
	  int i = -1;
	  while ((i = in.read(buffer)) != -1) {
	    out.write(buffer, 0, i);
	  }
	  out.flush();
	  in.close();
	  out.close();	  
	  if (isDel) {
		  //删除文件,删除前关闭所有的Stream.
		  file.delete();
	  }	  
	}
}
