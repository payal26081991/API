
package Common;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Date;
import java.util.Properties;


public class DBConnector {

	//private static Logger logger = Logger.getLogger(DBConnector.class);
	public static Logger log = LogManager.getLogger("file");

	/**
	 * 
	 * @description Obtains Connection with DB
	 * @return Connection
	 */
	public Connection getDBConnection(String API_TYPE) {
		Connection connection	= null;
		String databaseName	= null;
		 Properties credentials = Credentials.getData();

		String server = credentials.getProperty("DBServer");
		if (API_TYPE.equalsIgnoreCase("Linear")) {
			 databaseName = credentials.getProperty("LINEAR_DBName");
		}
		else{
			databaseName = credentials.getProperty("VOD_DBName");
		}
		String userId = credentials.getProperty("DBUser");
		String password = credentials.getProperty("DBPassword");

		String connectionString = "jdbc:sqlserver://" + server +  ";databaseName=" + databaseName;


		// Obtaining connection with DB
		try {

			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			connection = DriverManager.getConnection(connectionString, userId, password);
			
		} catch (SQLException e) {
			log.error("Exception Occurred while obtaining Driver Connection", e);
		}
		return connection;
	}

	/**
	 * 
	 * @description fetches data from DB
	 * @param query
	 * @return ResultSet
	 */
	public ResultSet getResult(Connection connection, String query) {

		Statement statement	= null;
		ResultSet resultSet	= null;

		if (connection != null) {

			// creating statement object
			try {
				statement = connection.createStatement();
				
			} catch (SQLException e) {
				log.error("Exception Occurred while creating Statement Object", e);
			}

			try {
				log.info("Executing Query :: " + query);

				// query execution start time
				Date startTime = new Date();

				resultSet = statement.executeQuery(query);
				
				

				// query execution end time
				Date endTime		= new Date();

				// caluclating time difference
				long	diffTime	= endTime.getTime() - startTime.getTime();

				long	minutes		= (diffTime / 1000) / 60;
				long	seconds		= (diffTime / 1000) % 60;

				log.info(minutes + " : " + seconds + " taken to execute query :: " + query);

				log.info("DB Query Execution Completed.");
			} catch (SQLException e) {
				log.error("Exception Occurred while fetching data from DB", e);
			}

		} else {
			log.error("DB Connection object received as null.");
		}

		return resultSet;
	}

	/**
	 * @description closing db connection
	 * @param connection
	 */
	public void closeDBConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			log.error("Exception Occurred while closing DB Connection.", e);
		}
	}
}
