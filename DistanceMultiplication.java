/*
 Tommy Wan
 3/22/2020
 This class serves as the calculation frame and calculates the multiplication for distance
*/
package wansteam;

//Import required packages
public class DistanceMultiplication
{
    //Variables for the numbers being used
    private double groundSpeedNumber;
    private double timeNumber;
    private double distance;
    private double hours;
   
    //Constructor with Attributes
    public DistanceMultiplication(double groundSpeedNumber, double timeNumber)
    {
        //Set variables 
        this.groundSpeedNumber = groundSpeedNumber;
        this.timeNumber = timeNumber;
        //Converts mintues to hours
        this.timeNumber = this.timeNumber / 60; 
        //System.out.println("TIME NUMBER IS: " + this.timeNumber);
        //Set distance calculation
        setDistance();
        
    }

    //Empty Constructor for Practice
    public DistanceMultiplication()
    {
        this.groundSpeedNumber = 0.0;
        this.timeNumber = 0.0;
        setDistance();
    }

    //Get and Set Methods for the Numbers in the Calculation
    public double getGroundSpeedNumber()
    {
        return this.groundSpeedNumber;
    }

    public void setGroundSpeedNumber(double GroundSpeedNumber)
    {
        this.groundSpeedNumber = groundSpeedNumber;
    }

    public double getTimeNumber()
    {
        return this.timeNumber;
    }

    public void setTimeNumber(double timeNumber)
    {
        this.timeNumber = timeNumber;
    }

    public double getDistance()
    {
        return this.distance;
    }

    //Main Calculation Part in This SET Method
    public void setDistance()
    {
        this.timeNumber = Math.round(this.timeNumber * 100);
        this.timeNumber = this.timeNumber / 100;
        this.distance = groundSpeedNumber * timeNumber;
        this.distance = Math.round(this.distance * 100);
        this.distance = this.distance / 100;
        
        
    }

    //override checks with the computer. it's basically a comment to the program. its a polymorhpism,
    @Override
    public String toString()
    {
        return "The distance give " + groundSpeedNumber + "knots and " + hours + "hours is " + distance; //results in the to string method
    }

    //Main method
    public static void main(String[] args)
    {
        //Sets in final, and creates objcet to be displayed in output frame
        DistanceMultiplication distanceObj = new DistanceMultiplication(3, 9);
    }
}