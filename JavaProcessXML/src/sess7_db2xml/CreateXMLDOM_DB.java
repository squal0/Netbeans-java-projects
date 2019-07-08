/*
 * The code in this class will read data from a Microsoft Access Database,
 * and convert it into XML  format. 
 */
package sess7_db2xml;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import classes.Courses;
import dbase.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
public class CreateXMLDOM_DB {
    //Class field to connect to the database
    private static Connection conn = null;
    private static Statement stat = null;
    private static ResultSet rs = null;
    
    //Method to get data from the courses table 
    
    private static List<Courses> getCourseDetails(String query) throws SQLException{
        
        List<Courses> crs = new ArrayList<>();
        try {
            //Create and instantiate a Course object
            Courses course = new Courses();
            conn = DBOpenConnections.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(query);
            //use an iterator to populate the course object with details from the db
            while (rs.next()) {                
                //Retrieve course details and store them in the courses object
                course.setCourseCode(rs.getString(1));
                course.setCourseName(rs.getString(2));
                course.setDuration(rs.getInt(3));
                course.setTotalModules(rs.getString(4));
                //Add the new course to the List of Courses 
                crs.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBCloseObjects.close(rs);
            DBCloseObjects.close(stat);
            DBCloseObjects.close(conn);
        }
        return crs; // returns a list of courses
    }
    //Method to display the course details on the console
    private static void outputToConsole(Document doc) throws TransformerException
    {
        try
        {
            //Declare and instantiate a DOMSource object
            DOMSource source = new DOMSource(doc);
            
            //Declare  and instantiate a DOMSource object to be used
            //Write the results
            StringWriter writer= new StringWriter();
            
            //Declare and instantiate a DOMSource object to hold the
            //Written results
            StreamResult result = new StreamResult(writer);
            
            //Transform the reults into xml format
            Transformer transformer = getTransformer();
            
            //Do the transformation
            transformer.transform(source, result);
            
            //A string object to hold the writer's results
            String xmlString = writer.toString();
            
            //Display the results
            System.out.println(xmlString);
            
        }
        catch(TransformerFactoryConfigurationError exc )
                {
                   exc.printStackTrace(); 
                }
    }
        
    //This method  creates a transformer object and sets its output properties
    private static Transformer getTransformer() 
    {
        //Declare the transformer object
        Transformer transformer = null;
        try
        {
            //Instantiate transformer object to transform the results to cml format
            transformer = TransformerFactory.newInstance().newTransformer();
            //Set the transformers output/formatting properties
            transformer.setOutputProperty(OutputKeys.INDENT,"yes" );
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
            "2");
        }
        catch(IllegalArgumentException | TransformerConfigurationException e)
        {
           e.printStackTrace(); 
        }
        return transformer;
    }
    //main method begins program execution
    public static void main(String[] args) throws SQLException
    {
        //List object to hold the course data
        List<Courses> data = getCourseDetails("Select * from courses");
        //Declare and instantiate an object of the DOMCreator class
        DOMCreator creator = new DOMCreator();
        //Create a Document object to store the xml dcument
        Document domDoc = creator.createXMLDocument(data);
        
        try
        {
            outputToConsole(domDoc);
        }
        catch(Exception e)
        {
        }
    }
}
