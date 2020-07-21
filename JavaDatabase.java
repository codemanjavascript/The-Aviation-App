/*
 Tommy Wan
 5/14/2020
 This is to show how database connections work with in Java and Database
*/

//Note. First is constructor, set and gets, then main method
package wansteam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JavaDatabase
{
    //DB name
    private String dbName;
    private Connection dbConn; //Db connection
    private ArrayList<ArrayList<String>> data; //array list data
   
    //Constructor. Use this when creating new database with no name
    public JavaDatabase() //when you do not have a database yet
    {
        dbName = ""; //Empty name
        dbConn = null; //Empty Values for null
        data = null;
    }
   
    //Constructor used with existing database since it has parameters
    //Note, right click, insert code, and you can auto make get and set methods. Click encapsulation if not private var
    public JavaDatabase(String dbName) //asumes existing database
    {
        setDbName(dbName); //sets name to db
        setDbConn(); //sets connection
        data = null;
    }

    //Set and Gets (Standards)
    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public Connection getDbConn()
    {
        return dbConn;
    }

    public void setDbConn()
    {
        String connectionURL = "jdbc:derby:" + this.dbName; //Create the connection url
        this.dbConn = null; //set to null to be able to assing value. Dummy assignment
        //You can add JavaDB Driver in library and get 3 jar files. You really need just first one
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.dbConn = DriverManager.getConnection(connectionURL); //makes the connection
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver not found, check Library");  //Not found in library, or typed in wrong
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection error!");  //SQL connection other problems
        }
    }

    //Closes DB Conn
    public void closeDbConn()
    {
        try
        {
            this.dbConn.close();
        }
        catch (Exception err)
        {
            System.out.println("DB closing error.");
        }
    }
    
    //get DataMethod: read data from database and return the database data for us. We basically added more in terms of parameters
    public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders) //recieve data from the database and return it. headers is column names      
    {
        //Returns 3 since we have 3 columns: name price color
        int columnCount = tableHeaders.length; //count how many columns we have. Table header is array
        Statement s = null; //Special database objects
        ResultSet rs = null; //result set. It returns true if there is something next of a row
        String dbQuery = "SELECT * FROM " + tableName; //Query to GET THE DATA with sequl. and use space! You need to concattonate
        this.data = new ArrayList<>(); //constructs the data
       
        //read the data
        try
        {
            System.out.println("test 0!");
            //send the query and recieve data
            s = this.dbConn.createStatement();
            rs = s.executeQuery(dbQuery); //reads query and assigns to object of ResultSet like a pointer
           
            //read the data using rs and store in ArrayList data
            //read the data while using rs and store. Checks if there is value in the next row, and if there is the pointer reads the first record. Then it goes next next.. until it reaches nothing, so it exits. It just makes sure we get through all our info and records with rs.
            //When it finishes, rs.next is false, and exits loop, exits try and catch, and returns data
            while (rs.next()) //checks for booleans and has 2 statements. Has next and get next. if has next is true, it gets next. It's combined together
            {
                //row object to hold one row data
                ArrayList<String> row = new ArrayList<>();
                System.out.println("test 1");
                //go through the row and read each cell
                //Starts at 0. There are 3 columns, so whenver its less than 3, it executes
                for (int i=0; i<columnCount; i++)
                {
                    System.out.println("test 2");
                    //read cell i and add the cell to the row
                    //row.add(rs.getString(tableHeaders[i]));
                    //String cell = rs.getString("Name"); Then when i = 1, the price gets added to the cell.
                    System.out.println("test 3");
                    String cell = rs.getString(tableHeaders[i]);  //gets string from each column, reads the member or the cell that rs is pointing to. Assigning it to a cell, and adding to row creating info for columns until all rows are created and while loops is exited
                    System.out.println("test 4");
                    row.add(cell); //The cell we stored, which is the first tableHeader. If the first tableHeader is cessna, this adds the Cessna to the row
                    System.out.println("test 5");
                    //After all this, it does i++
                    //After, we do the while loop again and if it dosent work, lets say i = 1, 1 < 3 so it runs again
                }
                //add the row to the data
                this.data.add(row); //Adds when 3<3 which is false, so loop is exited, and adds the 3 info we have to the row!
                //Each array list holds an object of array list
            }
        }
        catch (SQLException se)
        {
            System.out.println("SQL Error: Not able to get data");
        }
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data)
    {
        this.data = data;
    }
   
    //main method for the createDB
    public void createDb(String newDbName)
    {
        setDbName(newDbName);
        String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
        this.dbConn = null;
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.dbConn = DriverManager.getConnection(connectionURL);
            System.out.println("New Database " + this.dbName + " created!");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver not found, check Library");
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection error, Db was not created!");
        }
    }
    
    public void createTable(String newTable, String dbName)
    {
        System.out.println(newTable); //this prints our table so we can easily debug for errors if needed
        setDbName(dbName); //sets db name
        setDbConn(); //set Db conn
        Statement s; //creates a statement. s is a common way to declare this
        try
        {
            s = this.dbConn.createStatement();
            s.execute(newTable); //excecutes the new table
            System.out.println("New table created!"); //If everything works, the table is created
        }
        catch (SQLException se)
        {
            System.out.println("SQL connection error or syntax error!"); //Something is wrong here.
        }
    }
    
    //Ground Speed Table
    public void createTableTwo(String newTable, String dbName)
    {
        System.out.println(newTable); //this prints our table so we can easily debug for errors if needed
        setDbName(dbName); //sets db name
        setDbConn(); //set Db conn
        Statement s; //creates a statement. s is a common way to declare this
        try
        {
            s = this.dbConn.createStatement();
            s.execute(newTable); //excecutes the new table
            System.out.println("New table created!"); //If everything works, the table is created
        }
        catch (SQLException se)
        {
            System.out.println("SQL connection error or syntax error!"); //Something is wrong here.
        }
    }
    
    //Converting array list to array
    public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
    {
        //If statement for size and no data entered on may 18
        if (data.size()==0)
        {
            Object[][] dataList = new Object[0][0];
            return dataList;
        }
        else
        {
            int columnCount = data.get(0).size(); //gets the first data
            Object[][] dataList = new Object[data.size()][columnCount];
            for (int i = 0; i < data.size(); i++) //goes through each data and concerts into a 2d array so that it works with JTable
            {
                ArrayList<String> row = data.get(i);
                for(int j = 0; j < columnCount; j++)
                {
                    dataList[i][j] = row.get(j);
                }
            }
            return dataList; 
        }
    }
        
    public static void main(String[] args)
    {
        //Originally, this was a place to run and insert placebo data, but I want the user to be able to exlusively have their own. 
    }
}

