/**
 * Java class that extends the org.xml.sax.helpers.DefaultHandler class and 
 * overrides its 3 methods
 */
package sess2_sax;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author c.muturi
 */
public class SAXParseHandler extends DefaultHandler
{
    //Class instance variables/properties/fields
    List<Employee> empList = null;
    Employee emp = null;
    
    //Getter method to obtain a list of employees
    public List<Employee> getEmployees()
    {
        return empList;
    }
    
    //Boolean value that will be used to check if various element have been
    //found in the employees.xml file
    boolean bfName, blName, bLocation;
    
    //Override the meethods inn the DefaultHandler classs
    
    @Override
    public void startElement(String url, String localName, String qName,
           Attributes att) throws SAXException
  
    {
       if(qName.equalsIgnoreCase("employee"))
       {
           //Create a new employee and put in the employee list
           emp = new Employee();
           //Retrieve the employee's Id from the ID attribute
           String empID = att.getValue("id");
           //Set the new employee's id
           emp.setEmpID(Integer.parseInt(empID));
           //Check if the employee's list is null and initialize it
           if(empList == null)
               empList = new ArrayList<>();
       }
       else if(qName.equalsIgnoreCase("firstname"))
           bfName = true;
       
       else if(qName.equalsIgnoreCase("lastname"))
           blName = true;
       
       else if(qName.equalsIgnoreCase("location"))
           bLocation = true;
    }
    
    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException
    {
        if(qName.equalsIgnoreCase("employee"))
        {
            empList.add(emp);
        }
    }
    @Override
    public void characters(char ch[], int start, 
            int length) throws SAXException
    {
        if(bfName)
        {
            emp.setFirstname(new String(ch, start, length));
            bfName = false;
        }
        
        else if(blName)
        {
          emp.setLastname(new String(ch, start, length));
            blName = false;
        }
        
        else if(bLocation)
        {
          emp.setLocation(new String(ch, start, length));
            bLocation = false;
        }
    }
    //You can also use the override tostring() method to display the employee details
//    @Override
//    public String toString()
//    {
//        String output = "-------------------------" +
//                "\nEmployee ID: " + emp.getEmpID() +
//                "\nEmployee First Name: " + emp.getFirstname() + 
//                "\nEmployee Last Name: " + emp.getLastname() +
//                "\nEmployee Location: " + emp.getLocation() +
//                "\n-------------------------";
//        return output;
//    }
}
