package com.bridgelabz.addressbook;

import java.util.List;

public class contacts {
	public Address address = new Address();
	private int contactId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String dateAdded;
	
	
	public contacts(int contactId, String firstName, String lastName, String phoneNumber, String email,String dateRange) 
	{
		super();
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateAdded = dateAdded;
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
	
	public String getdateAdded() 
	{
		return dateAdded;
	}
	public void setdateAdded(String added) {
		this.dateAdded = added;
	}
	public int getContactId() {
		return contactId;
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

	public void setContactId(int contactId) {
		this.contactId = contactId;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}
