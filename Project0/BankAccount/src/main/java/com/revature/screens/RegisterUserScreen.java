package com.revature.screens;

import java.util.Scanner;

import com.revature.daos.User;
import com.revature.daos.UserDao;
import com.revature.daos.current;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		User u = new User();
		System.out.println("Create a username: ");
		u.setUsername(scan.nextLine());
		System.out.println("Create a password");
		u.setPassword(scan.nextLine());
		System.out.println("Enter first name");
		u.setFirstname(scan.nextLine());
		System.out.println("Enter last name");
		u.setLastname(scan.nextLine());
		System.out.println("Create your pin: ");
		int pin = Integer.valueOf(scan.nextLine());
		while(pin/1000 > 10 || pin/100 < 10) {
			System.out.println("Invalid input. Pin should be exactly 4 numbers. Try again: ");
			pin = Integer.valueOf(scan.nextLine());
		}
		u.createPin(pin);
		System.out.println("Enter starting balance of checking account: ");
		int c = Integer.valueOf(scan.nextLine());
		u.setCBalance(Integer.valueOf(c));
		u.setCheckings("+", c);
		u.setSavings("", 0);
		ud.setTransactions(u);
		System.out.println("Would you like to start a savings account? Enter y or n: ");
		String choice = scan.nextLine();
		if(choice.equalsIgnoreCase("y")) {
			System.out.println("Enter starting balance: ");
			c = Integer.valueOf(scan.nextLine());
			u.setSBalance(c);
			u.setSavings("+", c);
			u.setCheckings("", 0);
			ud.setTransactions(u);
		}
		else
			u.setSBalance(0);
		current cu = new current();
		cu.cUser(u);
		ud.createUser(u);
		ud.setTransactions(u);
		ud.updateUser(u);
		
		return new LoginScreen();
	}

}
