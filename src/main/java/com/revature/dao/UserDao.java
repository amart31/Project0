package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.User;

public interface UserDao {

	public List<User> getUsers() throws SQLException;

	public User getUserByUsername(String username) throws SQLException;

	public int createUser(User user) throws SQLException;

	public int updateUser(User user) throws SQLException;
	
	public int deleteUser(User user) throws SQLException;

	public boolean doesUsernameMatch(String username) throws SQLException;

	public boolean doesPasswordMatch(String password, String username) throws SQLException;

}
