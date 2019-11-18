package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.model.Account;
import com.revature.util.ConnFactory;

public class AccountDaoImpl implements AccountDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	public List<Account> getAllAccounts() throws SQLException {
		List<Account> accountsList = new ArrayList<Account>();

		Connection conn = cf.getConnection();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");

		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(1), rs.getDouble(2), rs.getString(3));
			accountsList.add(a);
		}
		return accountsList;
	}

	public List<Account> getAccountsByID(String acct_id) throws SQLException {
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();

		String sql = "SELECT * FROM accounts WHERE acct_id = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, acct_id);
		ResultSet rs = ps.executeQuery();

		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
			accountList.add(a);

		}
		System.out.println(accountList + "\n");
		return accountList;

	}

	public Account selectAccount(int acct_number, String username) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "SELECT * FROM accounts WHERE acct_id = ? AND acct_number = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, username);
		ps.setInt(2, acct_number);

		ResultSet rs = ps.executeQuery();

		Account a = null;

		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
			// System.out.println(a);
		}

		return a;

	}

	public int createAccount(Account account) throws SQLException {
		// if executeUpdate work returns 1 that we assign to this
		int accountsCreated = 0;
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO accounts(acct_number, acct_id, balance, acct_type) VALUES (nextval (\'acctseq\'), ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, account.getId());
		ps.setDouble(2, account.getBalance());
		ps.setString(3, account.getType());

		accountsCreated = ps.executeUpdate();
		ps = conn.prepareStatement("commit");
		ps.execute();

		return accountsCreated;

	}

	public void updateAccount(Account account) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "UPDATE accounts SET balance = ? WHERE acct_number = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setDouble(1, account.getBalance());
		ps.setInt(2, account.getAccountNumber());

		ps.execute();
		ps = conn.prepareStatement("commit");
		ps.execute();

	}

	public int deleteAccount(Account account) throws SQLException {
		int accountsDeleted = 0;
		Connection conn = cf.getConnection();

		String sql = "DELETE FROM accounts WHERE acct_id = ? AND acct_number = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, account.getId());
		ps.setInt(2, account.getAccountNumber());

		accountsDeleted = ps.executeUpdate();
		ps = conn.prepareStatement("commit");
		ps.execute();

		return accountsDeleted;

	}

	public double getBalance(int accountNumber) throws SQLException {
		double balance = 0;
		ResultSet rs = null;

		String sql = "SELECT * FROM accounts WHERE acct_number = ?";

		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, accountNumber);
		rs = ps.executeQuery();

		while (rs.next()) {
			balance = rs.getDouble("balance");
		}
		return balance;

	}

}
