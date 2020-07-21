/*
 Tommy Wan
 3/22/2020
 This class is the calculation class calculates the addition needed for compass heading. It also takes subtraction
 */
package wansteam;

//Import required packages
public class CompassHeadingAddition
{
    //Variables being used for addition
    private double trueCourseNumber;
    private double windCorrectionAngle; //This is the official name for the WCA.
    private double magneticVariationNumber;
    private double magneticDeviationNumber;
    private double compassHeading;
    private double trueHeadingNumber;
    private double magneticHeadingNumber;

    //Constructor with Attributes
    public CompassHeadingAddition(double trueCourse, double windCorrectionAngle, double magneticVariation, double magneticDeviation)
    {
        //Setting equals, and setting method
        this.trueCourseNumber = trueCourse;
        this.windCorrectionAngle = windCorrectionAngle;
        this.magneticVariationNumber = magneticVariation;
        this.magneticDeviationNumber = magneticDeviation;
        this.trueHeadingNumber = trueHeadingNumber;
        this.magneticHeadingNumber = magneticHeadingNumber;
        setCompassHeading();
    }

    //Empty constructor for good practice
    public CompassHeadingAddition()
    {
        this.trueCourseNumber = 0.0;
        this.windCorrectionAngle = 0.0;
        this.magneticVariationNumber = 0.0;
        this.magneticDeviationNumber = 0.0;
        this.trueHeadingNumber = 0.0;
        this.magneticHeadingNumber = 0.0;
        setCompassHeading();
    }

    //Get and Set Methods for the Numbers in the Calculation
    public double getTrueCourseNumber()
    {
        return this.trueCourseNumber;
    }

    public void setTrueCourseNumber(double trueCourseNumber)
    {
        this.trueCourseNumber = trueCourseNumber;
    }

    public double getTrueHeadingNumber()
    {
        return this.trueHeadingNumber;
    }

    public void setTrueHeadingNumber(double trueHeadingNumber)
    {
        this.trueHeadingNumber = trueHeadingNumber;
    }

    public double getMagneticHeadingNumber()
    {
        return this.magneticHeadingNumber;
    }

    public void setMagneticHeadingNumber(double magneticHeadingNumber)
    {
        this.magneticHeadingNumber = magneticHeadingNumber;
    }

    public double getWindCorrectionAngleNumber()
    {
        return this.windCorrectionAngle;
    }

    public void setWindCorrectionAngleNumber(double windCorrectionAngleNumber)
    {
        this.windCorrectionAngle = windCorrectionAngleNumber;
    }

    public double getMagneticVariationNumber()
    {
        return this.magneticVariationNumber;
    }

    public void setMagneticVariationNumber(double magneticVariationNumber)
    {
        this.magneticVariationNumber = magneticVariationNumber;
    }

    public double getMagneticDeviationNumber()
    {
        return this.magneticDeviationNumber;
    }

    public void setMagneticDeviationNumber(double magneticDeviationNumber)
    {
        this.magneticDeviationNumber = magneticDeviationNumber;
    }

    public double getCompassHeading()
    {
        return this.compassHeading;
    }

    //Main Calculation Part in This SET Method
    public void setCompassHeading()
    {
        System.out.println("Magnetic Variation: " + this.magneticVariationNumber);
        System.out.println("Magnetic Deviation: " + this.magneticDeviationNumber);
        System.out.println("True Course: " + this.trueCourseNumber);
        System.out.println("WCA: " + this.windCorrectionAngle);
        
        //Add TCN and WCA to get TH
        trueHeadingNumber = this.trueCourseNumber + this.windCorrectionAngle;
        System.out.println(this.trueHeadingNumber);
        //Add TH and MD to get MH
        this.magneticHeadingNumber = this.trueHeadingNumber + this.magneticVariationNumber;
        System.out.println("True heading: " + this.trueHeadingNumber);
        System.out.println("Magnetic Variation: " + this.magneticVariationNumber);
        System.out.println(this.magneticHeadingNumber);
        
        //ADD MH and MD to get CH
        this.compassHeading = this.magneticHeadingNumber + this.magneticDeviationNumber;
        
        //Rounding to 100th place
        this.compassHeading = Math.round(this.compassHeading * 100);
        this.compassHeading = this.compassHeading / 100;
        
    }

    //Polymorphism Checks
    @Override
    public String toString()
    {
        return "The compass heading is " + compassHeading; //results in the to string method
    }

    //Main Method
    public static void main(String[] args)
    {
        //Create Compass Frame Object
        CompassHeadingAddition compassHeadingObj = new CompassHeadingAddition();
    }
}