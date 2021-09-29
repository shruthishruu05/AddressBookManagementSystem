package com.bridgelabz.addressbook;

public class Place {
	private int contactID;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	public int getContactID() {
		return contactID;
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

	public void setContactID(int contactID) {
		this.contactID = contactID;
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

	public Place(int contactID, String address, String city, String state, String zip) {
		super();
		this.contactID = contactID;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	
}
