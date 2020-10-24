package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private List<MenuItem> menuItemList;
	
	public MenuItemDaoCollectionImpl() throws Exception
	{
		if(menuItemList==null)
		{
			menuItemList=new ArrayList<>();
			
			menuItemList.add(new MenuItem(000001, "Sandwich", 99.00f, true,
					DateUtil.convertToDate("15/03/2017"), "Main Course",
					true));
			menuItemList.add(new MenuItem(000002, "Burger", 129.00f, true,
					DateUtil.convertToDate("23/12/2017"), "Main Course",
					false));
			menuItemList.add(new MenuItem(000003, "Pizza", 149.00f, true,
					DateUtil.convertToDate("21/08/2018"), "Main Course",
					false));
			menuItemList.add(new MenuItem(000004, "French Fries", 200.00f,
					true, DateUtil.convertToDate("02/07/2017"),
					"Main Course", false));
			menuItemList.add( new MenuItem(000005, "Choclate Brownie", 32.00f,
					true, DateUtil.convertToDate("02/11/2022"), "Dessert",
					true));
		}
	}
	
	public  List<MenuItem> getMenuItemListAdmin()
	{
		return menuItemList;
	}
	public  List<MenuItem> getMenuItemListCustomer()
	{
		List<MenuItem> menuItemListCust=new ArrayList<>();
		Date todayDate = new Date();
		
		for(MenuItem menu:menuItemList)
		{
			if (menu.getDateOfLaunch().getTime() <= todayDate.getTime()
					&& menu.isActive()) {
				menuItemListCust.add(menu);
			}
		}
		return menuItemListCust;
	}
	public  void modifyMenuItem(MenuItem menuItem)
	{
		for (MenuItem matchingMenuItem: menuItemList) {

			if (menuItem.getId() == matchingMenuItem.getId()) {

				matchingMenuItem.setName(menuItem.getName());
				matchingMenuItem.setCategory(menuItem.getCategory());
				matchingMenuItem.setDateOfLaunch(menuItem
						.getDateOfLaunch());
				matchingMenuItem.setFreeDelivery(menuItem
						.isFreeDelivery());
				matchingMenuItem.setPrice(menuItem.getPrice());
				matchingMenuItem.setActive(menuItem.isActive());
			}

		}
	}
	
	public  MenuItem getMenuItem(long menuItemId)
	{
		for(MenuItem menu: menuItemList)
		{
			if(menuItemId==menu.getId())
				return menu;
		}
		
		return null;	
	}

		
}
