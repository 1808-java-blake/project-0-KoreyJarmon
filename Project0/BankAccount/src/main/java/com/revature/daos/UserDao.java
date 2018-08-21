package com.revature.daos;

public interface UserDao 
{
	public static final UserDao currentUserDao = new UserDaoJdbc();
	
	void createUser(User u);
	User findUsernameAndPassword(String username, String password);
	void updateUser(User u);
	void deleteUser(User u);
	void setTransactions(User u);
	String getTransactions(User u);
	User adminSearch(String username);
	int checkPin(String username, String password, int pin);
}
