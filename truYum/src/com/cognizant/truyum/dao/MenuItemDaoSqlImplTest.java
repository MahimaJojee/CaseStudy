package com.cognizant.truyum.dao;

import java.sql.SQLException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {
	
	private static MenuItemDaoSqlImpl menuItemDao = new MenuItemDaoSqlImpl();
	
	public static void main(String[] args) throws Exception {
		System.out.println(" Inside MenuItemDaoImplTest Main");
		System.out.println("Admin List of MenuItems");
		testGetMenuItemListAdmin();
		System.out.println("Customer List of Menu Items");
		testGetMenuItemListCustomer();
		System.out.println("Modifying and printing MenuItem");
		testModifyMenuItem();
		testGetMenuItemListAdmin();
		System.out.println("Get Menu Item");
		testGetMenuItem();
		System.out.println("MenuItemDaoImplTest Completed");
	}
	
	public static void testGetMenuItemListAdmin() {
		List<MenuItem> itemAdminList;
		try {
			itemAdminList = menuItemDao.getMenuItemListAdmin();
			itemAdminList.forEach(System.out::println);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void testGetMenuItemListCustomer() throws ClassNotFoundException, SQLException {
		List<MenuItem> itemCustomerList = menuItemDao.getMenuItemListCustomer();
		itemCustomerList.forEach(System.out::println);
	}

	public static void testModifyMenuItem() throws Exception {
		MenuItem menuItem = new MenuItem(5, "Chocolate", 30.0f, true, new DateUtil().convertToDate("15/03/2017"), "Desert", true);
		menuItemDao.modifyMenuItem(menuItem);
	}
	
	public static void testGetMenuItem() throws ClassNotFoundException, SQLException {
		MenuItem menuItem = menuItemDao.getMenuItem(2);
		if(menuItem == null) {
			System.out.println("The Item does not exist in our database");
			return;
		}
		System.out.println(menuItem);
	}
}


