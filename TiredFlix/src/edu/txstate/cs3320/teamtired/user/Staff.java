package edu.txstate.cs3320.teamtired.user;

public class Staff extends User{
	
	public Staff(int id, String firstName, String lastName, String emailAddress, String phoneNumber, String passwordHash) {
		super(id, firstName, lastName, emailAddress, phoneNumber, passwordHash);
	}
	
	public Staff(String firstName, String lastName, String emailAddress, String phoneNumber, String passwordHash) {
		super(firstName, lastName, emailAddress, phoneNumber, passwordHash);
	}

	@Override
	public String toString() {
		return "Staff [Id=" + getId() + ", FirstName=" + getFirstName() + ", LastName="+ getLastName() 
				+ ", EmailAddress=" + getEmailAddress() + ", PhoneNumber=" + getPhoneNumber() + "]";
	}
}
