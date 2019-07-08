
package sess4_XSL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author c.muturi
 */
public class TransformXMLToHTML 
{
    //main methos begins program execution
    public static void main(String[] args) throws TransformerConfigurationException,
        TransformerException, FileNotFoundException
    {
        //Variable to be used in the program
        URL xsltPath, xmlPath;
        String path2xslt, path2xml;
        
        //Set the  URL'S to the files
        xsltPath = TransformXMLToHTML.class.getResource("students.xsl");
        xmlPath = TransformXMLToHTML.class.getResource("students.xml");
        
        //String variable to hold the path to the HTML file to be created
        String path2HTMLFile = xmlPath.getPath().replaceAll("students.xml", "");
        try
        {
           //Set the paths to the xml and xml file respectively
           path2xslt = xsltPath.getPath();
           path2xml = xmlPath.getPath();
            
           //Create and instantiate a transformerFactory object/instance
           TransformerFactory tff = TransformerFactory.newInstance();
            
           //Create and instantiate a transformer object
           Transformer tf = tff.newTransformer(new StreamSource(new File(path2xslt)));
           
           //Create and instantiate the source document to be transformed/converted to html
           StreamSource xmlSource = new StreamSource(path2xml);
           
           //Specify the output result after the transformation
           StreamResult htmlOutput = new StreamResult(new FileOutputStream(new File(path2HTMLFile +
                   "students.html")));
           
           //Conduct the xml to html transformation
           tf.transform(xmlSource, htmlOutput);
           
           //Inform the user that the file has been created
           System.out.println("The student.html file has been created!");
        }
        catch(TransformerException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
