/*
 Tommy Wan
 3/22/2020
 This class allows for input for distance calculation
*/
package wansteam;

//Import required packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


//Create class with nessesary requirements
public class DistanceInput extends JFrame implements ActionListener
{
    //Declare Text Fields
    private JPanel inputPanel;
    private JPanel lowerPanel;
    private JPanel middlePanel;
    private JLabel idLabel;
    //private JTextField idField;
    private JTextField groundSpeedField;
    private JLabel groundSpeedLabel;
    private JTextField timeField;
    private JLabel timeLabel;

    //Buttons
    private JButton calculateButton;
    private JButton backButton;

    //Menus
    private JMenuBar mainBar; //Menu Bar
    private JMenu navigationMenu; //Menu
    private JMenuItem helpItem;
    private JMenuItem definitionItem;
    private JMenuItem aboutItem;
    private JMenuItem faqItem;
    private JMenuItem homeItem;
    private JMenuItem activityLogItem;
    private JMenuItem exitItem;

    //Images
    private final URL DISTANCE_PATH = getClass().getResource("distance.jpeg");
    private final ImageIcon DISTANCE_IMAGE = new ImageIcon(new ImageIcon(DISTANCE_PATH).getImage().getScaledInstance(650, 370, Image.SCALE_DEFAULT));
    private JLabel imageLabel;

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Color COLOR_RED = new Color(253, 73, 69);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);

    DistanceDisplay gFrame; //Global Frame

    //Constructor for Input Frame
    public DistanceInput(DistanceDisplay pFrame)
    {
        super("Calculate Distance");

        //Default Operations in the frame
        this.setBounds(300, 200, 800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COLOR_RED);
        this.setLayout(new BorderLayout());
        
        //To fix the scope, we pass g grame to p frame
        this.gFrame = pFrame; //Makes parameter pFrame accessible to other methods. Therefore, we can dispose insert frame IN ButtonsWithDisplay Class, since its passed in both.

        //Constructing the Menus and Information
        this.mainBar = new JMenuBar();
        this.navigationMenu = new JMenu("Navigation");
        this.helpItem = new JMenuItem("Help");
        this.helpItem.addActionListener(this);
        this.definitionItem = new JMenuItem("Definitions");
        this.definitionItem.addActionListener(this);
        this.aboutItem = new JMenuItem("About Me!");
        this.aboutItem.addActionListener(this);
        this.faqItem = new JMenuItem("Frequently Asked Questions");
        this.faqItem.addActionListener(this);
        this.activityLogItem = new JMenuItem("Activity Log");
        this.activityLogItem.addActionListener(this);
        this.homeItem = new JMenuItem("Home");
        this.homeItem.addActionListener(this);
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);
        mainBar.add(navigationMenu);
        navigationMenu.add(helpItem);
        navigationMenu.add(definitionItem);
        navigationMenu.add(aboutItem);
        navigationMenu.add(faqItem);
        navigationMenu.add(activityLogItem);
        navigationMenu.add(homeItem);
        navigationMenu.add(exitItem);

        //Input Panel Code
        this.inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(COLOR_RED);

        //Lower Panel Code which contains buttons
        this.lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBackground(COLOR_RED);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_RED);

        //Text Fields 
        this.groundSpeedField = new JTextField(7);
        this.timeField = new JTextField(7);
        //this.idField = new JTextField(7);

        //Buttons
        this.backButton = new JButton("Back");
        this.backButton.addActionListener(this);
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.addActionListener(this);

        //Labels and Text Fields on Top
        //this.idLabel = new JLabel("Entry #:");
        //this.idLabel.setForeground(COLOR_WHITE);
        this.groundSpeedLabel = new JLabel("Ground Speed (Knots): ");
        this.groundSpeedLabel.setForeground(COLOR_WHITE);
        this.timeLabel = new JLabel("Time (Minutes): ");
        this.timeLabel.setForeground(COLOR_WHITE);
        
        //image label
        this.imageLabel = new JLabel(DISTANCE_IMAGE);

        //Add Buttons to LowerPanel
        this.lowerPanel.add(calculateButton);
        this.lowerPanel.add(backButton);

        //Add Fields to Input with Label
        this.inputPanel.add(groundSpeedLabel);
        this.inputPanel.add(groundSpeedField);
        this.inputPanel.add(timeLabel);
        this.inputPanel.add(timeField);
        this.middlePanel.add(imageLabel);

        //Adds Panels inside the frame
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        //set visible to user
        this.setJMenuBar(mainBar);
        this.setVisible(true); 
    }
    
    //Main method
    public static void main(String[] args)
    {
        String dbName = "AviationDatabase";
        String tableName = "Distance";
        String[] columnHeaders =
        {
           "Groundspeed", "Time", "Distance"
        };
        
        DistanceDisplay mainObj = new DistanceDisplay(dbName, tableName, columnHeaders);
        DistanceInput distanceObj = new DistanceInput(mainObj);    
    }

    //Override Method to have for buttons
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Databse Variables
        String command = e.getActionCommand();
        String dbName = "AviationDatabase";
        String tableName = "Distance";
        String[] tableHeaders =
        {
           "Groundspeed", "Time", "Distance"
        }; 
        //note: the spaces are put in so that the table headers will be centered.
        //new DistanceDisplay(dbName, tableName, tableHeaders);
        
        gFrame.setVisible(false);
        String dbQuery = "INSERT INTO Distance VALUES (?, ?, ?)";
        
        //Connect to db
        JavaDatabase objDb = new JavaDatabase(dbName);
        Connection myDbConn = objDb.getDbConn();
        
        
        //list out variables
        Object myComponent = e.getSource();
        String groundSpeedInput;
        String timeInput;
        double groundSpeedValue;
        double timeValue;
        double output;
        //int id;
        String message;

        //if state ments for buttons
        if (command.equals("Calculate"))
        {
            try
            {
                //set equate
                groundSpeedInput = groundSpeedField.getText();
                timeInput = timeField.getText();
                groundSpeedValue = Double.parseDouble(groundSpeedInput);
                timeValue = Double.parseDouble(timeInput);
                
                //empty textField
                groundSpeedField.setText("");
                timeField.setText("");
                //idField.setText("");

                if (groundSpeedValue < 0)
                {
                    Warning warningObj = new Warning("Error Calculating your Number"); //creates a new frame, which will show in warning frame
                }
                else if (timeValue < 0)
                {
                    Warning warningObj = new Warning("Error Calculating your Number");
                } 
                else
                {
                    try
                    {
                        //Calculations from the calculation class
                        DistanceMultiplication distanceObj = new DistanceMultiplication(groundSpeedValue, timeValue); //Recives two values,  and shows it in the passed values
                        output = distanceObj.getDistance();
                        groundSpeedValue = distanceObj.getGroundSpeedNumber();
                        timeValue = distanceObj.getTimeNumber();

                        //New Object
                        DistanceOutput distanceObject = new DistanceOutput(output, groundSpeedValue, timeValue);
                    
                        //prepare statement using query we prepared with INSERT for 3 values. PS can assign values to query
                        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);//and import
                        //enter data into query. It dosen't have to be in order since it has labels. It assings 1 to first quetsion mark, and then so on
  
                        ps.setDouble(1, groundSpeedValue); 
                        ps.setDouble(2, timeValue);
                        ps.setDouble(3, output); 

                        //execute the query in the DB
                        ps.executeUpdate();
                        System.out.println("Data inserted successfully!"); //of we get here, it good.

                        //Deletes table,and immediately reconstructs table, so we update. When we close a connection, it only gets rid of object, but Frames still have info, so we dispose frame before we make a new connection with new info on table
                        gFrame.dispose();
                        //gFrame = new DistanceDisplay(dbName, tableName, tableHeaders);
                        
                        gFrame.toBack();
                    }
                    catch(SQLException se)
                    {
                        System.out.println("Error inserting data");
                    }     
                }
            } //catch for to check the thing
            catch (NumberFormatException nfe)
            {
                //System.out.println("You need a number!");
                Warning warningObj = new Warning("Error Calculating your Number"); //creates a new frame, which will show in warning frame
            } //general exception catch, if everything fails
            catch (Exception ge)
            {
                Warning warningObj = new Warning("Error Calculating your Number");
            }
            //Validate and Repaint required
            this.validate();
            this.repaint();
        } 
        else if (command.equals("Back"))
        {
            //Disposes and Creates Frame
            this.dispose();
            Option optionObjectOne = new Option("Option Page");

        } 
        else if (myComponent == helpItem)
        {
            //Help popup frame
            Help helpObject = new Help("Help Page");
        } 
        else if (myComponent == definitionItem)
        {
            Definitions definitionObject = new Definitions("Definition Page");
        }
        else if (myComponent == aboutItem)
        {
            About aboutObject = new About("About Page");
        }
        else if (myComponent == faqItem)
        {
            FAQ faqObject = new FAQ("FAQ Page");
        }
        else if (myComponent == activityLogItem)
        {
            ActivityLog activityObject = new ActivityLog("Activity Log");
        }
        else if (myComponent == homeItem)
        {
            //Disposes and Creates Frame
            this.dispose();
            Welcome welcomeObject2 = new Welcome("Welcome");
        } 
        else if (myComponent == exitItem)
        {
            //Exits
            System.exit(0);
        }
        //Required Validations
        this.validate();
        this.repaint();
    }
}