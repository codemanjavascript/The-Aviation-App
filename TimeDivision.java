/*
 Tommy Wan
 3/22/2020
 This class calculates time in GUI through Division
 */
package wansteam;

//Import required packages
public class TimeDivision
{
    //Variables for the numbers being used
    private double distanceNumber;
    private double groundSpeedNumber;
    private double time;
    private double hours;

    //Constructor with Attributes
    public TimeDivision(double distanceNumber, double groundSpeedNumber)
    {
        this.distanceNumber = distanceNumber;
        this.groundSpeedNumber = groundSpeedNumber;
        //this.timeNumber = this.timeNumber / 60; 
        setTime();
    }

    //Empty constructor
    public TimeDivision()
    {
        this.distanceNumber = 0.0;
        this.groundSpeedNumber = 0.0;
        setTime();
    }

    //Get and Set Methods for the Numbers in the Calculation
    public double getDistanceNumber()
    {
        return this.distanceNumber;
    }

    public void setDistanceNumber(double distanceNumber)
    {
        this.distanceNumber = distanceNumber;
    }

    public double getGroundSpeedNumber()
    {
        return this.groundSpeedNumber;
        
    }

    public void setGroundSpeedNumber(double groundSpeedNumber)
    {
        this.groundSpeedNumber = groundSpeedNumber;
        
    }

    public double getTime()
    {
        return this.time;
    }

    //Main Calculation Part in This SET Method
    public void setTime()
    {
        this.time = distanceNumber / groundSpeedNumber;
        this.time = Math.round(this.time * 100);
        this.time = this.time / 100;
    }

    //override checks with the computer. it's basically a comment to the program. its a polymorhpism,
    @Override
    public String toString()
    {
        return "The time given " + distanceNumber + "nautical miles and " + groundSpeedNumber + "knots is " + time; //results in the to string method
    }

    //Main method
    public static void main(String[] args)
    {
        //Sets in final, and creates objcet to be displayed in output frame
        TimeDivision timeObj = new TimeDivision();
        System.out.println(timeObj); //calls the object and prints it. If it dosent have override, it would run the address since it dosent have a TRUE to String method 
    }
}