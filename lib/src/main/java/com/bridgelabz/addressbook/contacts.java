package com.bridgelabz.addressbook;

import java.util.List;

public class contacts {
	private int contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String added;
	
	public contacts(int contactID, String firstName, String lastName, String phoneNumber, String email,String added) {
		super();
		this.contactID = contactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.added = added;
	}
	public contacts() {
		
	}
	public contacts(String firstName, String lastName, String phoneNumber, String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public String getAdded() {
		return added;
	}
	public void setAdded(String added) {
		this.added = added;
	}
	public int getContactID() {
		return contactID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
