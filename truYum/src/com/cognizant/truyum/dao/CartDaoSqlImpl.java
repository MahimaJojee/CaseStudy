package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {
	
	
	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		List<MenuItem> menuItemList = new ArrayList<>();
		Cart cart = new Cart(menuItemList, 0);
		double total = 0;
		try {
			Connection connection = ConnectionHandler.getConnection();
			
			String query = "SELECT * FROM MENU_ITEMS WHERE ID IN (SELECT CT_MENU_ID FROM CART WHERE CT_USER_ID = ?)";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setLong(1, userId);
			
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				long id = resultSet.getLong(1);
				String name = resultSet.getString(2);
				float price = resultSet.getFloat(3);
				total += price;
				boolean active = resultSet.getInt(4)==1;
				Date dateOfLaunch = resultSet.getDate(5);
				String category = resultSet.getString(6);
				boolean freeDelivery = resultSet.getInt(7)==1;
				MenuItem menuItem = new MenuItem(id, name, price, active, dateOfLaunch, category, freeDelivery);
				menuItemList.add(menuItem);
			}
			ps.clearParameters();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cart.setMenuItemList(menuItemList);
		cart.setTotal(total);
		return menuItemList;
	}
	
	
	@Override
	public void addCartItem(long userId, long menuItemId) throws ClassNotFoundException, SQLException {

			Connection connection = ConnectionHandler.getConnection();
			
			String query = "INSERT INTO CART(CT_USER_ID, CT_MENU_ID) VALUES (?, ?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("Query Successful");
			}else {
				System.out.println("Query Unsuccessful");
			}
			ps.clearParameters();
			
		
		
	}

	

	@Override
	public void removeCartItem(long userId, long menuItemId) throws ClassNotFoundException, SQLException {
		
	
			Connection connection = ConnectionHandler.getConnection();
			String query = "DELETE FROM CART WHERE CT_MENU_ID = ? AND CT_USER_ID = ?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, menuItemId);
			ps.setLong(2, userId);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("Query Successful");
			}else {
				System.out.println("Query Unsuccessful");
			}
			ps.clearParameters();
			
		
		
	}

}