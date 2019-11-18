package com.revature.driver;

import java.sql.SQLException;

import com.revature.controller.Menu;
import com.revature.daoimpl.AccountDaoImpl;
import com.revature.model.Account;
import com.revature.model.User;

public class Driver {
	public static void main(String[] args) throws SQLException {
		Menu session = new Menu();
		session.initialMenu();
		
	}

}
