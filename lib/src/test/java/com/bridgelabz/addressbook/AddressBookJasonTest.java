package com.bridgelabz.addressbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class AddressBookJasonTest {
	int size = 3;
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
	public void givenAContact_WhenAddedToJSONFile_ShouldReturnCorrectSize() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.JSON_IO);
		addressBook.addPerson(person2, IOService.JSON_IO);
		long count =0;
		try {
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(new FileReader("AddressBook-file.json"));
			PersonDetails[] usrObj = gson.fromJson(br, PersonDetails[].class);
			List<PersonDetails> csvUSerList = Arrays.asList(usrObj);
			count = csvUSerList.size();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		Assert.assertEquals(2,count);
	}
	@Test
	public void whenCalled_ReadFromJsonMethod_ShouldPrintFile() 
	{
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.JSON_IO);
		addressBook.addPerson(person2, IOService.JSON_IO);
		long size  = addressBook.readData(IOService.JSON_IO);
		Assert.assertEquals(2,size);
	}
}
