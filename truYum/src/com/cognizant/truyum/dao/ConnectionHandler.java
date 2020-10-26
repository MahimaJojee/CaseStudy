package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {

	public static Connection connection = null;
	private static Properties properties = new Properties();

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		try {
			FileInputStream fis = new FileInputStream("connection.properties");
			properties.load(fis);

			Class.forName(properties.getProperty("driver"));

			connection = DriverManager.getConnection(properties.getProperty("connection-url"), properties.getProperty("user"),
					properties.getProperty("password"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return connection;
	}
}