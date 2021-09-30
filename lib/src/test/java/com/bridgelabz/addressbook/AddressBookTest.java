package com.bridgelabz.addressbook;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import com.google.gson.Gson;

public class AddressBookTest 
{
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
		Assert.assertEquals(19, count);
	}
	
	@Test
	public void givenName_WhenFound_ShouldReturnEmployeeDetails() {
		
		AddressBook addressBook = new AddressBook();
		String name = "Anu";
		List<PersonDetails> addresList = new ArrayList<PersonDetails>();
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
		List<contacts> contactList = new ArrayList<contacts>();
		contactList = addressBook.readAddressBookContactData(IOService.DB_IO);
		int val = addressBook.updateContact("Akshay","L","9632587412","neha@gmail.com");
		boolean result = addressBook.checkContactsSyncWithDB("Akshay");
		Assert.assertEquals(result,result);
		
	}
	
	@Test
	public void givenListOfContact_WhenInserted_ShouldMatchContactEntries() 
	{
		AddressBook addressBook = new AddressBook();
		addressBook.addContactToAddress("Sita","K","9535082363","sita@gmail.com");
		boolean result = addressBook.checkContactsSyncWithDB("Sita");
	}
	@Test
	public void givenStartDateRange_WhenMatchesUsingPreparedStatement_ShouldReturnEmployeeDetails() {
		
		String startDate = "2018-01-01";
		String endDate = "2021-01-01";
		AddressBook addressBook = new AddressBook();
		List<contacts> contactData = addressBook.getContactsBasedOnStartDateUsingPreparedStatement(IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(5, contactData.size());
	}
	

	@Test
	public void givenCity_WhenMatches_ShouldReturnEmployeeDetails() {
		
		AddressBook addressBook= new AddressBook();
		String city = "Bengaluru";
		List<contacts> contactList = addressBook.getEmployeeDetailsBasedOnCity(IOService.DB_IO, city);
		Assert.assertEquals(1, contactList.size());
	}
	
	@Test
	public void givenState_WhenMatches_ShouldReturnEmployeeDetails() {
		
		String state = "Maharashtra";
		AddressBook addressBook= new AddressBook();
		List<contacts> contactList = addressBook.getEmployeeDetailsBasedOnState(IOService.DB_IO, state);
		Assert.assertEquals(2, contactList.size());
	}
		
	
	@Test
	public void givenNewEmployee_WhenAdded_ShouldSyncWithUpdatedDB() {
		
		String dateAdded = "2017-02-12";
		AddressBook addressBook = new AddressBook();
		
		addressBook.addContactToUpdatedDatabse("ShruthiM", "Destroy", "9988998877", "amy@gmail.com", dateAdded);
		List<contacts> employeePayrollData = addressBook.readAddressBookContactData(IOService.DB_IO);
		addressBook.readData(IOService.DB_IO);
		boolean result = addressBook.checkContactsSyncWithDB("ShruthiM");
		Assert.assertEquals(result,result);
		
	}
}