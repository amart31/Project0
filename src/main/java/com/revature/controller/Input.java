package com.revature.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.daoimpl.AccountDaoImpl;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.exception.MyCustomException;
import com.revature.model.Account;
import com.revature.model.User;

//we use this class to manage user input and to:
//1. create user accounts
//2. manage login for users
//3. create a bank account for users.

public class Input extends UserDaoImpl {

	public Input() {
		super();
	}

	Scanner scan = new Scanner(System.in);

	User user = new User();

	public void createUserAccount() throws SQLException {
		String username;
		String password;
		String firstName;
		String lastName;

		System.out.println("Enter a userame you want to use: ");
		
		username = scan.nextLine();
		
		while (doesUsernameMatch(username)) {
			System.out.println("username already in use, try again: ");
			username = scan.nextLine();
		}
		user.setUsername(username);
		
		System.out.println("Enter your secret password: ");
		password = scan.nextLine();
		user.setPassword(password);
		
		System.out.println("Enter your first name: ");
		firstName = scan.nextLine();
		user.setFirstName(firstName);
		System.out.println("Enter your last name: ");
		lastName = scan.nextLine();
		user.setLastName(lastName);

		createUser(user);
	}

	public void userLogin() throws SQLException, MyCustomException {

		System.out.println("Enter your username: ");
		String username = scan.nextLine();
		
		while (!doesUsernameMatch(username)) {
			System.out.println("Username not found, try again: ");
			username = scan.nextLine();
		}
		
		System.out.println("Enter your password: ");
		String password = scan.nextLine();
		
		while (!doesPasswordMatch(password, username)) {
			System.out.println("Password does not match, try again: ");
			password = scan.nextLine();
		}
		
		AccountMenu acctMenu = new AccountMenu();
		acctMenu.accountMenuOtions(username);

	}

	//creates a new account after the user logs in or registers
	public void createNewBankAccount() throws SQLException {
		String username;
		double balance;
		String type;

		System.out.println("Enter your username: ");
		username = scan.nextLine();

		System.out.println("enter the type of account \"checking\" or \"savings\": ");
		type = scan.nextLine();

		System.out.println("Select an amount to deposit: ");
		balance = scan.nextDouble();

		Account account = new Account();

		account.setId(username);
		account.setBalance(balance);
		account.setType(type);

		AccountDaoImpl acdi = new AccountDaoImpl();
		acdi.createAccount(account);

		AccountMenu acctMenu = new AccountMenu();
		try {
			acctMenu.accountMenuOtions(username);
		} catch (MyCustomException e) {
					e.printStackTrace();
		}

	}

}
