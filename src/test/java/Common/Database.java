/*
package Common;


import data.Vod_Data.ProgramData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
    protected Properties credentials = Credentials.getData();
    protected String server = credentials.getProperty("DBServer");
    protected String databaseName = credentials.getProperty("DBName");
    protected String userId = credentials.getProperty("DBUser");
    protected String password = credentials.getProperty("DBPassword");
    private String connectionString;
    private static ResultSet resultSet;
    private Statement statement;
    Connection connection;

    public static Logger log = LogManager.getLogger("file");



    public Database() {
        System.out.println("in");

        connectionString = "jdbc:sqlserver://" + server +  ";databaseName=" + databaseName;
        System.out.println(connectionString);

        connectDB(connectionString);
    }

    public void connectDB(String connectionString) {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection(connectionString, userId, password);
            log.info("Database Connection Established");
            System.out.println("Database Connection Established");
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Database Connection Established Failed");
        }
    }


    public void close() {
        try {
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

*/
