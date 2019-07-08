/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author c.muturi
 */
public class DBCloseObjects 
{
    //Class method to close the database connection
    public static void close(Connection conn) 
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException sqle)
                    {
                        System.out.println(sqle.getCause());
                    }
        }
    }
    
    //Class method to close statement objects
    public static void close(Statement stmt) 
    {
        if(stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch(SQLException sqle)
            {
                System.out.println(sqle.getCause());
            }
        }
    }
    
    //Class method to close the resultset
    public static void close(ResultSet rs) 
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException sqle)
            {
                System.out.println(sqle.getCause());
            }
        }
        
    }
}
