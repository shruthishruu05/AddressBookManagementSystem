package com.bridgelabz.addressbook;

public class addressBookType {
	private String addressBookName;
	private String addressBookType;
	private int contactID;
	
	public String getAddressBookName() {
		return addressBookName;
	}

	public String getAddressBookType() {
		return addressBookType;
	}

	public int getContactID() {
		return contactID;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public void setAddressBookType(String addressBookType) {
		this.addressBookType = addressBookType;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public addressBookType(String addressBookName, String addressBookType, int contactID) {
		super();
		this.addressBookName = addressBookName;
		this.addressBookType = addressBookType;
		this.contactID = contactID;
	}
	}
