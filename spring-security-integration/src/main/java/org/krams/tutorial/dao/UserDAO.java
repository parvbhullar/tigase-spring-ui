package org.krams.tutorial.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.krams.tutorial.domain.DbUser;

/**
 * A custom DAO for accessing data from the database.
 *
 */
public class UserDAO {

	protected static Logger logger = Logger.getLogger("dao");
	
	/**
	 * Simulates retrieval of data from a database.
	 */
	public DbUser searchDatabase(String username) {
		// Retrieve all users from the database
		List<DbUser> users = internalDatabase();
		
		// Search user based on the parameters
		for(DbUser dbUser:users) {
			if ( dbUser.getUsername().equals(username)  == true ) {
				logger.debug("User found");
				// return matching user
				return dbUser;
			}
		}
		
		logger.error("User does not exist!");
		throw new RuntimeException("User does not exist!");
	}

	/**
	 * Our fake database. Here we populate an ArrayList with a dummy list of users.
	 */
	private List<DbUser> internalDatabase() {
		// Dummy database
		
		// Create a dummy array list
		List<DbUser> users = new ArrayList<DbUser>();
		DbUser user = null;
		
		// Create a new dummy user
		user = new DbUser();
		user.setUsername("john");
		// Actual password: admin
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		// Admin user
		user.setAccess(1);
		
		// Add to array list
		users.add(user);
		
		// Create a new dummy user
		user = new DbUser();
		user.setUsername("jane");
		// Actual password: user
		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		// Regular user
		user.setAccess(2);
		
		// Add to array list
		users.add(user);
		
		return users;
	}
	
}
