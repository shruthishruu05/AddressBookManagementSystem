package com.bridgelabz.addressbook;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

public class AddressBookTest 
{
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
	public void givenAContact_WhenAddedToList_ShouldReturnCorrectSize() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.LIST_DS_IO);
		addressBook.addPerson(person2, IOService.LIST_DS_IO);
		Assert.assertEquals(2,addressBook.referenceBook.size());
	}
	
	@Test
	public void givenAContact_WhenAddedToFile_ShouldReturnCorectSize() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.TXT_FILE_IO);
		addressBook.addPerson(person2, IOService.TXT_FILE_IO);
		long size = 0;
		try {
			size = Files.lines(Paths.get("AddressBook-file.txt")).count();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(2,size);
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
	
	@Test
	public void givenAContact_WhenAddedToCSVFile_ShouldReturnCorrectSize() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.CSV_IO);
		addressBook.addPerson(person2, IOService.CSV_IO);
		long size = 0;
		try {
			size = Files.lines(Paths.get("AddressBook-file.csv")).count();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals(2,size-1);
	}
	
	@Test
	public void whenCalled_ReadFromCSVMethod_ShouldPrintFile() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.CSV_IO);
		addressBook.addPerson(person2, IOService.CSV_IO);
		long size  = addressBook.readData(IOService.CSV_IO);
		Assert.assertEquals(2,size);
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
		catch(IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(2,count);
	}
	
	@Test
	public void whenCalled_ReadFromJsonMethod_ShouldPrintFile() {
		AddressBook addressBook = new AddressBook();	
		addressBook.addPerson(person1, IOService.JSON_IO);
		addressBook.addPerson(person2, IOService.JSON_IO);
		long size  = addressBook.readData(IOService.JSON_IO);
		Assert.assertEquals(2,size);
	}
	
	@Test
	public void givenAdressBookDB_WhenRetrived_ShouldatchAddressBookCount() 
	{
		AddressBook addressBook = new AddressBook();
		long count = addressBook.readData(IOService.DB_IO);
		Assert.assertEquals(3, count);
	}
	
	@Test
	public void givenName_WhenFound_ShouldReturnEmployeeDetails() throws NullPointerException {
		
		AddressBook addressBook = new AddressBook();
		String name = "Anu";
		List<PersonDetails> addresList;
		try {
			addresList = addressBook.getPersonDetailsBasedOnName(IOService.DB_IO, name);
			String resultName = addresList.get(0).firstName;
			Assert.assertEquals(name, resultName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void givenNewContactForPerson_WhenUpdated_ShouldSyncWithDB()  
	{
		AddressBook addressBook = new AddressBook();
		
		List<contacts> contactList = addressBook.readAddressBookContactData(IOService.DB_IO);
		int val = addressBook.updateContact("Shruthi","L","9632587412","neha@gmail.com");
		boolean result = addressBook.checkContactsSyncWithDB("Sruthi");
		Assert.assertEquals(val,val);
		
	}

}