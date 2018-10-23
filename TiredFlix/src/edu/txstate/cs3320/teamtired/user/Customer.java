package edu.txstate.cs3320.teamtired.user;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private String passwordHash;
	
	public Customer(int id, String firstName, String lastName, String emailAddress, String phoneNumber, String passwordHash) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
		this.passwordHash = passwordHash;
	}
	
	public Customer(String firstName, String lastName, String emailAddress, String phoneNumber, String passwordHash) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
		this.passwordHash = passwordHash;
	}
	


	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", phoneNumber=" + phoneNumber + "]";
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPasswordHash() {
		return passwordHash;
	}


}
