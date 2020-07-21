/*
 Tommy Wan
 4/30/2020
 This is to show how database are installed. SelfNote: Whenever you want new DB in new project, get install db and JavaDatabse Code, then click on Project --> Libraries --> Add Library --> Java DB, and then install
 */
package wansteam;
public class InstallDb
{
    public static void main(String[] args)
    {
        //This is the name of the database
        String dbName = "AviationDatabase";
        
        //Creating an object of db class
        JavaDatabase objDb = new JavaDatabase(); //we are using java data base because we ONLY have a name, and nothing else. So we create object
        
        //Creating a new database
        objDb.createDb(dbName); //creates the database with the name
       
        //Tables for Calculations
        String newTable = "CREATE TABLE Distance (Groundspeed double, Time double, Distance double)"; //This is SQL in a string which goes into the database. This will turn into a table
        String newTableTwo = "CREATE TABLE groundSpeed (Distance double, Time double, Groundspeed double)";
        String newTableThree = "CREATE TABLE Time (Distance double, Groundspeed double, Time double)";
        String newTableFour = "CREATE TABLE MilesToKnots (Miles double, Knots double)";
        String newTableFive = "CREATE TABLE CompassHeading (TrueCourse double, WindCorrectionAngle double, MagneticVariation double, MagneticDeviation double, CompassHeading double)"; //Fix Camel Case?
        String newTableSix = "CREATE TABLE ManeuveringSpeed (LoadFactor double, StallSpeed double, ManeuveringSpeed double)";
        
        //Create Tables
        objDb.createTable(newTable, dbName); //Create Table with table and database.
        objDb.createTable(newTableTwo, dbName); //Create Table with table and database.
        objDb.createTable(newTableThree, dbName); //Create Table with table and database.
        objDb.createTable(newTableFour, dbName); //Create Table with table and database.
        objDb.createTable(newTableFive, dbName);
        objDb.createTable(newTableSix, dbName);
    }
}

