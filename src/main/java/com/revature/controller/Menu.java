package com.revature.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.revature.exception.MyCustomException;

//this class holds the initial menu to :
//1. sign up an account 
//2. to log in to an account
//3. exit? make exit return to login instead of just shutting down the console.
public class Menu extends Input {
	public Menu() {
		super();
	}

	Input input = new Input();

	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
	Date date = new Date(System.currentTimeMillis());

	public void initialMenu() throws SQLException {

		int choice;
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to your Bank");
		System.out.println("Today's date and time: " + formatter.format(date) + "\n");
		System.out.println("1.) To Sign up for a new user account");
		System.out.println("2.) To Log in to an exisiting account");
		System.out.println("3.) To access as Administrator");
		System.out.println("4.) To exit the program");
		System.out.println("Please make a selection");

		do {
			choice = scan.nextInt();

			switch (choice) {
			case 1:
				createUserAccount();
				System.out.println("Now select 2 to log in to your newly created account: ");
				break;
			case 2:
				try {
					input.userLogin();
				} catch (MyCustomException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				Scanner in = new Scanner(System.in);
				com.revature.controller.AdminController.adminSession(in);
				break;
			case 4:
				System.out.println("Thank you and See you soon");
				break;
			default:
				System.out.println("enter a choice from 1 to 3");
			}
		} while (choice != 4);
	}

}
