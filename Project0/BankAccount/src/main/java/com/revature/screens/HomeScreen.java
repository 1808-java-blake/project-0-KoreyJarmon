package com.revature.screens;

import java.util.Scanner;

import com.revature.daos.User;
import com.revature.daos.UserDao;
import com.revature.daos.current;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private User u = new User();
	private UserDao ud = UserDao.currentUserDao;
	current cu = new current();
	private boolean ch = false;
	private int pin = 10000;
	int w;
	int d;
	int b = 0;

	public Screen start() {
		u = cu.getUser();
		if (ch == false) {
			System.out.println("Enter pin in otder to access bank features: ");
			pin = Integer.valueOf(scan.nextLine());
			while (pin != ud.checkPin(u.getUsername(), u.getPassword(),pin)) {
				System.out.println("Invalid input. Try again: ");
				pin = Integer.valueOf(scan.nextLine());
			}
		}
		ch = true;
		System.out.println("\n" + "Please chose from following options:");
		System.out.println("Enter 1 to view balance");
		System.out.println("Enter 2 to deposit money");
		System.out.println("Enter 3 to withdraw money");
		System.out.println("Enter 4 to view transaction history");
		System.out.println("Enter 5 to return to login screen");
		String selection = scan.nextLine();
		String choice;

		switch (selection) {
		case "1":
			System.out.println("Which account would you like to view? Type C or S: ");
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("c")) {
				System.out.println("Your balance is: " + u.getCBalance());
			} else if (choice.equalsIgnoreCase("s")) {
				System.out.println("Your balance is: " + u.getSBalance());
			} else {
				System.out.println("Invalid input, returning to main menu");
				break;
			}
			ud.updateUser(u);
			break;
		case "2":
			System.out.println("Which account are you depositing to? Type C or S:");
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("c")) {
				System.out.println("Enter the amount you wish to deposit:");
				d = Integer.valueOf(scan.nextLine());
				u.depositIntoChecking(d);
				u.setCheckings("+",d);
				u.setSavings("", 0);
				ud.setTransactions(u);
			} else if (choice.equalsIgnoreCase("s")) {
				System.out.println("Enter the amount you wish to deposit:");
				d = Integer.valueOf(scan.nextLine());
				u.depositIntoSaving(d);
				u.setSavings("+",d);
				u.setCheckings("", 0);
				ud.setTransactions(u);
			} else
				System.out.println("Invalid input, returning to main menu");
			ud.updateUser(u);
			break;
		case "3":
			System.out.println("Which account are you withdrawing from? Type C or S:");
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("c")) {
				System.out.println("Enter the amount you wish to withdraw:");
				w = Integer.valueOf(scan.nextLine());
				if (u.getCBalance() < w)
					System.out.println("Insufficient funds. RIP ");
				else {
					u.withdrawFromChecking(w);
					u.setCheckings("-",w);
					u.setSavings("", 0);
					ud.setTransactions(u);
				}
			} else if (choice.equalsIgnoreCase("s")) {
				System.out.println("Enter the amount you wish to withdraw:");
				w = Integer.valueOf(scan.nextLine());
				if (u.getSBalance() < w)
					System.out.println("Insufficient funds. RIP ");
				else {
					u.withdrawFromSavings(w);
					u.setSavings("-",w);
					u.setCheckings("", 0);
					ud.setTransactions(u);
				}
			} else
				System.out.println("Invalid input, returning to main menu");
			ud.updateUser(u);
			break;
		case "4":
			System.out.println("Transaction history: " + "\n" + ud.getTransactions(u));
			break;
		case "5":
			return new LoginScreen();
		default:
			break;
		}

		return this;
	}

}
