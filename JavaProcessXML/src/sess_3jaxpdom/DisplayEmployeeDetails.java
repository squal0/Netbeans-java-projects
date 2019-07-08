
package sess_3jaxpdom;

import classes.Employee;
import java.net.URL;
import java.util.List;

/**
 *
 * @author c.muturi
 */
public class DisplayEmployeeDetails
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //Declare and Instantiate an object of the DOMReader class
        DOMReader empDOM = new DOMReader();
        URL pathToFile = DisplayEmployeeDetails.class.getResource("../xmlfiles/employees.xml");
        String XMLFilePath = pathToFile.getPath();
                
        //List to hold the employee details
        List<Employee> myEmps = empDOM.getDataFromXMLFile(XMLFilePath);
        
        //Display the number of employee nodes in the file
        System.out.println("There are " + myEmps.size() + " nodes in the file.");
        
        //Use a for each loop to display the employee details
        for (Employee myEmp : myEmps)
        {
            //Display the employee details using the overriden toString() method
           System.out.println(myEmp);
    
        }
       
    }
    
}
