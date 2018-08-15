package com.revature.daos;

public interface UserDao 
{
	public static final UserDao currentUserDao = new UserSerializer();
	
	void createUser(User u);
	User findUsernameAndPassword(String username, String password);
	void updateUser(User u);
	void deleteUser(User u);
	User adminSearch(String username);
}
