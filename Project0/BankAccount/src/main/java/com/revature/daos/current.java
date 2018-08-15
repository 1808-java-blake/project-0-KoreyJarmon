package com.revature.daos;

public class current {
	private static User cu;
	
	public void cUser(User ud) {
		current.cu = ud;
	}
	
	public User getUser() {
		return current.cu;
	}

}
