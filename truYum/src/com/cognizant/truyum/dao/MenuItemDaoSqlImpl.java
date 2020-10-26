package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao{

	public List<MenuItem> getMenuItemListAdmin() throws ClassNotFoundException, SQLException
	{
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> menuItemList =new ArrayList<>();
		String query="select * from menu_item";
		PreparedStatement ps=connection.prepareStatement(query);
		ResultSet rs=ps.executeQuery();  
		while(rs.next())  
		{
			System.out.println(rs.getLong(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)); 
			menuItemList.add(new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3),
					rs.getInt(4)==1,
					rs.getDate(5),rs.getString(6),
					rs.getInt(7)==1));
		}	
		ps.clearParameters();
		connection.close();  
		return menuItemList;
	}
	
	public List<MenuItem> getMenuItemListCustomer() throws ClassNotFoundException, SQLException
	{
		Connection connection = ConnectionHandler.getConnection();
		List<MenuItem> menuItemList =new ArrayList<>();
		String query="select * from menu_item where active=true and dateOfLaunch < now() ";
		PreparedStatement ps=connection.prepareStatement(query);
		ResultSet rs=ps.executeQuery();  
		while(rs.next())  
		{
			System.out.println(rs.getLong(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)); 
			menuItemList.add(new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3),
					rs.getInt(4)==1,
					rs.getDate(5),rs.getString(6),
					rs.getInt(7)==1));
		}	
		ps.clearParameters();
		connection.close();  
		return menuItemList;
	}
	public  MenuItem getMenuItem(long menuItemId) throws ClassNotFoundException, SQLException
	{
		Connection connection = ConnectionHandler.getConnection();
		String query="select * from menu_item where id=?";
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setLong(1, menuItemId);
		ResultSet rs=ps.executeQuery();  
		//rs.next();
			System.out.println(rs.getLong(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)); 
			MenuItem menuItem = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3),
					rs.getInt(4)==1,
					rs.getDate(5),rs.getString(6),
					rs.getInt(7)==1);
	
		ps.clearParameters();
		connection.close();  
		return menuItem;
	}
	public void modifyMenuItem( MenuItem menuItem)throws ClassNotFoundException, SQLException
	{
		Connection connection = ConnectionHandler.getConnection();
		String query = "UPDATE MENU_ITEMS SET item_name = ?, PRICE = ?, ACTIVE = ?, DATE_OF_LAUNCH = ?, CATEGORY = ?, FREE_DELIVERY = ? WHERE ID = ?";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement ps = connection.prepareStatement(query);
		ps.clearParameters();
		ps.setString(1, menuItem.getName());
		ps.setFloat(2, menuItem.getPrice());
		ps.setBoolean(3, menuItem.isActive());
		ps.setString(4, format.format(menuItem.getDateOfLaunch()));
		ps.setString(5, menuItem.getCategory());
		ps.setBoolean(6, menuItem.isFreeDelivery());
		ps.setLong(7, menuItem.getId());
		
		if(ps.executeUpdate() > 0) {
			System.out.println("Query Successful");
		}else {
			System.out.println("Query Unsuccessful");
		}
		ps.clearParameters();
	}
}
