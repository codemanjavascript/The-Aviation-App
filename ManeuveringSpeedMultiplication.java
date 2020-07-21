/*
 Tommy Wan
 3/22/2020
 This class calculates maneuvering speed in GUI through multiplication
 */
package wansteam;

//Import required packages
import static java.lang.Math.sqrt;

public class ManeuveringSpeedMultiplication
{
    //Variables for the numbers being used
    private double loadFactorNumber;
    private double stallSpeedNumber;
    private double maneuveringSpeedNumber;
    private double rootLoadFactorNumber;
    
    //Constructor with Attributes
    public ManeuveringSpeedMultiplication(double loadFactorNumber, double stallSpeedNumber)
    {
        this.loadFactorNumber = loadFactorNumber;
        this.stallSpeedNumber = stallSpeedNumber;
        setManeuveringSpeed();
    }

    //Empty constructor for good practice
    public ManeuveringSpeedMultiplication()
    {
        this.loadFactorNumber = 0.0;
        this.stallSpeedNumber = 0.0;
        setManeuveringSpeed();
    }

    //Get and Set Methods for the Numbers in the Calculation
    public double getLoadFactorNumber()
    {
        return this.loadFactorNumber;
    }

    public void setLoadFactorNumber(double loadFactorNumber)
    {
        this.loadFactorNumber = loadFactorNumber;
    }

    public double getStallSpeedNumber()
    {
        return this.stallSpeedNumber;
    }

    public void setStallSpeedBumber(double stallSpeedNumber)
    {
        this.stallSpeedNumber = stallSpeedNumber;
    }

    public double getManeuveringSpeed()
    {
        return this.maneuveringSpeedNumber;
    }

    //Main Calculation Part in This SET Method
    public void setManeuveringSpeed()
    {
        //Squareroot of loadFactor
        this.rootLoadFactorNumber = sqrt(loadFactorNumber);
        //Multiply by stall speed
        this.maneuveringSpeedNumber = this.stallSpeedNumber * rootLoadFactorNumber;
        //Round
        this.maneuveringSpeedNumber = Math.round(this.maneuveringSpeedNumber * 100);
        this.maneuveringSpeedNumber= this.maneuveringSpeedNumber / 100;
    }

    //override checks with the computer. it's basically a comment to the program. its a polymorhpism,
    @Override
    public String toString()
    {
        return "The Maneuvering Speed is " + maneuveringSpeedNumber; //results in the to string method
    }

    //main method
    public static void main(String[] args)
    {
        //Sets in final, and creates objcet to be displayed in output frame
        ManeuveringSpeedMultiplication speedObj = new ManeuveringSpeedMultiplication();
        System.out.println(speedObj); //calls the object and prints it. If it dosent have override, it would run the address since it dosent have a TRUE to String method 
    }
}