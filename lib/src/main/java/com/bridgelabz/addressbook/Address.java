package com.bridgelabz.addressbook;

public class Address {
	private int contactId;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	public int getContactId() {
		return contactId;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public void setContactID(int contactId) {
		this.contactId = contactId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Address(int contactId, String address, String city, String state, String zip) {
		super();
		this.contactId = contactId;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	
}
