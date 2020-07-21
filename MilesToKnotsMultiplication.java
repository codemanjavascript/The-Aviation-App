/*
 Tommy Wan
 3/22/2020
 This class acts as the frame that calculates knots throught multiplication
 */
package wansteam;

//import required packages
public class MilesToKnotsMultiplication
{
    //Variables for the numbers being used
    final double KNOTS_CONSTANT = 1.15078;
    private double milesNumber;
    private double knots;
  
    //Constructor with Attributes
    public MilesToKnotsMultiplication(double milesNumber)
    {
        this.milesNumber = milesNumber;
        setKnots();
    }

    //Empty constructor
    public MilesToKnotsMultiplication()
    {
        this.milesNumber = 0.0;
        setKnots();
    }

    //Get and Set Methods for the Numbers in the Calculation
    public double getMilesNumber()
    {
        return this.milesNumber;
    }

    public void setMilesNumber(double milesNumber)
    {
        this.milesNumber = milesNumber;
    }

    public double getKnots()
    {
        return this.knots;
    }

    //Main Calculation Part in This SET Method
    public void setKnots()
    {
        //Division to get Knots, then ORunding
        this.knots = milesNumber / KNOTS_CONSTANT;
        this.knots = Math.round(this.knots * 100);
        this.knots = this.knots / 100;
    }

    //override checks with the computer. it's basically a comment to the program. its a polymorhpism,
    @Override
    public String toString()
    {
        return "Knots is " + milesNumber + " divided by 1.15078"; //results in the to string method
    }

    //main method
    public static void main(String[] args)
    {
        //Sets in final, and creates objcet to be displayed in output frame
        MilesToKnotsMultiplication knotsObj = new MilesToKnotsMultiplication(3);
        System.out.println(knotsObj); //calls the object and prints it. If it dosent have override, it would run the address since it dosent have a TRUE to String method 
    }
}