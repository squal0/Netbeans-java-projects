/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author c.muturi
 */
public class Courses
{
    
    public String Coursecode;
    public String Coursename;
    public int duration;
    public String totalmodules;

    public String getCourseCode()
    {
        return Coursecode;
    }

    public void setCourseCode(String CourseCode) 
    {
        this.Coursecode = CourseCode;
    }

    public String getCourseName() 
    {
        return Coursename;
    }

    public void setCourseName(String CourseName)
    {
        this.Coursename = CourseName;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public String getTotalModules()
    {
        return totalmodules;
    }

    public void setTotalModules(String totalModules)
    {
        this.totalmodules = totalModules;
    }

    
    
}
