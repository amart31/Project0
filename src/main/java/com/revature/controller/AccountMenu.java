package com.revature.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.daoimpl.AccountDaoImpl;
import com.revature.exception.MyCustomException;
import com.revature.model.Account;

public class AccountMenu extends Input {
	double lastAmount;
	double balance;

	public AccountMenu() {
		super();
	}

	public AccountMenu(double lastAmount, double balance) {
		this.lastAmount = lastAmount;
		this.balance = balance;
	}

	public double deposit(double amount) {
		if (amount != 0) {
			balance += amount;
			lastAmount = amount;
		}
		return balance;
	}

	public double withdraw(double amount) {
		if (amount != 0) {
			balance -= amount;
			lastAmount = amount;
		}
		return balance;
	}

	public void accountMenuOtions(String username) throws SQLException, MyCustomException {

		AccountDaoImpl adi = new AccountDaoImpl();

		if (adi.getAccountsByID(username).isEmpty()) {
			System.out.println("Create an account with us \n ");
			createNewBankAccount();
		}

		System.out.println("Enter an account number to access it: ");
		Scanner scan = new Scanner(System.in);
		int accountNumber = scan.nextInt();
		Account a = adi.selectAccount(accountNumber, username);

		balance = adi.getBalance(accountNumber);

		int choice;

		double depositAmount = 0;
		double withdrawAmount = 0;

		System.out.println("Welcome to your bank\n");
		System.out.println("input a number for each choice");
		System.out.println("1.) To view your account's balance.");
		System.out.println("2.) To make a deposit to an account.");
		System.out.println("3.) To make a withdraw from an account");
		System.out.println("4.) To create a new account.");
		System.out.println("5.) To view all your accounts.");
		System.out.println("6.) To select an account.");
		System.out.println("7.) To delete an account");
		System.out.println("8.) To exit the app");

		do {

			System.out.println("your selection: ");
			choice = scan.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Your account's balance is: " + balance);
				break;
			case 2:
				System.out.println("Account number: " + a.getAccountNumber() + " balance: " + a.getBalance() + " type: "
						+ a.getType());
				System.out.println("Enter an amount to deposit: ");
				while (!(scan.hasNextDouble())) {
					System.out.println("You must enter a numeric value, try again.");
					scan.next();
				}
				depositAmount = scan.nextDouble();
				if (depositAmount > 0) {

					a.setBalance(deposit(depositAmount));
					adi.updateAccount(a);

					System.out.println(
							"Your account number: " + a.getAccountNumber() + " new balance is: " + a.getBalance());
				} else {
					System.out.println("You attemtepted to deposit an invalid amount. Try again.");
				}
				break;
			case 3:
				System.out.println("Account number: " + a.getAccountNumber() + " balance: " + a.getBalance() + " type: "
						+ a.getType());
				System.out.println("Enter an amount to withdraw: ");

				while (!(scan.hasNextDouble())) {
					System.out.println("Amount must be a valid number, try again.");
					scan.next();
				}
				withdrawAmount = scan.nextDouble();

				if (withdrawAmount < 0) {
					System.out.println("You entered a negative amount. Try again.");
				} else if (withdrawAmount > balance) {
					System.out.println("You attempted to withdraw more than your balance");

				} else {

					a.setBalance(withdraw(withdrawAmount));
					adi.updateAccount(a);

					System.out.println(
							"Your account number: " + a.getAccountNumber() + " new balance is: " + a.getBalance());
				}
				break;
			case 4:
				createNewBankAccount();
				break;
			case 5:
				System.out.println("These are your available accounts: \n");
				adi.getAccountsByID(username);
				break;
			case 6:
				System.out.println("enter an account number to select that account: ");

				int acct_number = scan.nextInt();

				Account account = adi.selectAccount(acct_number, username);

				adi.updateAccount(account);
				break;
			case 7:
				System.out.println("select an account to delete: ");
				int number = scan.nextInt();
				Account accountToDelete = adi.selectAccount(number, username);
				if (accountToDelete.getBalance() != 0) {
					System.out.println("Account must have a balance of 0 to be deleted.");
				} else {
					adi.deleteAccount(accountToDelete);
				}

				break;
			case 8:
				System.out.println("Thank you for letting us service you, see you soon.");
				break;
			default:
				System.out.println("Please enter a valid number ranging from 1-7");
			}
		} while (choice != 8);
		Account account = new Account();
		account.setId(username);
		account.setBalance(balance);

		adi.updateAccount(account);
	}
}
