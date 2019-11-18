package com.revature.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import com.revature.daoimpl.UserDaoImpl;
import com.revature.model.User;
import com.revature.util.ConnFactory;

public class AdminController {

	public static ConnFactory cf = ConnFactory.getInstance();

	public static void adminSession(Scanner in) throws SQLException {
		// generate date and current time

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());

		UserDaoImpl udi = new UserDaoImpl();

		System.out.println("Welcome Admin " + formatter.format(date));

		String adminUsername, adminPassword;

		System.out.println("Enter your username: ");
		adminUsername = in.nextLine();

		Properties pro = new Properties();
		int adminChoice;
		try {
			pro.load(new FileReader("admin.properties"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (adminUsername.equals(pro.getProperty("adminUsername"))) {
			System.out.println("Enter your password: ");

			adminPassword = in.nextLine();

			if (adminPassword.equals(pro.getProperty("adminPassword"))) {
				System.out.println("welcome Admin: ");

			} else {
				System.out.println("Wrong passowrd, try again.");
			}
		} else {
			System.out.println("Incorrect username, try again");
		}

		System.out.println("1.) to view users");
		System.out.println("2.) to create a new user");
		System.out.println("3.) to delete users");
		System.out.println("4.) to update users");
		System.out.println("5.)  to Log Out");

		do {
			System.out.println("enter a choice: ");
			adminChoice = in.nextInt();

			switch (adminChoice) {

			case 1:
				try {
					for (int i = 0; i < udi.getUsers().size(); i++) {
						System.out.println(udi.getUsers().get(i));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				Scanner input = new Scanner(System.in);
				String user_username, user_password, firstName, lastName;

				System.out.println("enter a username: ");
				user_username = input.nextLine();

				System.out.println("Enter your secret password: ");
				user_password = input.nextLine();

				System.out.println("Enter your first name: ");
				firstName = input.nextLine();

				System.out.println("Enter your last name: ");
				lastName = input.nextLine();

				User user = new User();
				user.setUsername(user_username);
				user.setPassword(user_password);
				user.setFirstName(firstName);
				user.setLastName(lastName);

				udi.createUser(user);

				System.out.println(udi.getUsers());

				break;

			case 3:
				System.out.println("Enter the username for the account you wish to delete: ");
				String usernameToDelete = in.nextLine();
				User u = udi.getUserByUsername(usernameToDelete);
				udi.deleteUser(u);
				System.out.println("User deleted");
				break;

			case 4:
				System.out.println("enter username for account you wish to update: ");
				String usernameAcctUpdate = in.nextLine();
				System.out.println(
						"1.) to update username \n 2.) to update password \n 3.) to update first name \n 4.) to update last name:");
				int updateChoice = in.nextInt();

				if (updateChoice == 1) {
					System.out.println("enter a new username: ");
					String newUsername = in.nextLine();
					u = udi.getUserByUsername(usernameAcctUpdate);
					udi.updateUsername(newUsername, usernameAcctUpdate);
					System.out.println(u.toString());
					
				} else if (updateChoice == 2) {
					System.out.println(udi.getUsers().toString() + "\n");
					System.out.println("enter the username of the user: ");
					String uname = in.nextLine();
					u = udi.getUserByUsername(uname);
					System.out.println("enter a new password: ");
					String newPassword = in.nextLine();
					udi.updatePassword(newPassword, uname);
					System.out.println(u.toString());
					
				} else if (updateChoice == 3) {
					System.out.println(udi.getUsers().toString() + "\n");
					System.out.println("enter the username of an user to update: ");
					String uname = in.nextLine();
					u = udi.getUserByUsername(uname);
					System.out.println("enter a new first name for the user: ");
					String newFirstName = in.nextLine();
					udi.updateFirstName(newFirstName, uname);
					System.out.println(u.toString());

				} else if (updateChoice == 4) {
					System.out.println(udi.getUsers().toString() + "\n");
					System.out.println("enter the username of an user to update: ");
					String uname = in.nextLine();
					u = udi.getUserByUsername(uname);
					System.out.println("enter a new last name for the user: ");
					String newLastName = in.nextLine();
					udi.updateFirstName(newLastName, uname);

				}
				break;

			case 5:
				System.out.println("Thank you for working on behalf of us.");
				break;
			}
		} while (adminChoice != 5);

	}

}
