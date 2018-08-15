package com.revature.screens;

import java.util.Scanner;

import com.revature.daos.User;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		User u = new User();
		System.out.println("Enter new username: ");
		u.setUsername(scan.nextLine());
		System.out.println("Enter password");
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
		u.setCBalance(Integer.valueOf(scan.nextLine()));
		System.out.println("Would you like to start a savings account? Enter y or n: ");
		String choice = scan.nextLine();
		if(choice.equalsIgnoreCase("y")) {
			System.out.println("Enter starting balance: ");
			u.setSBalance(scan.nextInt());
		}
		else
			u.setSBalance(0);
		ud.createUser(u);
		u.setTransactions("Account created.");
		ud.updateUser(u);
		
//		try {
//		u.setBalance(Integer.valueOf(balance));
//		ud.createUser(u);
//		} catch(NumberFormatException e) {
//			System.out.println("Invalid Number");
//		}
		
		return new LoginScreen();
	}

}
