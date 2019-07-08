/**
 * The code in this class will be used to create the DOM Objects
 */
package sess7_db2xml;

import classes.Courses;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author c.muturi
 */
public class DOMCreator 
{
    //Declare a Document object
    Document doc = null;
    
    //Non-parameterized/No-argument constructor
    public DOMCreator(){}
    
    //Method to create an XML Document layout
    public Document createXMLDocument(List<Courses> courseData)
    {
      try
      {
          //Create a document builder object
          DocumentBuilder builder = DocumentBuilderFactory.newInstance().
                                    newDocumentBuilder();
          
          //Instantiate a document object
          doc = builder.newDocument();
          
          //Creadt the xml's document root element
          Element rootElement = doc.createElement("courses");
          
          //Attach/append the element to the document
          doc.appendChild(rootElement);
          
          //use a for each loop to create and append other elements in the document
          for(Courses courses : courseData)
          {
            //Create a course element and   add it to the courses element
              Element courseElem = addElement(rootElement,"course","");
              
            //Create a coursecode element, set its value and add it to the course element
             addElement(courseElem,"coursecode",courses.getCourseCode())
                     ;
            //Create a coursecode element, set its value and add it to the course element 
             addElement(courseElem,"coursename",courses.getCourseName());
             
             //Create a coursecode element, set its value and add it to the course element 
             addElement(courseElem,"duration",courses.getDuration() + "months");
             
             //Create a coursecode element, set its value and add it to the course element 
             addElement(courseElem,"totalmodules","" + courses.getTotalModules()+"");
          }
      }
      catch(Exception e)
      {
        e.printStackTrace();  
      }
      return doc;
    }

    /**
     * 
     * @param parentElement
     * @param childElem
     * @param childElementValue
     * @return 
     */
    private Element addElement(Element parentElement, String childElem, 
            String childElementValue)
    {
        //Create a new element
        Element childElement = doc.createElement(childElem);
        
        //Set the child's elements value
        childElement.setTextContent(childElementValue);
        
        //Add/Append the child element to it's parent tag
        parentElement.appendChild(childElement);
        return childElement;
    }
}
