/*
 Tommy Wan
 3/22/2020
 This class calculates ground speed through division
 */
package wansteam;

//Import required packages
public class GroundSpeedDivision
{
    //Variables for the numbers being used
    private double distanceNumber;
    private double timeNumber;
    private double groundSpeed;
    private double hours;
    
    //Constructor with Attributes
    public GroundSpeedDivision(double distanceNumber, double timeNumber)
    {
        //Equaling Variables
        this.distanceNumber = distanceNumber;
        this.timeNumber = timeNumber;
        //Converting to Hours
        this.timeNumber = this.timeNumber / 60; 
        setGroundSpeed();
    }

    //Empty constructor
    public GroundSpeedDivision()
    {
        this.distanceNumber = 0.0;
        this.timeNumber = 0.0;
        setGroundSpeed();
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

    public double getTimeNumber()
    {
        return this.timeNumber;
        
    }

    public void setTimeNumber(double timeNumber)
    {
        this.timeNumber = timeNumber;
        
    }

    public double getGroundSpeed()
    {
        return this.groundSpeed;
    }

    //Main Calculation Part in This SET Method
    public void setGroundSpeed()
    {
        //Division to get groundSpeed
        this.timeNumber = Math.round(this.timeNumber * 100);
        this.timeNumber = this.timeNumber / 100;
        this.groundSpeed = distanceNumber / timeNumber;
        //Rounding
        this.groundSpeed = Math.round(this.groundSpeed * 100);
        this.groundSpeed = this.groundSpeed / 100;
    }

    //override checks with the computer. it's basically a comment to the program. its a polymorhpism,
    @Override
    public String toString()
    {
        return "The ground speed given " + distanceNumber + "nautical miles and " + hours + "hours is " + groundSpeed; //results in the to string method
    }

    //Main method
    public static void main(String[] args)
    {
        //Sets in final, and creates objcet to be displayed in output frame
        GroundSpeedDivision groundSpeedObj = new GroundSpeedDivision();
        System.out.println(groundSpeedObj); //calls the object and prints it. If it dosent have override, it would run the address since it dosent have a TRUE to String method 
    }
}