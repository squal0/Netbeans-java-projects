package sess_3jaxpdom;

import classes.Employee;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author c.muturi
 */
public class DOMReader
{
    //Method to reeturn a List of Employee Data read from an xml file
    public List<Employee> getDataFromXMLFile(String filename)
    {
        List<Employee> empData = new ArrayList<>();
        
        //Create a Document Object
        Document doc = null;
        
        //Create a file object to store  the xml contents
        File empXMLFile = new File(filename);
        
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(empXMLFile);
        }
        catch(ParserConfigurationException | SAXException |
                IOException exc)
        {
           exc.printStackTrace(); 
        }
        
        //Create a Nodelist 
        NodeList empList = doc.getElementsByTagName("employee");
        
        //Display the number of employee nodes found
        System.out.println("employee nodes found: " + empList.getLength());
        
        //Use an array style for loop to extract the data
        for (int n = 0; n < empList.getLength();n++ )
        {
            //Create an employee object
            Employee emp = new Employee();
            empData.add(emp);
            
            //Create an employee element
            Element empElement = (Element) empList.item(n);
            
            //Set the employee's id
            emp.setEmpID(Integer.parseInt(empElement.getAttribute(Employee.ID)));
            
            //Set the employee's First Name
            String fname = getTextFromElement(empElement, Employee.FIRSTNAME);
            emp.setFirstname(fname);
            
            //Set the employee's Last Name
            String lname = getTextFromElement(empElement, Employee.LASTNAME);
            emp.setLastname(lname);
            
            //Set the employee's Location
            String location = getTextFromElement(empElement, Employee.LOCATION);
            emp.setLocation(location);
        }
        
        //Return the employee details
        return empData;
    }
        //Private method to extract the details from the employee's child elements
        private String getTextFromElement(Element parentElem, String elemName)
        {
          Element node = (Element) parentElem.getElementsByTagName(elemName).item(0);
          return node.getTextContent();
        }
}
