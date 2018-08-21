package com.revature.screens;

import java.util.Scanner;

import com.revature.daos.User;
import com.revature.daos.UserDao;
import com.revature.daos.current;

public class AdminScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private boolean open = false;
	private String again = "";

	@Override
	public Screen start() {

		if (open == false) {
			System.out.println("Welcome to admin mode!");
			open = true;
		}
		System.out.println("Enter password in access admin features: ");
		String adminPass = scan.nextLine();
		if (adminPass.equals("koreyjarmon")) {
			System.out.println("Enter 1 to search the bank's database " + again);
			again = "again";
			System.out.println("Enter 2 to return to previous screen");
			String selection = scan.nextLine();
			switch (selection) {
			case "1":
				System.out.println("Enter the username of the account you would like to view: ");
				String username = scan.nextLine();

				User currentUser = ud.adminSearch(username);
				current cu = new current();
				cu.cUser(currentUser);

				if (currentUser != null) {
					System.out.println(ud.getTransactions(currentUser));
				}
				break;
			case "2":
				return new LoginScreen();
			default:
				System.out.println("Invalid input, try again");
				break;
			}
		} else {
			System.out.println("Incorrect password, you will be returned to the login screen.");
			return new LoginScreen();
		}

		return this;
	}

}
