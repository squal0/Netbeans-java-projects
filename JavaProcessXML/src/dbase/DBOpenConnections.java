
package dbase;
import java.sql.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *This class is used to connect to a Microsoft Access Database 
 * @author c.muturi
 */

public class DBOpenConnections 
{
    
 private static final DBOpenConnections INSTANCE = new DBOpenConnections();
 public  final URL pathToDB = this.getClass().getResource("../access_db/Edulink.accdb");
 public static final String URL = INSTANCE.pathToDB.getPath();
 public static final String DRIVER_CLASS = "jdbc:ucanaccess://";

//Private constructor

private DBOpenConnections()
{
    try
    {
        Class.forName(DRIVER_CLASS);
    }
    catch (ClassNotFoundException cnfe){}
}

public Connection createConnection()
{
    Connection conn = null;
    String dbURL = DRIVER_CLASS + URL;
    try
    {
        conn = DriverManager.getConnection(dbURL);
    }
    catch(SQLException e)
    {
        System.out.println("ERROR: Unable to Connect to Database.");
    }
    return conn;
}

public static Connection getConnection()
{
    return INSTANCE.createConnection();
}
}
