package edu.txstate.cs3320.teamtired.data.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import edu.txstate.cs3320.teamtired.user.Staff;

public class StaffDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(StaffDAO.class.getName());

	private static final int    STAFF_ID_COLUMN          		= 1;
	private static final int    STAFF_FIRST_NAME_COLUMN       	= 2;
	private static final int    STAFF_LAST_NAME_COLUMN 			= 3;
	private static final int    STAFF_EMAIL_ADDRESS_COLUMN      = 4;
	private static final int	STAFF_PHONE_NUMBER_COLUMN		= 5;
	private static final int    STAFF_PASSWORD_HASH_COLUMN      = 6;
	private static final String STAFF_TABLE						= ".staff ";
	private final static String STAFF_SELECT_STRING 	= "SELECT staff_id, first_name, last_name, email, phone_number, password_hash ";

	public static Staff findStaff (String email){
		StringBuilder sqlStatementBuilder = new StringBuilder(STAFF_SELECT_STRING);
		sqlStatementBuilder.append("FROM ").append(DAO.getDBName()).append(STAFF_TABLE).
					 append("WHERE email = '").append(email).append("';");
		String sqlStatement = sqlStatementBuilder.toString();
		LOGGER.info(sqlStatement);
		return findStaffHelper(sqlStatement);	
	}
	
	private static Staff findStaffHelper(String sqlStatement) {
		Connection dbConnection = null;
		Staff staff = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(sqlStatement);
			staff = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("StaffDAO.findStaff: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}	
		return staff;
		
	}
	
	private static Staff buildResults (ResultSet results) {
		Staff staff = null;
		try {
			while (results.next()) {
				int    id             	= results.getInt   (STAFF_ID_COLUMN );
				String firstName       	= results.getString(STAFF_FIRST_NAME_COLUMN );
				String lastName 		= results.getString(STAFF_LAST_NAME_COLUMN);
				String    email      	= results.getString(STAFF_EMAIL_ADDRESS_COLUMN);
				String phoneNumber		= results.getString(STAFF_PHONE_NUMBER_COLUMN);
				String passwordHash     = results.getString(STAFF_PASSWORD_HASH_COLUMN);
				staff = new Staff(id, firstName, lastName, email, phoneNumber, passwordHash);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return staff;
	}	

	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to staff members
		
	}
}
