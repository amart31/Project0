package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Account;

public interface AccountDao {

	// public void insertAccount(Account account, User user) throws SQLException;

	public List<Account> getAllAccounts() throws SQLException;

	public List<Account> getAccountsByID(String acct_id) throws SQLException;

	public Account selectAccount(int acct_number, String username) throws SQLException;
	
	public int createAccount(Account account) throws SQLException;

	public void updateAccount(Account account) throws SQLException;

	public int deleteAccount(Account account) throws SQLException;
	
	public double getBalance(int accountNumber) throws SQLException;

}
