package com.bridgelabz.addressbook;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;

public class AdressBookDBService {
	private PreparedStatement addressDataStatement;
	private static List<PersonDetails> addressList;
	public List<PersonDetails> readData() {
		String sql = "SELECT * FROM contacts";
		List<PersonDetails> contactList = new ArrayList<>();
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				PersonDetails person  = new PersonDetails();
				person.setFirstName(result.getString("firstName"));
				person.setLastName(result.getString("lastName"));
				person.setPhoneNumber(result.getString("phoneNumber"));
				person.setEmail(result.getString("email"));
				contactList.add(person);
			}
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	public List<contacts> readContactData() {
		String sql = "SELECT * FROM contacts";
		List<contacts> contactList = new ArrayList<>();
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				contacts person  = new contacts();
				person.setFirstName(result.getString("firstName"));
				person.setLastName(result.getString("lastName"));
				person.setPhoneNumber(result.getString("phoneNumber"));
				person.setEmail(result.getString("email"));
				contactList.add(person);
			}
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	
	
	public Connection getConnection() throws SQLException
	{
		String jdbcURL = "jdbc:mysql://localhost:3306/AddressBook_Service?userSSL=false";
		String userName = "root";
		String password = "Welcome@123";
		Connection connection;
		System.out.println("Connecting to database:"+jdbcURL);
		connection =  (Connection) DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("Connection is successful!!!!"+connection);
		return connection;
	
	}
	private void prepareStatementForAddressData() {
		
		try {
			Connection connection = this.getConnection();
			String sqlStatement = "SELECT * FROM Address_Book WHERE name = ?;";
			addressDataStatement = connection.prepareStatement(sqlStatement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private void prepareStatementForContactData() {
			
			try {
				Connection connection = this.getConnection();
				String sqlStatement = "SELECT * FROM contacts WHERE name = ?;";
				addressDataStatement = connection.prepareStatement(sqlStatement);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
private List<PersonDetails> getAddressData(ResultSet resultSet) {
		
	addressList = new ArrayList<>();
		
		try {
			while(resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("address");
				String city  = resultSet.getString("city");
				String state   = resultSet.getString("state ");
				String phoneNumber   = resultSet.getString("phoneNumber ");
				int pinCode   = resultSet.getInt("pinCode");
				String email   = resultSet.getString("email");
				addressList.add(new PersonDetails(firstName,lastName, address, city,state,phoneNumber,pinCode,email));
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return addressList;
		
	}
	
	public List<PersonDetails> getPersonDetailsBasedOnNameUsingStatement(String name) {
		String sqlStatement = String.format("SELECT * FROM Address_Book WHERE firstName  = '%s';",name);
		List<PersonDetails> addressList = new ArrayList<>();
				
		try (Connection connection = getConnection();){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			addressList = this.getAddressData(resultSet);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return addressList;
	}
	public int updateContact(String firstName, String lastName, String phoneNumber, String email) 
	{
		String sqlString = String.format("update contacts set lastName ='%s',phoneNumber ='%s',email='%s' where firstName= '%s';",lastName,phoneNumber,email,firstName);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sqlString);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}return 0;
	}
	
	public List<contacts> getAddressContactData(ResultSet resultSet) {
		// TODO Auto-generated method stub
		List<contacts> contactList = new ArrayList<>();
		try {
			while(resultSet.next()) {
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String email = resultSet.getString("email");
				contactList.add(new contacts(firstName, lastName, phoneNumber,email));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
		
	}
	public List<contacts> getContactData(String name) {
		List<contacts> contactList = null;
		if(this.addressDataStatement == null) {
			this.prepareStatementForContactData();
		}
		try {
			addressDataStatement.setString(1, name);
			ResultSet resultSet = addressDataStatement.executeQuery();
			contactList = this.getAddressContactData(resultSet);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	
	public contacts addContactToAddress(String firstName, String lastName, String phoneNumber, String email) 
	{
		int contactID = -1;
		contacts contactData = null;
		String sql = String.format("INSERT INTO contacts(firstname,lastname,phoneNumber,email) VALUES('%s','%s','%s','%s')",firstName,lastName,phoneNumber,email);
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
			if(result == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) contactID = resultSet.getInt(1);
			}
			connection.close();
			contactData = new contacts(firstName,lastName,phoneNumber,email);
		}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			System.out.println(contactData);
		return contactData; 	
	}
	public List<contacts> getContactsBasedOnStartDateUsingPreparedStatement(String startDate, String endDate) {
		
		String sqlStatement = String.format("SELECT * FROM contacts WHERE added BETWEEN '%s' AND '%s';",startDate, endDate);
		List<contacts> contactList = new ArrayList<>();
				
		try (Connection connection = getConnection();){
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			contactList = this.getAddressContactData(resultSet);
		}
		catch(SQLException exception){
			exception.printStackTrace();
		}
		return contactList;
	}
	
}