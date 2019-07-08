package classes;

/**
 *
 * @author c.muturi
 */
public class Employee 
{
   
    /**
     * Class instance field/properties
     */
    public int EmpID;
    public String firstname;
    public String lastname;
    public String location;
    
    //Names of Employee data items
    public static final String ID = "empID" ,
            FIRSTNAME = "firstname",
            LASTNAME = "lastname",
            LOCATION = "location";
    
    //Constructors
   

    /**
     * @return the empID
     */
    public int getEmpID() 
    {
        return EmpID;
    }

    /**
     * @param EmpID the Employee's Id to be set
     */
    public void setEmpID(int EmpID) 
    {
        this.EmpID = EmpID;
    }

    /**
     * @return the firstname
     */
    public String getFirstname()
    {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname()
    {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    //Override the toString() method
    @Override 
    public String toString()
    {
        String empDetails = "__________";
        empDetails +="\nEmployee ID: " + this.getEmpID();
        empDetails +="\nEmployee First Name: " + this.getFirstname();
        empDetails +="\nEmployee Last Name: " + this.getLastname();
        empDetails +="\nEmployee Location: " + this.getLocation();
        empDetails +="\n__________";
        return empDetails;
    }
    
}
