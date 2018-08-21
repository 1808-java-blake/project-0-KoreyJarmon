package com.revature.daos;

public class User {

	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String checkings ="";
	private int cbalance;
	private int sbalance;
	private int pin;
	private String savings ="";

	public User() {
		super();
	}

	public User(String username, String password, String firstname, String lastname, int cbalance, int sbalance,
			int pin) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.cbalance = cbalance;
		this.sbalance = sbalance;
		this.pin = pin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getSBalance() {
		return sbalance;
	}

	public int getCBalance() {
		return cbalance;
	}

	public void setSBalance(int bal) {
		this.sbalance = bal;
	}

	public void setCBalance(int bal) {
		this.cbalance = bal;
	}

	public void withdrawFromChecking(int change) {
		this.cbalance = cbalance - change;
	}

	public void depositIntoChecking(int change) {
		this.cbalance = cbalance + change;
	}

	public void withdrawFromSavings(int change) {
		this.sbalance = sbalance - change;
	}

	public void depositIntoSaving(int change) {
		this.sbalance = sbalance + change;
	}

	public void createPin(int pin) {
		this.pin = pin;
	}

	public int checkPin() {
		return this.pin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheckings() {
		return checkings;
	}

	public void setCheckings(String sign, int checkings) {
		this.checkings = sign + checkings;
	}

	public String getSavings() {
		return savings;
	}

	public void setSavings(String sign, int savings) {
		this.savings = sign + savings;
	}

}
