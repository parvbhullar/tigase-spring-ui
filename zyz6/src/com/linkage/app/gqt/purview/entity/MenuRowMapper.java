package com.linkage.app.gqt.purview.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MenuRowMapper implements RowMapper<Menu>{
	
	/*MENUID	NUMBER(16)	N			
	PARENTMENUID	NUMBER(16)	N	0		
	MENUNAME	VARCHAR2(50)	N			
	MENUORDER	NUMBER(16)	N			
	URL	VARCHAR2(255)	Y			
	MENUSTATE	NUMBER(1)	Y			
	MENUTYPE	NUMBER(1)	Y*/
	
	public Menu mapRow(ResultSet rs, int arg1) throws SQLException {
		Menu menu=new Menu();
		menu.setMenuId(rs.getLong("MENUID"));
		menu.setParentMenuId(rs.getLong("PARENTMENUID"));
		menu.setMenuName(rs.getString("MENUNAME"));
		menu.setMenuOrder(rs.getLong("MENUORDER"));
		menu.setUrl(rs.getString("URL"));
		menu.setMenuState(rs.getInt("MENUSTATE"));
		menu.setLevel(rs.getInt("LEVEL"));
		return menu;
	}
}
