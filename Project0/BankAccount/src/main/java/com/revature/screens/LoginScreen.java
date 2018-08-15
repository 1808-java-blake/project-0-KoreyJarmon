package com.revature.screens;

import java.util.Scanner;

import com.revature.daos.User;

import com.revature.daos.UserDao;
import com.revature.daos.current;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		System.out.println("\n" +"Enter Username or type Register to sign up: ");
		String username = scan.nextLine();
		if ("register".equalsIgnoreCase(username)) {
			return new RegisterUserScreen();
		} else if ("admin".equalsIgnoreCase(username)) {
			return new AdminScreen();
		}

		System.out.println("Enter Password: ");
		String password = scan.nextLine();

		User currentUser = ud.findUsernameAndPassword(username, password);
		current cu = new current();
		cu.cUser(currentUser);

		if (currentUser != null) {
			return new HomeScreen();
		}

		System.out.println("Sorry that username and/or password was incorrect");
		return this;
	}

}
