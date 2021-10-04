package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookIOTest {
	PersonDetails person1;
	PersonDetails person2;
	@Before
	public void initialize() {
		person1 = new PersonDetails();
		person1.setFirstName("shru");
		person1.setLastName("m");
		person1.setAddress("btm");
		person1.setCity("bangalore");
		person1.setState("karnataka");
		person1.setPinCode(560076);
		person1.setPhoneNumber("9110884694");
		person1.setEmail("shru@gmail.com");
		
		person2 = new PersonDetails();
		person2.setFirstName("Mahesh");
		person2.setLastName("S");
		person2.setAddress("jaynagar");
		person2.setCity("bangalore");
		person2.setState("karnataka");
		person2.setPinCode(560072);
		person2.setPhoneNumber("7899387072");
		person2.setEmail("mahi@gmail.com");
	}
	@Test
	public void whenCalled_ReadFromListMethod_ShouldPrintList() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.LIST_DS_IO);
		addressBook.addPerson(person2, IOService.LIST_DS_IO);
		long size  = addressBook.readData(IOService.LIST_DS_IO);
		Assert.assertEquals(2,size);
	}
	
	@Test
	public void whenCalled_ReadFromFileMethod_ShouldPrintFile() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.TXT_FILE_IO);
		addressBook.addPerson(person2, IOService.TXT_FILE_IO);
		long size  = addressBook.readData(IOService.TXT_FILE_IO);
		Assert.assertEquals(2,size);
	}
	
}
