package edu.txstate.cs3320.teamtired.data.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import edu.txstate.cs3320.teamtired.user.Customer;

public class CustomerDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	
	private static final int    CUSTOMER_ID_COLUMN          		= 1;
	private static final int    CUSTOMER_FIRST_NAME_COLUMN       	= 2;
	private static final int    CUSTOMER_LAST_NAME_COLUMN 			= 3;
	private static final int    CUSTOMER_EMAIL_ADDRESS_COLUMN      	= 4;
	private static final int	CUSTOMER_PHONE_NUMBER_COLUMN		= 5;
	private static final int    CUSTOMER_PASSWORD_HASH_COLUMN      	= 6;
	private static final String CUSTOMER_TABLE						= ".customer ";
	private static final String STAFF_TABLE 						= ".staff ";
	private final static String CUSTOMER_SELECT_STRING 	= "SELECT customer_id, first_name, last_name, email, phone_number, password_hash ";
	private final static String STAFF_SELECT_STRING		= "Select staff_id, first_name, last_name, email, phone_number, password_hash ";
	
	public static Customer findCustomer (String email){
		StringBuilder sqlStatementBuilder = new StringBuilder(CUSTOMER_SELECT_STRING);
		sqlStatementBuilder.append("FROM ").append(DAO.getDBName()).append(CUSTOMER_TABLE).
					 append("WHERE email = '").append(email).append("';");
		String sqlStatement = sqlStatementBuilder.toString();
		LOGGER.info(sqlStatement);
		Customer customer = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(sqlStatement);
			customer = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("CustomerDAO.findCustomer: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}	
		return customer;
	}
	
	
	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to customers	
	}
	
	public static Customer addEntry(String firstName, String lastName, String emailAddress, String phoneNumber, String passwordHash) throws SQLException {
		String sqlStatement = buildInsertStatement(firstName, lastName, emailAddress, phoneNumber, passwordHash);
		LOGGER.info(sqlStatement);

		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			statement.executeUpdate(sqlStatement);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("CustomerDAO.addCustomer: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}		
		
		return findCustomer(emailAddress);
	}	
	
	public static String buildInsertStatement(String firstName, String lastName, String emailAddress, String phoneNumber, String passwordHash) {
		StringBuilder statement = new StringBuilder("INSERT INTO ");
		
		statement.append(getDBName()).append(CUSTOMER_TABLE).
				  append("(first_name, last_name, email, phone_number, password_hash) VALUES ('").
				  append(firstName).
				  append("', '").append(lastName).
				  append("', '").append(emailAddress).
				  append("', '").append(phoneNumber).
				  append("', '").append(passwordHash).
				  append("')");
		
		return statement.toString();
	}

	private static Customer buildResults (ResultSet results) {
		Customer customer = null;
		try {
			while (results.next()) {
				int    id             	= results.getInt   (CUSTOMER_ID_COLUMN );
				String firstName       	= results.getString(CUSTOMER_FIRST_NAME_COLUMN );
				String lastName 		= results.getString(CUSTOMER_LAST_NAME_COLUMN);
				String    email      	= results.getString(CUSTOMER_EMAIL_ADDRESS_COLUMN);
				String phoneNumber		= results.getString(CUSTOMER_PHONE_NUMBER_COLUMN);
				String passwordHash     = results.getString(CUSTOMER_PASSWORD_HASH_COLUMN);
				customer = new Customer(id, firstName, lastName, email, phoneNumber, passwordHash);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return customer;
	}	

public static Customer findStaff (String email){
	StringBuilder sqlStatementBuilder = new StringBuilder(STAFF_SELECT_STRING);
	sqlStatementBuilder.append("FROM ").append(DAO.getDBName()).append(STAFF_TABLE).
				 append("WHERE email = '").append(email).append("';");
	String sqlStatement = sqlStatementBuilder.toString();
	LOGGER.info(sqlStatement);
	Customer customer = null;
	Connection dbConnection = null;
	try {
		dbConnection = DAO.getDBConnection();
		Statement statement 	= dbConnection.createStatement();
		ResultSet results       = statement.executeQuery(sqlStatement);
		customer = buildResults (results);
		dbConnection.close();
	} catch (SQLException e) {
		System.err.println("CustomerDAO.findStaff: " + e.toString());
		LOGGER.severe(e.toString());
		closeQuietly(dbConnection);
	}	
	return customer;
}
}
