
package sess2_sax;

import java.io.File;
import java.net.URL;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author c.muturi
 */
public class EmployeeXMLSaxParser
{
    //Main method begins program execution
    public static void main(String[] args)
    {
       //Variables to be used in the program
        URL empXMLPath = EmployeeXMLSaxParser.class.getResource("../xmlfiles/employees.xml");
        String filePath = empXMLPath.getPath();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try
        {
            SAXParser sp = spf.newSAXParser();
            SAXParseHandler sph = new SAXParseHandler();
            sp.parse(new File(filePath),sph);
            //Retrieve the list of employees
            List<Employee> empList = sph.getEmployees();
            //Display the list of empolyees in the emplist list
            for(Employee curEmp: empList)
            {
                System.out.println("Display employee details for: " +
                        curEmp.getLastname());
                System.out.printf("----------------");
                System.out.printf("\nEmployee ID: %d " ,curEmp.getEmpID()); 
                System.out.printf("\nEmployee First Name:%s " , curEmp.getFirstname());
                System.out.printf("\nEmployee Last Name:%s" , curEmp.getLastname()); 
                System.out.printf("\nEmployee Location: %s " , curEmp.getLocation());
                System.out.println("\n-------------------------");
                //Alternatively you can invoke the overriden toString() method
                //System.out.println(curEmp.toString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }       
    }
    
}
