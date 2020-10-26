package com.cognizant.truyum.dao;

import java.sql.SQLException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public interface MenuItemDao {
	
	public  List<MenuItem> getMenuItemListAdmin() throws ClassNotFoundException, SQLException;
	public  List<MenuItem> getMenuItemListCustomer() throws ClassNotFoundException, SQLException;
	public  void modifyMenuItem(MenuItem menuItem)throws ClassNotFoundException, SQLException;
	public  MenuItem getMenuItem(long menuItemId)throws ClassNotFoundException, SQLException;

}
