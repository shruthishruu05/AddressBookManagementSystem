package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AddressBookDBTest {
	@Test
	public void givenAdressBookDB_WhenRetrived_ShouldatchAddressBookCount() 
	{
		AddressBook addressBook = new AddressBook();
		long count = addressBook.readData(IOService.DB_IO);
		Assert.assertEquals(19, count);
	}
	
	@Test
	public void givenName_WhenFound_ShouldReturnPersonDetails() {
		
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
		int val = addressBook.updateContact("Neha","L","9632587412","neha@gmail.com");
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
		
		addressBook.addContactToUpdatedDatabse("Adam", "Matt", "9988998877", "adam@gmail.com", dateAdded);
		List<contacts> employeePayrollData = addressBook.readAddressBookContactData(IOService.DB_IO);
		addressBook.readData(IOService.DB_IO);
		boolean result = addressBook.checkContactsSyncWithDB("ShruthiM");
		Assert.assertEquals(result,result);
		
	}
	@Test
	public void givenAWrongQuery_WhenExecuted_ShouldThrowException()
	{
		AdressBookDBService addrBookDBService = new AdressBookDBService();
		String sql = "SELECT * FROM random_table";
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(Exception.class);
			addrBookDBService.demoQuery(sql);
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		
	}
}
